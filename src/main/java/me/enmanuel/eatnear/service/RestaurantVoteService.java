package me.enmanuel.eatnear.service;

import me.enmanuel.eatnear.entity.Restaurant;
import me.enmanuel.eatnear.entity.RestaurantVote;
import me.enmanuel.eatnear.entity.User;
import me.enmanuel.eatnear.repository.RestaurantVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 26-Jan-17
 * Time: 9:42 AM
 */
@org.springframework.stereotype.Service
public class RestaurantVoteService implements Service<RestaurantVote> {
    @Autowired
    private RestaurantVoteRepository restaurantVoteRepository;

    @Autowired
    LikelyService likelyService;

    @Override
    public RestaurantVote findOne(Integer var1) {
        return null;
    }

    @Override
    public RestaurantVote save(final RestaurantVote var1) {
        RestaurantVote restaurantVote = getByUserAndRestaurant(var1);
        if (restaurantVote == null) {
            restaurantVote = var1;
        } else {
            restaurantVote.setVote(var1.getVote());
        }

        final RestaurantVote save = restaurantVoteRepository.save(restaurantVote);
        likelyService.buildModel();
        return save;

    }

    @Override
    public void delete(Integer var1) {

    }

    @Override
    public void delete(RestaurantVote entity) {

    }

    @Override
    public Iterable<RestaurantVote> findAll() {
        return null;
    }

    public RestaurantVote getByUserAndRestaurant(User user, Restaurant restaurant) {
        return restaurantVoteRepository.findByUserAndRestaurant(user, restaurant);
    }

    public RestaurantVote getByUserAndRestaurant(RestaurantVote restaurantVote) {
        return getByUserAndRestaurant(restaurantVote.getUser(), restaurantVote.getRestaurant());
    }
}
