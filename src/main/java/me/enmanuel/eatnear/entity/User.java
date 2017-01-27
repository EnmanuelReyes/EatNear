package me.enmanuel.eatnear.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 10-Jan-17
 * Time: 10:05 AM
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Integer id;

    @Column(unique = true)
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Email
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value ="secondParent")
    private List<RestaurantVote> restaurantVotes;

    public User(Integer id, String username, String password) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RestaurantVote> getRestaurantVotes() {
        return restaurantVotes;
    }

    public void setRestaurantVotes(List<RestaurantVote> restaurantVotes) {
        this.restaurantVotes = restaurantVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
