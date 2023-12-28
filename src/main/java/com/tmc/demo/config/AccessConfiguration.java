package com.tmc.demo.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AccessConfiguration implements AuthenticationSuccessHandler {

    private final String patientPage = "/patient/test";
    private final String therapistPage = "/therapist/test";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getPrincipal().toString().toLowerCase();
        if(role.contains("patient")) {
            response.sendRedirect(patientPage);
        } else if(role.contains("therapist")) {
            response.sendRedirect(therapistPage);
        }
    }
    
}
