package me.enmanuel.eatnear.repository;

import me.enmanuel.eatnear.entity.Restaurant;
import me.enmanuel.eatnear.entity.RestaurantType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel
 * Date: 14/01/2017
 * Time: 09:27 AM
 */
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
}
