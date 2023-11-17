package com.jejujaju.user.model.service;

import com.jejujaju.user.model.dto.User;
import com.jejujaju.user.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    @Override
    public Long join(User user) {
        String encrypted = encoder.encode(user.getPassword());
        user.setPassword(encrypted);
        userMapper.insertUser(user);
        return user.getUserId();
    }

    @Override
    public boolean checkPwd(String password, String savedPwd) {
        return encoder.matches(password, savedPwd);
    }

    @Override
    public void modifyUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void modifyUserPwd(Long userId, String password) {
        String encrypted = encoder.encode(password);
        userMapper.updateUserPwd(userId, encrypted);
    }

    @Override
    public void deleteUser(Long userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    public boolean isExistLoginId(String loginId) {
        User user = userMapper.selectUserByLoginId(loginId);
        return user != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.selectUserByLoginId(username);
    }
}
