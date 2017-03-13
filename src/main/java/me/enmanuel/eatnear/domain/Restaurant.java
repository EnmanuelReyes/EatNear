package me.enmanuel.eatnear.domain;

/**
 * Created by enmanuelreyes on 3/12/17.
 */
public class Restaurant {
    private me.enmanuel.eatnear.entity.Restaurant restaurant;
    private byte vote;

    public Restaurant(me.enmanuel.eatnear.entity.Restaurant restaurant, byte vote) {
        this.restaurant = restaurant;
        this.vote = vote;
    }

    public me.enmanuel.eatnear.entity.Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(me.enmanuel.eatnear.entity.Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public byte getVote() {
        return vote;
    }

    public void setVote(byte vote) {
        this.vote = vote;
    }
}
