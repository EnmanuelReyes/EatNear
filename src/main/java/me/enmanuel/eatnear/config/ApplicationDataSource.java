package me.enmanuel.eatnear.config;

import me.enmanuel.eatnear.entity.Restaurant;
import me.enmanuel.eatnear.entity.RestaurantType;
import me.enmanuel.eatnear.entity.RestaurantVote;
import me.enmanuel.eatnear.entity.User;
import me.enmanuel.eatnear.repository.RestaurantRepository;
import me.enmanuel.eatnear.repository.RestaurantTypeRepository;
import me.enmanuel.eatnear.repository.RestaurantVoteRepository;
import me.enmanuel.eatnear.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 10-Jan-17
 * Time: 10:22 AM
 */
@Configuration
@EnableJpaRepositories(basePackages = "me.enmanuel.eatnear.repository")
@EntityScan(basePackages = "me.enmanuel.eatnear.entity")
public class ApplicationDataSource {

    @Bean
    public CommandLineRunner initRestaurantTypes(RestaurantTypeRepository restaurantTypeRepository) {
        return (args) -> {
            restaurantTypeRepository.save(new RestaurantType(1, "Comida Rapida"));
            restaurantTypeRepository.save(new RestaurantType(2, "Comida Tailandesa"));
        };
    }

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository) {
        return (args) -> {
            userRepository.save(new User(1, "enma","koko"));
        };
    }

    @Bean
    public CommandLineRunner initRestaurants(RestaurantRepository restaurantRepository) {
        return (args) -> {
            Restaurant restaurant = new Restaurant(1);
            restaurant.setRestaurantType(new RestaurantType(1));
            restaurant.setName("Plus+");
            restaurant.setLatitude(18.473592853487055);
            restaurant.setLongitude(-69.91469261236489);
            restaurant = restaurantRepository.save(restaurant);
            Restaurant julieta = new Restaurant(2);
            julieta.setRestaurantType(new RestaurantType(1));
            julieta.setName("Julieta");
            julieta.setLatitude(18.47271493472734);
            julieta.setLongitude(-69.93943959474564);
            julieta = restaurantRepository.save(julieta);
        };
    }

    @Bean
    public CommandLineRunner initRestaurantVotes(RestaurantRepository restaurantRepository,RestaurantVoteRepository restaurantVoteRepository) {
        return (args) -> {
            System.out.println(restaurantRepository.findOne(1).getRestaurantVotes());
            RestaurantVote restaurantVote = new RestaurantVote(new Restaurant(1), new User(1), (byte) 3);
            restaurantVoteRepository.save(restaurantVote);
        };
    }
}
