DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals(user_id, datetime, description, calories) VALUES
  (100000,'2016-03-22 10:00', 'Завтрак', 500),
  (100000,'2016-03-22 13:00', 'Обед', 1000),
  (100000,'2016-03-22 20:00','Ужин', 550)
;

