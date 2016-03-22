package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;


    private Map<Integer, Map<Integer,UserMeal>> repository = new ConcurrentHashMap<>();
    Map<Integer,UserMeal> userMealMap = new ConcurrentHashMap<>();

    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        userMealMap.put(100002,new UserMeal(LocalDateTime.parse("2016-03-22 10:00", formatter),"Завтрак",500));
        userMealMap.put(100002,new UserMeal(LocalDateTime.parse("2016-03-22 10:00", formatter),"Обед",1000));
        userMealMap.put(100002,new UserMeal(LocalDateTime.parse("2016-03-22 10:00", formatter),"Ужин",550));
        repository.put(USER_ID,userMealMap);

    }

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}

/*
  (100000,'2016-03-22 10:00', 'Завтрак', 500),
  (100000,'2016-03-22 13:00', 'Обед', 1000),
  (100000,'2016-03-22 20:00','Ужин', 550)
 */
