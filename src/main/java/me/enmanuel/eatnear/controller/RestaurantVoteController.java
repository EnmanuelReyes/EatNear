package me.enmanuel.eatnear.controller;

import me.enmanuel.eatnear.entity.RestaurantVote;
import me.enmanuel.eatnear.service.RestaurantVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 26-Jan-17
 * Time: 9:41 AM
 */
@RestController
@RequestMapping("/api")
public class RestaurantVoteController {

    @Autowired
    private RestaurantVoteService restaurantVoteService;

    @PostMapping(value = "/restaurantvote")
    @ResponseStatus(HttpStatus.OK)
    public void vote(@RequestBody RestaurantVote restaurantVote) {
        restaurantVoteService.save(restaurantVote);
    }
}
