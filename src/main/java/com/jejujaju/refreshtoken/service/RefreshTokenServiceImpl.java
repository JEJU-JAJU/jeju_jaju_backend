package com.jejujaju.refreshtoken.service;

import com.jejujaju.security.JwtTokenProvider;
import com.jejujaju.user.model.dto.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService{

    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtToken reCreateToken(String refreshTokenBody) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtTokenProvider.getUserLoginId(refreshTokenBody));
        if(userDetails == null){
            return null;
        }

        Long userId = jwtTokenProvider.getUserId(refreshTokenBody);
        String loginId = jwtTokenProvider.getUserLoginId(refreshTokenBody);

        String accessToken = jwtTokenProvider.createAccessToken(userId, loginId);
        String refreshToken = jwtTokenProvider.createRefreshToken(userId, loginId);

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken).build();
    }
}
