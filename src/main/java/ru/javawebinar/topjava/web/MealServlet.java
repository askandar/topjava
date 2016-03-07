package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by askandar on 05.03.16.
 */

public class MealServlet extends HttpServlet {


    private static String LIST = "/mealList.jsp";
    private static String EDIT_OR_ADD = "/user.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String forward = "";
        if (req.getParameter("action") != null) {

            String action = req.getParameter("action");
            if (action.equalsIgnoreCase("delete")) {
                Long id = Long.valueOf(req.getParameter("userId"));
                UserMealsUtil.usersMap.delete(id);
                forward = LIST;
            } else if (action.equalsIgnoreCase("edit")) {
                Long id = Long.valueOf(req.getParameter("userId"));
                UserMeal userMeal = UserMealsUtil.usersMap.findById(id);
                req.setAttribute("user", userMeal);
                forward = EDIT_OR_ADD;

            } else if (action.equalsIgnoreCase("add")) {
                forward = EDIT_OR_ADD;
            }
/*            else {

            }*/
        } else {
            forward = LIST;
        }


      /* List<UserMealWithExceed> users = UserMealsUtil.getFilteredMealsWithExceeded(UserMealsUtil.usersMap.findAll(), LocalTime.of(1, 0), LocalTime.of(23, 0), 2000);*/
        req.setAttribute("users", UserMealsUtil.getFilteredMealsWithExceeded(UserMealsUtil.usersMap.findAll(), LocalTime.of(1, 0), LocalTime.of(23, 0), 2000));
        req.getRequestDispatcher(forward).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        LocalTime time = LocalTime.parse(req.getParameter("time"));
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        if (req.getParameter("userId") !=null && !req.getParameter("userId").isEmpty()){
            Long id = Long.valueOf(req.getParameter("userId"));
         /*   if (UserMealsUtil.usersMap.findById(id) != null) {*/
                System.out.println(id);
                UserMealsUtil.usersMap.update(id, new UserMeal(dateTime, description, calories));
/*            }else {
                UserMeal userMeal = new UserMeal(dateTime, description, calories);
                UserMealsUtil.usersMap.add(userMeal);
            }*/
        }else {

            UserMeal userMeal = new UserMeal(dateTime, description, calories);
            UserMealsUtil.usersMap.add(userMeal);
        }


        req.setAttribute("users", UserMealsUtil.getFilteredMealsWithExceeded(UserMealsUtil.usersMap.findAll(), LocalTime.of(1, 0), LocalTime.of(23, 0), 2000));
        req.getRequestDispatcher(LIST).forward(req, resp);
    }
}
