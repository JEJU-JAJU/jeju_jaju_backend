package com.jejujaju.user.controller;

import com.jejujaju.user.model.dto.User;
import com.jejujaju.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> join(@RequestBody User user){
        try{
            Long userId = userService.join(user);
            return ResponseEntity.ok(userId);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PutMapping("/{user-id}")
    public ResponseEntity<?> modifyUser(@PathVariable("user-id") Long userId, @RequestBody User user){
        try{
            user.setUserId(userId);
            userService.modifyUser(user);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PutMapping("/{user-id}/pwd")
    public ResponseEntity<?> modifyUserPwd(@PathVariable("user-id") Long userId, @RequestBody Map<String,String> map){
        try{
            String password = map.get("password");
            userService.modifyUserPwd(userId, password);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user-id") Long userId){
        try{
            userService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @PostMapping("/{user-id}/pwd")
    public ResponseEntity<?> checkPwd(@PathVariable("user-id") Long userId, @RequestBody Map<String,String> map){
        try{
            String password = map.get("password");
            boolean result = userService.checkPwd(userId, password);
            return ResponseEntity.ok(result);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> isExistId(@RequestParam(value = "id") String loginId){
        try{
            boolean result = userService.isExistLoginId(loginId);
            return ResponseEntity.ok(result);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<?> findUser(@PathVariable("user-id") Long userId){
        try{
            User user = userService.findUserByUserId(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
