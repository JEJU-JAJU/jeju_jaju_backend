package com.jejujaju.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jejujaju.exception.ErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.jejujaju.exception.GlobalErrorCode.LOGIN_DATA_NOT_FOUND;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        ErrorResponse errorResponse = ErrorResponse.createErrorResponse(LOGIN_DATA_NOT_FOUND,request.getRequestURI());
        ObjectMapper objectMapper = new ObjectMapper();
        response.setCharacterEncoding("UTF-8");
        response.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
        response.setStatus(errorResponse.getHttpStatus().value());
        response.getWriter().println(objectMapper.writeValueAsString(errorResponse));
    }
}
