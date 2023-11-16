package com.jejujaju.refreshtoken.controller;

import com.jejujaju.refreshtoken.service.RefreshTokenService;
import com.jejujaju.security.JwtTokenProvider;
import com.jejujaju.user.model.dto.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshTokens(@RequestBody Map<String,String> map, HttpServletResponse response) {
        try {
            String token = map.get("refreshToken");

            JwtToken newToken = refreshTokenService.reCreateToken(token);
            response.addHeader("access-token", newToken.getAccessToken());
            response.addHeader("refresh-token", newToken.getRefreshToken());

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e){
            return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
