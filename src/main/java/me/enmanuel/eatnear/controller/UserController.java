package me.enmanuel.eatnear.controller;

import me.enmanuel.eatnear.entity.User;
import me.enmanuel.eatnear.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 25-Jan-17
 * Time: 4:01 PM
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User create(User user){
        return userService.save(user);
    }

}
