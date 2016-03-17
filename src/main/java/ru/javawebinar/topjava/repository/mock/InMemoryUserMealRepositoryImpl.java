package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.to.UserMealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */

@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {

    private Map<Integer, Map<Integer,UserMeal>> repository = new ConcurrentHashMap<>();
    private Map<Integer, UserMeal> userMealsMap = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(this::save);
    }


    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        userMealsMap.put(userMeal.getId(), userMeal);
        repository.put(LoggedUser.id(), userMealsMap);
        return userMeal;
    }


    @Override
    public void delete(int id) {
        repository.get(LoggedUser.id()).remove(id);
    }

    @Override
    public UserMeal get(int id) {

        return repository.get(LoggedUser.id()).get(id);
    }

    @Override
    public Collection<UserMeal> getAll() {
        Comparator<UserMeal> comparator = Comparator.comparing(UserMeal::getDateTime);
        return repository.get(LoggedUser.id()).values()
                .stream().sorted((e1,e2) -> e1.getDateTime()
                        .compareTo(e2.getDateTime()))
                .collect(Collectors.toList());
    }
}

