package me.enmanuel.eatnear.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 10-Jan-17
 * Time: 10:29 AM
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "redirect:/login";
    }
}
