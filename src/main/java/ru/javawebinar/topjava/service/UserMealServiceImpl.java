package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Qualifier("inMemoryUserMealRepositoryImpl")
    @Autowired
    private UserMealRepository repository;


    @Override
    public UserMeal save(UserMeal userMeal) {
        return repository.save(userMeal);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);

    }

    @Override
    public UserMeal get(int id) throws NotFoundException {
        return repository.get(id);
    }

    @Override
    public List<UserMeal> getAll() {
        return (List<UserMeal>) repository.getAll();
    }

    @Override
    public void update(UserMeal userMeal) {
        repository.save(userMeal);
    }
}
