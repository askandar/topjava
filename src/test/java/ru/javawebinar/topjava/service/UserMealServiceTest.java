package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;
import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by askandar on 22.03.16.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }


    @Test
    public void testGet() throws Exception {
        UserMeal userMeal = service.get(100002, USER_ID);
        UserMeal userMeal1 = MealTestData.getById(100002, USER_ID);
        MATCHER.assertEquals(userMeal,userMeal1);
    }

    @Test
    public void testGetNotUser() throws Exception {
        UserMeal userMeal = service.get(100002, USER_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(100002, USER_ID);
    }



    @Test(expected = NotFoundException.class)
    public void testDeleteNotUser() throws Exception {
        service.delete(100002, ADMIN_ID);


    }

    @Test
    public void testGetBetweenDates() throws Exception {

    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

        Collection<UserMeal> all = service.getAll(USER_ID);
        Collection<UserMeal> local = MealTestData.getAll(USER_ID);
        MATCHER.assertCollectionEquals(local, all);

    }


    @Test(expected = DataAccessException.class)
    public void testUpdateNotUser() throws Exception {
        service.get(100002,ADMIN_ID);
    }

    @Test
    public void testSave() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        UserMeal userMeal = new UserMeal(100005, LocalDateTime.parse("2016-03-23 10:00", formatter),"Завтрак",500);
        userMeal.setId(null);
        service.save(userMeal, USER_ID);
        UserMeal userMeal1 = service.get(100005,USER_ID);
        MATCHER.assertEquals(userMeal,userMeal1);
    }

    @Test(expected = NotFoundException.class)
    public void testSaveNotUser() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
/*        if (ADMIN_ID != USER_ID){
            throw new NotFoundException("");
        }*/
        service.update(new UserMeal(100002, LocalDateTime.parse("2016-03-22 10:00", formatter),"Завтрак",500), ADMIN_ID);
    }
}
