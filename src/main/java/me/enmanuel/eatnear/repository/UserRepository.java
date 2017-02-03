package me.enmanuel.eatnear.repository;

import me.enmanuel.eatnear.entity.Restaurant;
import me.enmanuel.eatnear.entity.RestaurantVote;
import me.enmanuel.eatnear.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 10-Jan-17
 * Time: 10:23 AM
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

}
