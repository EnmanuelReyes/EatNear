package me.enmanuel.eatnear.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 10-Jan-17
 * Time: 10:58 AM
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class RestaurantVote implements Serializable {
    @EmbeddedId
    private RestaurantVotePK restaurantVoteId;

    private byte vote;

    public RestaurantVotePK getRestaurantVoteId() {
        return restaurantVoteId;
    }

    public void setRestaurantVoteId(RestaurantVotePK restaurantVoteId) {
        this.restaurantVoteId = restaurantVoteId;
    }

    public byte getVote() {
        return vote;
    }

    public void setVote(byte vote) {
        this.vote = vote;
    }
}

@Embeddable
class RestaurantVotePK implements Serializable {

    @ManyToOne
    private Restaurant restaurant;
    @ManyToOne
    private User user;

    public RestaurantVotePK(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantVotePK(Restaurant restaurant, User user) {
        this.restaurant = restaurant;
        this.user = user;
    }

}
