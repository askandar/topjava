package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {



    private static final class UserMealRowMapper implements RowMapper<UserMeal>{

        @Override
        public UserMeal mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserMeal userMeal = new UserMeal( rs.getTimestamp("datetime").toLocalDateTime(),rs.getString("description"), rs.getInt("calories"));
            userMeal.setId(rs.getInt("id"));
            return userMeal;
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcUserMealRepositoryImpl(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("MEALS").usingGeneratedKeyColumns("id");
    }


    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        MapSqlParameterSource map =  new MapSqlParameterSource()
                .addValue("id", userMeal.getId())
                .addValue("user_id", userId)
                .addValue("datetime", userMeal.getDateTime())
                .addValue("description",userMeal.getDescription())
                .addValue("calories",userMeal.getCalories());
        if (userMeal.isNew()){
            Number number = simpleJdbcInsert.executeAndReturnKey(map);
            userMeal.setId(number.intValue());
        }else {
            namedParameterJdbcTemplate.update
                    ("UPDATE  meals set datetime =:datetime, description =:description, calories=:calories WHERE id =:id and user_id =:user_id",map
            );
        }
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id = ? and user_id = ?",id,userId) !=0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return jdbcTemplate.queryForObject
                ("SELECT id, datetime, description, calories FROM meals WHERE id = ? AND user_id = ?",new UserMealRowMapper(), id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {

        return jdbcTemplate.query("SELECT id, datetime, description, calories FROM meals WHERE user_id = ? ORDER BY datetime DESC " , new UserMealRowMapper(), userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {

        return jdbcTemplate.query("SELECT id, datetime, description, calories FROM meals WHERE user_id = ? AND datetime BETWEEN ? AND ? ORDER BY datetime DESC " ,
                new UserMealRowMapper(), userId, Timestamp.valueOf(startDate),Timestamp.valueOf(endDate));

    }
}
