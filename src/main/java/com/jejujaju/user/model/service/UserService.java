package com.jejujaju.user.model.service;

import com.jejujaju.config.jwt.JwtToken;
import com.jejujaju.user.model.dto.User;

public interface UserService {
    Long join(User user) throws Exception;
    JwtToken login(User user) throws Exception;
    boolean checkPwd(User user) throws Exception;
    User modifyUser(User user) throws Exception;
    int deleteUser(String userId) throws Exception;
}
