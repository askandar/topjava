package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by askandar on 05.04.16.
 */

@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {
    @Override
    List<UserMeal> findAll(Sort sort);

    List<UserMeal> findAllByUser_IdOrderByDateTimeDesc(Integer userId);


    UserMeal getByIdAndUser_Id(int id, int userId);

    @Transactional
    int deleteByIdAndUser_Id(int id, int userId);

    @Override
    @Transactional
    UserMeal save(UserMeal userMeal);

    @Modifying
    @Query("SELECT m FROM UserMeal m  WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<UserMeal> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

}
