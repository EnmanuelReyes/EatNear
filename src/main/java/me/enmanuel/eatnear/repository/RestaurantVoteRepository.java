package me.enmanuel.eatnear.repository;

import me.enmanuel.eatnear.entity.Restaurant;
import me.enmanuel.eatnear.entity.RestaurantType;
import me.enmanuel.eatnear.entity.RestaurantVote;
import me.enmanuel.eatnear.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 18-Jan-17
 * Time: 11:45 AM
 */
public interface RestaurantVoteRepository extends CrudRepository<RestaurantVote, Integer> {
    RestaurantVote findByUserAndRestaurant(User user, Restaurant restaurant);

}
