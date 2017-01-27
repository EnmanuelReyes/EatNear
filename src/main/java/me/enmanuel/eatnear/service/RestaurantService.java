package me.enmanuel.eatnear.service;

import me.enmanuel.eatnear.domain.Geolocalizable;
import me.enmanuel.eatnear.entity.Restaurant;
import me.enmanuel.eatnear.entity.User;
import me.enmanuel.eatnear.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel
 * Date: 14/01/2017
 * Time: 09:30 AM
 */
@org.springframework.stereotype.Service
public class RestaurantService implements Service<Restaurant> {
    @Autowired
    LikelyService likelyService;
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

    public List<Restaurant> findRestaurants(final Geolocalizable geolocalizable) {
        List<Restaurant> list = new ArrayList<>();
        findAll().iterator().forEachRemaining(list::add);
        return list.stream().filter(x->withinRadius(x,geolocalizable)).collect(Collectors.toList());
    }

    public boolean withinRadius(Geolocalizable oldGeolocalizable, Geolocalizable newGeolocalizable) {
        return withinRadius(oldGeolocalizable,newGeolocalizable, (byte) 1);
    }

    public boolean withinRadius(Geolocalizable oldGeolocalizable, Geolocalizable newGeolocalizable,
                                byte kms) {
        if (!oldGeolocalizable.isValid() || !newGeolocalizable.isValid()) return false;
        final short R = 6371;
        Function<Double, Double> deg2rad = (n) -> Math.tan(n * (Math.PI / 180));

        double dLat = deg2rad.apply(newGeolocalizable.getLatitude() - oldGeolocalizable.getLatitude());
        double dLon = deg2rad.apply(newGeolocalizable.getLongitude() - oldGeolocalizable.getLongitude());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(deg2rad.apply(oldGeolocalizable.getLatitude())) *
                        Math.cos(deg2rad.apply(newGeolocalizable.getLatitude())) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double d = R * c;

        return (d <= kms);
    }
}
