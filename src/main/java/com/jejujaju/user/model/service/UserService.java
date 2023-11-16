package com.jejujaju.user.model.service;

import com.jejujaju.user.model.dto.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Long join(User user) throws Exception;
    boolean checkPwd(Long userId, String password) throws Exception;
    int modifyUser(User user) throws Exception;
    int modifyUserPwd(Long userId, String password) throws Exception;
    int deleteUser(Long userId) throws Exception;
    boolean isExistLoginId(String loginId) throws Exception;
    User findUserByUserId(Long loginId) throws Exception;
}
