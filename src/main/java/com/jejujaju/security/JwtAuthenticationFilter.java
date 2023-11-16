package com.jejujaju.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jejujaju.user.model.dto.User;
import com.jejujaju.user.model.dto.UserAccessRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("[JwtAuthenticationFilter] - attemptAuthentication 시작");

        ObjectMapper mapper = new ObjectMapper();
        UserAccessRequestDto userDto = null;
        try {
            userDto = mapper.readValue(request.getInputStream(), UserAccessRequestDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        if(!LoginValidator.validateLogin(dto.getLoginId(), dto.getPassword())){
//            throw new LoginValidateErrorException();
//        }
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(userDto.getLoginId(), userDto.getPassword());
        return authenticationManager.authenticate(authenticationToken);
    }

    // jwt 토큰을 만들어서 response로 전달해줘서 유저가 받아서 사용할 수 있게끔 한다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("[JwtAuthenticationFilter] - successfulAuthentication 시작");
        User user = (User) authResult.getPrincipal();
        log.info("[JwtAuthenticationFilter] - User = {}", user);
        String accessToken = jwtTokenProvider.createAccessToken(user.getUserId(), user.getLoginId());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId(), user.getLoginId());

        response.addHeader("Access-Token", accessToken);
        response.addHeader("Refresh-Token", refreshToken);
        response.setStatus(201);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("[JwtAuthenticationFilter] - 인증실패");
        authenticationFailureHandler.onAuthenticationFailure(request,response,failed);
    }
}
