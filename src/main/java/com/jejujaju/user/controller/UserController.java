package com.jejujaju.user.controller;

import com.jejujaju.config.jwt.JwtToken;
import com.jejujaju.user.model.dto.User;
import com.jejujaju.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> join(@RequestBody User user){
        try{
            Long userId = userService.join(user);
            return ResponseEntity.ok(userId);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            if(user.getLoginId() == null || user.getPassword() == null){
                return new ResponseEntity<String>("아이디 또는 비밀번호를 입력해주세요.", HttpStatus.BAD_REQUEST);
            }

            JwtToken token = userService.login(user);
            return ResponseEntity.ok(token);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        // e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
