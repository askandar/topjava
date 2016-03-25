package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;


/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;


    private static Map<Integer, Map<Integer,UserMeal>> repository = new ConcurrentHashMap<>();
    private static Map<Integer,UserMeal> userMealMap = new ConcurrentHashMap<>();

    static {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        userMealMap.put(100002,new UserMeal(100002, LocalDateTime.parse("2016-03-22 10:00", formatter),"Завтрак",500));
        userMealMap.put(100003,new UserMeal(100003,LocalDateTime.parse("2016-03-22 13:00", formatter),"Обед",1000));
        userMealMap.put(100004,new UserMeal(100004,LocalDateTime.parse("2016-03-22 20:00", formatter),"Ужин",550));
        repository.put(USER_ID,userMealMap);

    }



    public static UserMeal getById(int id, int user_id){
        return repository.get(user_id).get(id);
    }

    public static Collection<UserMeal> getAll(int user_id){

        Comparator<UserMeal> comparator = Comparator.comparing(UserMeal::getDateTime);
        return repository.get(USER_ID).values()
                .stream().sorted((e2,e1) -> e1.getDateTime()
                        .compareTo(e2.getDateTime()))
                .collect(Collectors.toList());

    }

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
