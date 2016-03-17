package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.meal.UserMealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            System.out.println(adminUserController.create(new User(1, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN)));
       /*     System.out.println(adminUserController.create(new User(10, "Askandar", "email", "password", Role.ROLE_USER)))*/;
            UserMealRestController userMealRestController = appCtx.getBean(UserMealRestController.class);

            userMealRestController.create(new UserMeal(LocalDateTime.of(2015, Month.MAY, 20, 20, 0), "Ужин", 510));
            System.out.println("-------------------------------------------");
            System.out.println(userMealRestController.getAll());
            System.out.println("-------------------------------------------");
            //System.out.println(adminUserController.getAll());
        }
    }
}
