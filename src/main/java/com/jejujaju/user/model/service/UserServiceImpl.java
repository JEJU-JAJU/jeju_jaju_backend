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
        public Long join(User user) throws Exception {
            String encrypted = encoder.encode(user.getPassword());
            user.setPassword(encrypted);
            userMapper.insertUser(user);
            return user.getUserId();
        }

        @Override
        public boolean checkPwd(Long userId, String password) throws Exception {
            User savedUser = userMapper.selectUserByUserId(userId);
            return encoder.matches(password, savedUser.getPassword());
        }

        @Override
        public int modifyUser(User user) throws Exception {
            return userMapper.updateUser(user);
        }

        @Override
        public int modifyUserPwd(Long userId, String password) throws Exception {
            String encrypted = encoder.encode(password);
            return userMapper.updateUserPwd(userId, encrypted);
        }

        @Override
        public int deleteUser(Long userId) throws Exception {
            return userMapper.deleteUser(userId);
        }

        @Override
        public boolean isExistLoginId(String loginId) throws Exception {
            User user = userMapper.selectUserByLoginId(loginId);
            return user != null;
        }

        @Override
        public User findUserByUserId(Long userId) throws Exception {
            return userMapper.selectUserByUserId(userId);
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userMapper.selectUserByLoginId(username);
        }
    }
