package me.enmanuel.eatnear.config;

import me.enmanuel.eatnear.entity.RestaurantType;
import me.enmanuel.eatnear.repository.RestaurantTypeRepository;
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
    public CommandLineRunner initJobTypes(RestaurantTypeRepository restaurantTypeRepository) {
        return (args) -> {
            restaurantTypeRepository.save(new RestaurantType(1, "Comida Rapida"));
            restaurantTypeRepository.save(new RestaurantType(2, "Comida Tailandesa"));
        };
    }
}
