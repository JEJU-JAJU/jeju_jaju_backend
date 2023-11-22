package com.jejujaju.user.model.service;

import com.jejujaju.user.model.dto.User;
import com.jejujaju.user.model.dto.UserBasicDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Long join(User user);

    boolean checkPwd(String password, String savedPwd);

    void modifyUser(User user);

    void modifyUserPwd(Long userId, String password);

    void deleteUser(Long userId);

    boolean isExistLoginId(String loginId);

    UserBasicDto findUserByUserId(Long userId);
}
