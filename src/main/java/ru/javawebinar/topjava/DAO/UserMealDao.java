package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * Created by askandar on 06.03.16.
 */
public interface UserMealDao {
    List<UserMeal> findAll();
    UserMeal findById(Long id);
    void update(Long id, UserMeal user);
    void add(UserMeal user);
    void delete(Long id);
}
