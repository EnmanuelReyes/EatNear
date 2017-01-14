package me.enmanuel.eatnear.service;

import me.enmanuel.eatnear.entity.Restaurant;
import me.enmanuel.eatnear.entity.RestaurantType;
import me.enmanuel.eatnear.repository.RestaurantRepository;
import me.enmanuel.eatnear.repository.RestaurantTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel
 * Date: 14/01/2017
 * Time: 10:25 AM
 */
@org.springframework.stereotype.Service
public class RestaurantTypeService implements Service<RestaurantType> {
    @Autowired
    private RestaurantTypeRepository restaurantTypeRepository;

    @Override
    public RestaurantType findOne(Integer var1) {
        return null;
    }

    @Override
    public <S extends RestaurantType> S save(S var1) {
        return null;
    }

    @Override
    public void delete(Integer var1) {

    }

    @Override
    public void delete(RestaurantType entity) {

    }

    @Override
    public Iterable<RestaurantType> findAll() {
        return restaurantTypeRepository.findAll();
    }
}
