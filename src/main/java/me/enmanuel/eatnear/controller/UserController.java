package me.enmanuel.eatnear.controller;

import me.enmanuel.eatnear.domain.Recommendation;
import me.enmanuel.eatnear.entity.Restaurant;
import me.enmanuel.eatnear.entity.RestaurantVote;
import me.enmanuel.eatnear.entity.User;
import me.enmanuel.eatnear.service.LikelyService;
import me.enmanuel.eatnear.service.RestaurantService;
import me.enmanuel.eatnear.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
    public List<me.enmanuel.eatnear.domain.Restaurant> recommendations(Principal principal){

        User user = (User) ((OAuth2Authentication)principal).getPrincipal();
        List<me.enmanuel.eatnear.domain.Restaurant> restaurants = new ArrayList<>();

        final Recommendation recommendations = likelyService.recommendations(user);

        final Restaurant restaurant = recommendations.getRestaurant();
        restaurants.add(new me.enmanuel.eatnear.domain.Restaurant(restaurant, restaurant.getRestaurantVotes().stream().filter(z->z.getUser().equals(user)).findFirst()
                    .orElse(new RestaurantVote(null,null,(byte)0)).getVote()));
        return restaurants;
    }

}
