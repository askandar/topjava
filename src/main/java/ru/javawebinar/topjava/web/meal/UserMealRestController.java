package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * GKislin
 * 06.03.2015.
 */

@Controller
public class UserMealRestController {

    private static final Logger LOG = getLogger(UserMealRestController.class);

    @Autowired
    private UserMealService service;

    public UserMeal create(UserMeal userMeal){
        LOG.info("create " + userMeal);
        return service.save(userMeal);
    }

    public List<UserMeal> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public UserMeal get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(UserMeal userMeal, int id) {
        userMeal.setId(id);
        LOG.info("update " + userMeal);
        service.update(userMeal);
    }

}
