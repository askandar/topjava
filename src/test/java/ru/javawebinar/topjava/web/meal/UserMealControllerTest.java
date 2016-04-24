package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.MEAL1;


/**
 * Created by askandar on 18.04.16.
 */
public class UserMealControllerTest extends AbstractControllerTest {

    @Test
    public void testUserMealList() throws Exception{
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"))
                .andExpect(model().attribute("mealList",hasSize(6)))
                .andExpect(model().attribute("mealList",hasItem(
                        allOf(
                                hasProperty("id", is(MEAL1.getId())),
                                hasProperty("description", is(MEAL1.getDescription())),
                                hasProperty("calories",is(MEAL1.getCalories()))
                        )
                )))
                ;
    }
}

