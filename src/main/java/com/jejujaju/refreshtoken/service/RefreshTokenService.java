package com.jejujaju.refreshtoken.service;

import com.jejujaju.user.model.dto.JwtToken;

public interface RefreshTokenService {
    JwtToken reCreateToken(String refreshTokenBody);
}
