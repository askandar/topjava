package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * GKislin
 * 11.01.2015.
 */
@NamedQueries({
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal m WHERE m.id = :id" ),
        @NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT m from UserMeal m  where m.user.id = :user_id order by m.dateTime desc "),
        @NamedQuery(name = UserMeal.GET_BY_ID, query = "select m from UserMeal m where m.id = :id and m.user.id = :user_id"),
        @NamedQuery(name = UserMeal.GET_FILTERED, query =
                "select s from UserMeal s where s.user.id = :userid and s.dateTime between  :startTime and  :endTime order by s.dateTime desc")
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = "date_time", name = "meals_unique_user_datetime_idx")})
public class UserMeal extends BaseEntity {

    public static final String DELETE = "meals.delete";
    public static final String ALL_SORTED = "UserMeal.getAllSorted";
    public static final String GET_BY_ID = "UserMeal.getByID";
    public static final String GET_FILTERED = "UserMeal.beetween";


    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "description")
    private String description;

    @Column(name = "calories")
    protected int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public UserMeal() {
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }
    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

    @Converter(autoApply = true)
    public static class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

        @Override
        public java.sql.Timestamp convertToDatabaseColumn(java.time.LocalDateTime attribute) {
            return attribute == null ? null : java.sql.Timestamp.valueOf(attribute);
        }

        @Override
        public java.time.LocalDateTime convertToEntityAttribute(java.sql.Timestamp dbData) {
            return dbData == null ? null : dbData.toLocalDateTime();
        }
    }

}
