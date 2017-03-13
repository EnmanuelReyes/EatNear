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

import java.math.BigDecimal;

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
            userRepository.save(new User(2, "willis","koko"));
            userRepository.save(new User(3, "luis","koko"));
        };
    }

    @Bean
    public CommandLineRunner initRestaurants(RestaurantRepository restaurantRepository) {
        return (args) -> {
            Restaurant plus = new Restaurant(1);
            plus.setRestaurantType(new RestaurantType(1));
            plus.setName("Plus+");
            plus.setAddress("Atras de apec");
            plus.setFoodType("Rapida");
            plus.setMinPrice(BigDecimal.valueOf(100));
            plus.setMaxPrice(BigDecimal.valueOf(205));
            plus.setPhoneNumber("8094834309");
            plus.setLatitude(18.473592853487055);
            plus.setLongitude(-69.91469261236489);
            restaurantRepository.save(plus);


            Restaurant julieta = new Restaurant(2);
            julieta.setRestaurantType(new RestaurantType(1));
            julieta.setName("Julieta");
            julieta.setAddress("Atras de apec");
            julieta.setFoodType("Rapida");
            julieta.setMinPrice(BigDecimal.valueOf(100));
            julieta.setMaxPrice(BigDecimal.valueOf(205));
            julieta.setPhoneNumber("8094834309");julieta.setLatitude(18.47271493472734);
            julieta.setLongitude(-69.93943959474564);
            restaurantRepository.save(julieta);


            Restaurant apetito = new Restaurant(3);
            apetito.setRestaurantType(new RestaurantType(1));
            apetito.setName("Apetito");
            apetito.setAddress("Atras de apec");
            apetito.setFoodType("Rapida");
            apetito.setMinPrice(BigDecimal.valueOf(100));
            apetito.setMaxPrice(BigDecimal.valueOf(205));
            apetito.setPhoneNumber("8094834309");
            apetito.setLatitude(18.473920);
            apetito.setLongitude(-69.914804);
            restaurantRepository.save(apetito);


            Restaurant pizzahut= new Restaurant(4);
            pizzahut.setRestaurantType(new RestaurantType(1));
            pizzahut.setName("Pizza Hut");
            pizzahut.setAddress("Atras de apec");
            pizzahut.setFoodType("Rapida");
            pizzahut.setMinPrice(BigDecimal.valueOf(100));
            pizzahut.setMaxPrice(BigDecimal.valueOf(205));
            pizzahut.setPhoneNumber("8094834309");
            pizzahut.setLatitude(18.465403);
            pizzahut.setLongitude(-69.910037);
            restaurantRepository.save(pizzahut);
        };
    }

    @Bean
    public CommandLineRunner initRestaurantVotes(RestaurantRepository restaurantRepository,RestaurantVoteRepository restaurantVoteRepository) {
        return (args) -> {
            System.out.println(restaurantRepository.findOne(1).getRestaurantVotes());
            restaurantVoteRepository.save(new RestaurantVote(new Restaurant(1), new User(1), (byte) 1));
            restaurantVoteRepository.save(new RestaurantVote(new Restaurant(2), new User(1), (byte) 2));
            restaurantVoteRepository.save(new RestaurantVote(new Restaurant(3), new User(1), (byte) 3));
            restaurantVoteRepository.save(new RestaurantVote(new Restaurant(4), new User(1), (byte) 0));

            restaurantVoteRepository.save(new RestaurantVote(new Restaurant(1), new User(2), (byte) 4));
            restaurantVoteRepository.save(new RestaurantVote(new Restaurant(2), new User(2), (byte) 0));
            restaurantVoteRepository.save(new RestaurantVote(new Restaurant(3), new User(2), (byte) 5));
            restaurantVoteRepository.save(new RestaurantVote(new Restaurant(4), new User(2), (byte) 6));

            restaurantVoteRepository.save(new RestaurantVote(new Restaurant(1), new User(3), (byte) 7));
            restaurantVoteRepository.save(new RestaurantVote(new Restaurant(2), new User(3), (byte) 8));
            restaurantVoteRepository.save(new RestaurantVote(new Restaurant(3), new User(3), (byte) 0));
            restaurantVoteRepository.save(new RestaurantVote(new Restaurant(4), new User(3), (byte) 9));

        };
    }
}
