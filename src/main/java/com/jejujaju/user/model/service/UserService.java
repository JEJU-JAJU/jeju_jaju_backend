package com.jejujaju.user.model.service;

import com.jejujaju.user.model.dto.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Long join(User user) throws Exception;
    boolean checkPwd(User user) throws Exception;
    User modifyUser(User user) throws Exception;
    int deleteUser(String userId) throws Exception;
}
