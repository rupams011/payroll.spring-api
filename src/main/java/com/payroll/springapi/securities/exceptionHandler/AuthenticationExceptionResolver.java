package com.payroll.springapi.securities.exceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        Map<String, String> err = new HashMap<>();
        err.put("cause", String.valueOf(ex.getCause()));
        err.put("message",ex.getMessage());
        err.put("type", ex.getClass().getTypeName());
        /*if (List.of("POST","PUT","PATCH").contains(request.getMethod().toUpperCase().trim())){
            try{
                InputStream requestInputStream = request.getInputStream();
                err.put("requestBody", requestInputStream.toString());
            }catch (Exception e){}
        }*/
        err.put("servletPath", request.getServletPath());
        mv.setViewName(err.get("type"));
        mv.setStatus(HttpStatus.BAD_REQUEST);
        mv.addAllObjects(err);

        try {
            String message = "";
            String[] exceptionType = err.get("type").split("\\.");
            switch (exceptionType[exceptionType.length - 1]){
                case "SignatureException":
                    message = "Authentication Unsuccessful";
                case "ExpiredJwtException":
                    message = "Authentication expired. Please login again.";
                    break;
                default:
                    message = "Unhandled exception occurred at authentication. -> " + err.get("type");
            }
            response.getWriter().write(message);
        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
        return mv;
    }
}
