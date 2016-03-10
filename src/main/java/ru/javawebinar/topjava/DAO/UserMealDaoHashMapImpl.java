package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by askandar on 06.03.16.
 */
public class UserMealDaoHashMapImpl implements UserMealDao {


    private Map<Long,UserMeal> mapUserMeals = new ConcurrentHashMap<>();

    @Override
    public synchronized List<UserMeal> findAll() {
        return new ArrayList<UserMeal>(mapUserMeals.values());
    }

    @Override
    public synchronized UserMeal findById(Long id) {
        return mapUserMeals.get(id);
    }

    @Override
    public synchronized  void update(Long id, UserMeal user) {

        if (mapUserMeals.containsKey(id)){
            mapUserMeals.remove(id);
            user.setId(id);
            mapUserMeals.put(id,user);
        } else add(user);
    }

    @Override
    public synchronized void add(UserMeal user) {

        if (user.getId() != null) {
            if (mapUserMeals.entrySet().size() == 0) {
                mapUserMeals.put(0L, user);
            } else {
                Long id = getMaxId() + 1;
                user.setId(id);
                mapUserMeals.put(id, user);
            }
        }

    }
    @Override
    public synchronized void delete(Long id) {
        mapUserMeals.remove(id);
    }

    private Long getMaxId (){
        if (mapUserMeals.entrySet().size() !=0 ) {
            return Collections.max(mapUserMeals.keySet());
        }
        else return 0L;
    }
}
