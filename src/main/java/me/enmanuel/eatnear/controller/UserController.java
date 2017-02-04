package me.enmanuel.eatnear.controller;

import me.enmanuel.eatnear.domain.Recommendation;
import me.enmanuel.eatnear.entity.Restaurant;
import me.enmanuel.eatnear.entity.User;
import me.enmanuel.eatnear.service.LikelyService;
import me.enmanuel.eatnear.service.RestaurantService;
import me.enmanuel.eatnear.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 25-Jan-17
 * Time: 4:01 PM
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    LikelyService likelyService;

    @Autowired
    RestaurantService restaurantService;
    @PostMapping("/user")
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/user/recommendation")
    public Recommendation recommendation(Principal principal){

        User user = (User) ((OAuth2Authentication)principal).getPrincipal();

        final Recommendation recommendations = likelyService.recommendations(user);
        return recommendations;
    }

}
