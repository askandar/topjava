package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository{

    @Autowired
    private ProxyUserMealRepository proxy;

    @PersistenceContext
    private EntityManager em;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        User ref = em.getReference(User.class, userId);
        userMeal.setUser(ref);
        if (userMeal.isNew()){
            proxy.save(userMeal);
            return userMeal;
        }else {
            return get(userMeal.getId(), userId) == null ? null : proxy.save(userMeal);
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.deleteByIdAndUser_Id(id,userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return proxy.getByIdAndUser_Id(id,userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.findAllByUser_IdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.getBetween(startDate,endDate,userId);
    }
}
