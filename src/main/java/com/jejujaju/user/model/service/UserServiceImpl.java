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
    public boolean checkPwd(User user) throws Exception {
        return false;
    }

    @Override
    public User modifyUser(User user) throws Exception {
        return null;
    }

    @Override
    public int deleteUser(String userId) throws Exception {
        return 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.selectUserByLoginId(username);
    }
}
