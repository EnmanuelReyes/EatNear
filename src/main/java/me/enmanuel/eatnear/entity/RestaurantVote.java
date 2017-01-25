package me.enmanuel.eatnear.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id_restaurant", "id_user"})})
public class RestaurantVote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", referencedColumnName = "id_restaurant", nullable = false)
    @JsonBackReference
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    @JsonBackReference
    private User user;

    private byte vote;

    public RestaurantVote() {
    }

    public RestaurantVote(Restaurant restaurant, User user, byte vote) {
        this.restaurant = restaurant;
        this.user = user;
        this.vote = vote;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte getVote() {
        return vote;
    }

    public void setVote(byte vote) {
        this.vote = vote;
    }
}


