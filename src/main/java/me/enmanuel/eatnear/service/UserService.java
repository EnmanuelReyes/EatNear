package me.enmanuel.eatnear.service;

import me.enmanuel.eatnear.entity.User;
import me.enmanuel.eatnear.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Enmanuel Reyes
 * Date: 26-Oct-16
 * Time: 2:50 PM
 */
@org.springframework.stereotype.Service
public class UserService implements Service<User> {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findOne(Integer var1) {
        return userRepository.findOne(var1);
    }

    @Override
    public void delete(Integer var1) {
        userRepository.delete(var1);
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
