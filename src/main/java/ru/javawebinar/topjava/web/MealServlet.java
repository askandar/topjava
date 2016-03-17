package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.datetime.DateFormatter;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.mock.InMemoryUserMealRepositoryImpl;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.to.UserMealsUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private  UserMealRestController userMealRestController;

/*    private UserMealRepository repository;*/




    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
 /*       repository = new InMemoryUserMealRepositoryImpl();*/
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            userMealRestController = appCtx.getBean(UserMealRestController.class);
            userMealRestController.create(new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        UserMeal userMeal = new UserMeal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        LOG.info(userMeal.isNew() ? "Create {}" : "Update {}", userMeal);
        userMealRestController.create(userMeal);
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String filter = request.getParameter("toDate");
        if (action == null) {
            if (filter != null){
                String fromDate = request.getParameter("fromDate");
                String toDate = request.getParameter("toDate");
                String fromTime = request.getParameter("fromTime");
                String toTime = request.getParameter("toTime");
                request.setAttribute("fromDate",fromDate);
                request.setAttribute("toDate", toDate);
                request.setAttribute("fromTime", fromTime);
                request.setAttribute("toTime", toTime);
                System.out.println(fromDate + " " + fromTime);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime startLt =  LocalDateTime.parse(fromDate + " " + fromTime, formatter);
                LocalDateTime endLt = LocalDateTime.parse(toDate + " " + toTime, formatter);
                request.setAttribute("mealList",
                        UserMealsUtil.getFilteredWithExceeded(userMealRestController.getAll(),startLt,endLt, UserMealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/mealList.jsp").forward(request, response);
            }else {
                LOG.info("getAll ");
/*            request.setAttribute("mealList",
                    UserMealsUtil.getWithExceeded(repository.getAll(), UserMealsUtil.DEFAULT_CALORIES_PER_DAY));*/
                request.setAttribute("mealList",
                        UserMealsUtil.getWithExceeded(userMealRestController.getAll(), UserMealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/mealList.jsp").forward(request, response);
            }
        } else if (action.equals("delete")) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            userMealRestController.delete(id);
            response.sendRedirect("meals");
        }else {
            final UserMeal meal = action.equals("create") ?
                    new UserMeal(LocalDateTime.now(), "", 1000) :
                    userMealRestController.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
