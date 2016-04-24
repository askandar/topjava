package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@RestController
@RequestMapping(UserMealRestController.REST_MEAL_URL)
public class UserMealRestController extends AbstractUserMealController {

    public static final String REST_MEAL_URL = "/rest/meals";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserMeal get(@PathVariable("id") int id){
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        super.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody UserMeal userMeal, @PathVariable("id") int id){
        super.update(userMeal,id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserMeal> updateOrCreate(@RequestBody UserMeal userMeal){

        UserMeal created = super.create(userMeal);

        URI uriofNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_MEAL_URL+"/{id}")
                .buildAndExpand(created.getId()).toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriofNewResource);

        return new ResponseEntity<UserMeal>(created, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserMealWithExceed> getBetween( @RequestParam String sDate
            ,@RequestParam String eDate
            , @RequestParam String sTime
            , @RequestParam String eTime){

        LocalDate startDate = TimeUtil.parseLocalDate(sDate);
        LocalDate endDate = TimeUtil.parseLocalDate(eDate);
        LocalTime startTime = TimeUtil.parseLocalTime(sTime);
        LocalTime endTime = TimeUtil.parseLocalTime(eTime);


        return super.getBetween(startDate, startTime, endDate, endTime);
    }

}

/*

 */