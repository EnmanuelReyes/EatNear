package me.enmanuel.eatnear.domain;

import me.enmanuel.eatnear.entity.Restaurant;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel
 * Date: 04/02/2017
 * Time: 09:41 AM
 */
public class Recommendation {
    private Restaurant restaurant;
    private double wtf;


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public double getWtf() {
        return wtf;
    }

    public void setWtf(double wtf) {
        this.wtf = wtf;
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "restaurant=" + restaurant +
                ", wtf=" + wtf +
                '}';
    }
}
