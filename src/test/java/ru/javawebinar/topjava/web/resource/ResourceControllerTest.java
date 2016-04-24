package ru.javawebinar.topjava.web.resource;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by askandar on 18.04.16.
 */
public class ResourceControllerTest extends AbstractControllerTest {

    @Test
    public void cssTest() throws Exception {
        mockMvc.perform(get("/resources/css/style.css"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/css"))
                ;
    }
}
