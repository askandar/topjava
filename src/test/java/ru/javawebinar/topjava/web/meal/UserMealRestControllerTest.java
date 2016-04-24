package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
/**
 * Created by askandar on 19.04.16.
 */
public class UserMealRestControllerTest extends AbstractControllerTest {

    public static final String REST_URL = UserMealRestController.REST_MEAL_URL + '/';

    @Test
    public void testGetAll() throws Exception {
        List<UserMeal> meals = (List<UserMeal>) userMealService.getAll(USER_ID);
        TestUtil.print(
        mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(USER_MEALS)))

        ;
        //MATCHER.assertCollectionEquals(USER_MEALS,userMealService.getAll(USER_ID));

    }

    /*
            TestUtil.print(mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(ADMIN, USER)));
     */
    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL+ MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));

    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL+ MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2), userMealService.getAll(USER_ID));

    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = getUpdated();
        mockMvc.perform(put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated,userMealService.get(MEAL1_ID,USER_ID));

    }

    @Test
    public void testUpdateOrCreate() throws Exception {
        UserMeal userMeal = getCreated();
        ResultActions actions = mockMvc.perform(post(REST_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtil.writeValue(userMeal)))
                .andExpect(status().isCreated());
        UserMeal returned = MATCHER.fromJsonAction(actions);
        userMeal.setId(returned.getId());

        MATCHER.assertEquals(userMeal,returned);
        MATCHER.assertCollectionEquals(Arrays.asList(userMeal, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), userMealService.getAll(USER_ID));
    }


    @Test
    public void testGetBetween() throws Exception {

    }
    /*
            MATCHER.assertCollectionEquals(Arrays.asList(MEAL3, MEAL2, MEAL1),
                service.getBetweenDates(LocalDate.of(2015, Month.MAY, 30), LocalDate.of(2015, Month.MAY, 30), USER_ID));
     */
}