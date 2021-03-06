package me.enmanuel.eatnear.controller;

import me.enmanuel.eatnear.domain.Geolocalizable;
import me.enmanuel.eatnear.entity.Restaurant;
import me.enmanuel.eatnear.entity.RestaurantVote;
import me.enmanuel.eatnear.entity.User;
import me.enmanuel.eatnear.service.RestaurantService;
import me.enmanuel.eatnear.service.RestaurantTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel
 * Date: 14/01/2017
 * Time: 09:30 AM
 */
@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantTypeService restaurantTypeService;

    @GetMapping("/restaurants")
    public ModelAndView restaurants(ModelAndView modelAndView) {
        attachRestaurants(modelAndView.getModelMap());
        modelAndView.setViewName("restaurants");
        return modelAndView;
    }


    @PostMapping(value = "/restaurant")
    public ModelAndView save(ModelAndView modelAndView, Restaurant restaurant, RedirectAttributes redirectAttributes) {
        restaurantService.save(restaurant);
        redirectAttributes.addFlashAttribute("success", "El restaurante fue guardada correctamente");
        modelAndView.setViewName("redirect:/restaurants");
        return modelAndView;
    }


    @RequestMapping(value = "/restaurant/{restaurantId}")
    public ModelAndView edit(ModelAndView modelAndView, @PathVariable("restaurantId") Restaurant restaurant) {

        modelAndView.setViewName("restaurant");
        modelAndView.addObject("restaurant", restaurant);
        attachRestaurants(modelAndView.getModelMap());
        attachRestaurantTypes(modelAndView.getModelMap());
        return modelAndView;
    }


    @RequestMapping(value = "/restaurant/create")
    public ModelAndView create(ModelAndView modelAndView) {

        modelAndView.setViewName("restaurant");
        modelAndView.addObject("restaurant", new Restaurant());
        attachRestaurants(modelAndView.getModelMap());
        attachRestaurantTypes(modelAndView.getModelMap());
        return modelAndView;
    }


    @RequestMapping(value = "/restaurant/delete/{restaurantId}")
    public ModelAndView delete(ModelAndView modelAndView, @PathVariable Integer restaurantId,
                               RedirectAttributes redirectAttributes) {
        restaurantService.delete(restaurantId);
        redirectAttributes.addFlashAttribute("success", "El restaurante fue eliminado correctamente");
        modelAndView.setViewName("redirect:/restaurants");
        return modelAndView;
    }

    private void attachRestaurants(ModelMap modelMap) {
        modelMap.addAttribute("restaurants", restaurantService.findAll());
    }

    private void attachRestaurantTypes(ModelMap modelMap) {
        modelMap.addAttribute("restaurantTypes", restaurantTypeService.findAll());
    }


    @GetMapping(value = "/api/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<me.enmanuel.eatnear.domain.Restaurant> restaurants(Principal principal, @RequestParam(required = false) Double latitude,
                                                                   @RequestParam(required = false) Double longitude) {
        final User user =  (User) ((OAuth2Authentication)principal).getPrincipal();
        List<Restaurant> restaurantList;
        List<me.enmanuel.eatnear.domain.Restaurant> restaurants = new ArrayList<>();

        if (latitude == null || longitude == null)
            restaurantList = restaurantService.findAll();
        else
            restaurantList = restaurantService.findRestaurants(new Geolocalizable() {
                @Override
                public Double getLatitude() {
                    return latitude;
                }

                @Override
                public Double getLongitude() {
                    return longitude;
                }
            });

        restaurantList.forEach(x-> {
            restaurants.add(new me.enmanuel.eatnear.domain.Restaurant(x, x.getRestaurantVotes().stream().filter(z->z.getUser().equals(user)).findFirst()
                    .orElse(new RestaurantVote(null,null,(byte)0)).getVote()));
        });

        return restaurants;
    }


}
