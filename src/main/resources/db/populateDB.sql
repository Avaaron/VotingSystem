DELETE FROM vote;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM dish;
DELETE FROM menu;
DELETE FROM restaurant;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('Admin1', 'admin1@gmail.com', '{noop}admin'),
  ('Admin2', 'admin2@gmail.com', '{noop}admin'),
  ('User1', 'user1@yandex.ru', '{noop}password'),
  ('User2', 'user2@yandex.ru', '{noop}password'),
  ('User3', 'user3@yandex.ru', '{noop}password');



INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_ADMIN', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_USER', 100003),
  ('ROLE_USER', 100004);

INSERT INTO restaurant (name)
VALUES  ('Roga and Kopita'),
  ('Pupkin obshepit');


INSERT INTO menu (rest_id, menu_date) VALUES
  (100005, '2018-09-08'),
  (100006, '2015-09-08');


INSERT INTO dish (name, price, menu_id)
VALUES  ('Meat', 150, 100007),
  ('Borsh', 333, 100007),
  ('Soup', 22, 100007),
  ('Egg', 111, 100007),
  ('cheese soup', 22, 100008),
  ('Coffee', 22, 100008),
  ('dessert', 22, 100008),
  ('salad', 22, 100008);

INSERT INTO vote (user_id, menu_id, vote_date) VALUES
  (100003, 100007, '2018-09-19'),
  (100002, 100008, '2018-09-23');