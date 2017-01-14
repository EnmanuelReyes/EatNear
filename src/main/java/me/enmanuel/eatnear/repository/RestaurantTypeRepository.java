package me.enmanuel.eatnear.repository;

import me.enmanuel.eatnear.entity.RestaurantType;
import me.enmanuel.eatnear.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel
 * Date: 14/01/2017
 * Time: 09:27 AM
 */
public interface RestaurantTypeRepository extends CrudRepository<RestaurantType, Integer> {
}
