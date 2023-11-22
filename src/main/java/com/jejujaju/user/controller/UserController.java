package com.jejujaju.user.controller;

import com.jejujaju.user.model.dto.User;
import com.jejujaju.user.model.dto.UserBasicDto;
import com.jejujaju.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> join(@RequestBody User user) {
        Long userId = userService.join(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/my")
    public ResponseEntity<?> modifyUser(@AuthenticationPrincipal User user, @RequestBody User newUserInfo) {
        newUserInfo.setUserId(user.getUserId());
        userService.modifyUser(newUserInfo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/my/pwd")
    public ResponseEntity<?> modifyUserPwd(@AuthenticationPrincipal User user, @RequestBody Map<String, String> map) {
        String password = map.get("password");
        userService.modifyUserPwd(user.getUserId(), password);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/my")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal User user) {
        userService.deleteUser(user.getUserId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/my/pwd")
    public ResponseEntity<?> checkPwd(@AuthenticationPrincipal User user, @RequestBody Map<String, String> map) {
        String password = map.get("password");
        boolean result = userService.checkPwd(password, user.getPassword());
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> isExistId(@RequestParam(value = "id") String loginId) {
        boolean result = userService.isExistLoginId(loginId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/my")
    public ResponseEntity<UserBasicDto> findUser(@AuthenticationPrincipal User user) {
        UserBasicDto userBasicDto = userService.findUserByUserId(user.getUserId());
        return new ResponseEntity<>(userBasicDto, HttpStatus.OK);
    }
}
