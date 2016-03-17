package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by askandar on 13.03.16.
 */

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

    private Map<Integer, User> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    List<Integer> mealList = new ArrayList<>();

    @Override
    public User save(User user) {
/*        if (user.isNew()){
            user.setId(counter.getAndIncrement());
        }*/
        repository.put(user.getId(),user);
        return user;
    }


    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        User user = null;
        for (Map.Entry<Integer, User> entry : repository.entrySet()) {
            if (entry.getValue().getEmail().equalsIgnoreCase(email)){
                user = entry.getValue();
                break;
            }
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(repository.values());
    }

}
