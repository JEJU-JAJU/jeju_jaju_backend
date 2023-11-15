package com.jejujaju.user.model.service;

import com.jejujaju.config.jwt.JwtToken;
import com.jejujaju.config.jwt.JwtTokenProvider;
import com.jejujaju.user.model.dto.User;
import com.jejujaju.user.model.mapper.UserMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(UserMapper userMapper,
                           BCryptPasswordEncoder encoder,
                           AuthenticationManagerBuilder authenticationManagerBuilder,
                           JwtTokenProvider jwtTokenProvider) {
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Long join(User user) throws Exception {
        String encrypted = encoder.encode(user.getPassword());
        user.setPassword(encrypted);
        userMapper.insertUser(user);
        return user.getUserId();
    }

    @Override
    public JwtToken login(User user) throws Exception {
        // Authentication 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user.getLoginId(), user.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 검증된 인증 정보로 JWT 토큰 생성
        return jwtTokenProvider.generateToken(authentication);
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
