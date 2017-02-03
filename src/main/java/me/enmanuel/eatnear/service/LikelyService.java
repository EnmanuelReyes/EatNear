package me.enmanuel.eatnear.service;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import me.enmanuel.eatnear.entity.Restaurant;
import me.enmanuel.eatnear.entity.RestaurantVote;
import me.enmanuel.eatnear.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 13-Jan-17
 * Time: 3:11 PM
 */
@org.springframework.stereotype.Service
public class LikelyService {
    private Invocable engine;
    ScriptObjectMirror model;

    @Autowired
    RestaurantService restaurantService;
    @Autowired
    UserService userService;

    public LikelyService() {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            engine.eval(new FileReader("sylvester.src.js"));
            engine.eval(new FileReader("likely.js"));

            this.engine = (Invocable) engine;
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ScriptObjectMirror buildModel(List<String> rowLabels, List<String> colLabels, List<List<Integer>> inputMatrix)
            throws ScriptException, NoSuchMethodException {
        model = (ScriptObjectMirror) engine.invokeFunction("buildModel", inputMatrix, rowLabels, colLabels);
        return model;
    }

    public ScriptObjectMirror buildModel() {

        List<Restaurant> restaurants = (List<Restaurant>) restaurantService.findAll();
        List<User> users = (List<User>) userService.findAll();
        List<String> usersId = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        List<String> restaurantsId = restaurants.stream().map(x -> x.getId().toString()).collect(Collectors.toList());

        List<List<Integer>> restaurantVotes = new ArrayList<>();

        for (User user : users) {
            List<Integer> userVotes = new ArrayList<>();
            for (Restaurant restaurant : restaurants) {
                Optional<RestaurantVote> restaurantVote;
                if (user.getRestaurantVotes() == null) {
                    restaurantVote = Optional.empty();
                } else {
                    restaurantVote = user.getRestaurantVotes().stream()
                            .filter(x -> x.getRestaurant().equals(restaurant)).findFirst();
                }
                if (restaurantVote.isPresent()) {
                    userVotes.add((int) restaurantVote.get().getVote());
                } else {
                    userVotes.add(0);
                }
            }
            restaurantVotes.add(userVotes);
        }


        try {
            return buildModel(usersId, restaurantsId, restaurantVotes);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ScriptObjectMirror rankAllItems(ScriptObjectMirror result, Integer id) {
        ScriptObjectMirror rankedAllItems = (ScriptObjectMirror) result.callMember("rankAllItems", id.toString());
        return rankedAllItems;
    }

    public ScriptObjectMirror recommendations(ScriptObjectMirror result, String name) {
        ScriptObjectMirror rankedAllItems = (ScriptObjectMirror) result.callMember("recommendations", name);
        return rankedAllItems;
    }

    public ScriptObjectMirror recommendations(String name) {
        return recommendations(model, name);
    }

    public ScriptObjectMirror recommendations(User user) {
        return recommendations(String.valueOf(user.getId()));
    }
}
