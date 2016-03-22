package ru.javawebinar.topjava.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;

import java.util.Collection;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

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

    }

    @Test
    public void testDelete() throws Exception {

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
        /*
        * MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER), all);*/

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testSave() throws Exception {

    }
}