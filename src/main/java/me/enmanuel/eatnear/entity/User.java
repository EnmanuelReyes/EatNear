package me.enmanuel.eatnear.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 10-Jan-17
 * Time: 10:05 AM
 */
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String user;
    private String password;


}
