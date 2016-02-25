package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {


        // TODO return filtered list with correctly exceeded field

        Map<Integer, Long> map = new HashMap<>();

        mealList.stream()
                .collect(Collectors.groupingBy(foo -> foo.getDateTime().getDayOfYear() + foo.getDateTime().getDayOfYear(), Collectors.summarizingInt(UserMeal::getCalories)))
                .forEach((date,sum) -> map.put(date,sum.getSum()));

        List<UserMealWithExceed> userMealWithExceeds = mealList
                .stream()
                .filter(s -> s.getDateTime().getHour() > startTime.getHour() && s.getDateTime().getHour() < endTime.getHour())
                .filter(m -> m.getCalories() < caloriesPerDay)
                .map(userMeal ->
                        new UserMealWithExceed(
                                userMeal.getDateTime(),
                                userMeal.getDescription(),
                                userMeal.getCalories(),
                                isExceeded(map.get(userMeal.getDateTime().getDayOfYear()+userMeal.getDateTime().getDayOfYear()),caloriesPerDay)))
                .collect(Collectors.toList());


 /*       for (UserMealWithExceed userMealWithExceed : userMealWithExceeds) {
            System.out.println("Time: " + userMealWithExceed.getDateTime() +
                    "\tDescription: " + userMealWithExceed.getDescription() +
                    "\tCalories: " + userMealWithExceed.getDescription() +
                    "\tExceeded: "  + userMealWithExceed.isExceed());
        }*/

        return userMealWithExceeds;
    }

    private static boolean isExceeded(long day, int caloriesPerDay){
        return day > caloriesPerDay;
    }
}
