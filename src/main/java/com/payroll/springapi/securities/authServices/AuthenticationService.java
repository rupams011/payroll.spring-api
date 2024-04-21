package com.payroll.springapi.securities.authServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payroll.springapi.securities.authControllers.AuthenticationRequest;
import com.payroll.springapi.securities.authControllers.AuthenticationResponse;
import com.payroll.springapi.securities.authEntities.RegisterRequest;
import com.payroll.springapi.securities.authEntities.Token;
import com.payroll.springapi.entities.UserCredentials;
import com.payroll.springapi.repositories.UserCredentialsRepository;
import com.payroll.springapi.securities.securityConfigurations.JwtService;
import com.payroll.springapi.securities.authEntities.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserCredentialsRepository userCredentialsRepository;
//    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
//    private final RolesRepository rolesRepository;

    /*@Autowired
    public AuthenticationService(
            UserCredentialsRepository userCredentialsRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ){
        this.userCredentialsRepository = userCredentialsRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }*/

    public AuthenticationResponse register(RegisterRequest request) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        var user = UserCredentials.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
//                .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt("$2b")))
                .role(request.getRole())
                .timestamp(LocalDateTime.now())
                .build();
//        System.out.println("Entered Details ===> " + user);
        var savedUser = userCredentialsRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
//                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userCredentialsRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
//        revokeAllUserTokens(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
//                .token(jwtToken)
                .build();
    }

    private void saveUserToken(UserCredentials user, String jwtToken){
        var token = Token.builder()
                .userCredentials(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
//        tokenRepository.save(token);
    }

    /*private void revokeAllUserTokens(UserCredentials user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }*/

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userName = jwtService.extractUsername(refreshToken);
        if (userName != null) {
            var user = this.userCredentialsRepository.findByUsername(userName)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
//                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
