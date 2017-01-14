package me.enmanuel.eatnear.service;

import me.enmanuel.eatnear.entity.Restaurant;
import me.enmanuel.eatnear.entity.User;
import me.enmanuel.eatnear.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel
 * Date: 14/01/2017
 * Time: 09:30 AM
 */
@org.springframework.stereotype.Service
public class RestaurantService implements Service<Restaurant> {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant findOne(Integer var1) {
        return restaurantRepository.findOne(var1);
    }

    @Override
    public <S extends Restaurant> S save(S var1) {
        return restaurantRepository.save(var1);
    }

    @Override
    public void delete(Integer var1) {
        restaurantRepository.delete(var1);
    }

    @Override
    public void delete(Restaurant entity) {
        restaurantRepository.delete(entity);
    }

    @Override
    public Iterable<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
}
