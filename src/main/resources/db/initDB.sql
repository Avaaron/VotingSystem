DROP TABLE IF EXISTS vote CASCADE ;
DROP TABLE IF EXISTS dish ;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR   NOT NULL,
  user_id     INTEGER   NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX rest_unique ON restaurant (name);

CREATE TABLE menu (
  id INTEGER  PRIMARY KEY DEFAULT nextval('global_seq'),
  rest_id INTEGER NOT NULL ,
  menu_date TIMESTAMP DEFAULT now() NOT NULL,
  FOREIGN KEY (rest_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX menu_unique ON menu (rest_id, menu_date);

CREATE TABLE dish (
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  menu_id     INTEGER               NOT NULL,
  name              VARCHAR               NOT NULL,
  price             DOUBLE PRECISION      NOT NULL,
  FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX dish_unique ON dish (menu_id, name);

CREATE TABLE vote (
  id INTEGER  PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id INTEGER NOT NULL ,
  menu_id INTEGER NOT NULL ,
  vote_date TIMESTAMP NOT NULL ,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX vote_unique ON vote (user_id, vote_date);

