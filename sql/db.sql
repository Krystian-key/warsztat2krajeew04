CREATE DATABASE warsztat2krajeew04
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
USE warsztat2krajeew04;

CREATE TABLE warsztat2krajeew04.users_group
(
    id   INT(11) AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id            INT(11) AUTO_INCREMENT,
    username      VARCHAR(255),
    email         VARCHAR(255) UNIQUE NOT NULL,
    password      VARCHAR(245)        NOT NULL,
    user_group_id INT                 NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_group_id) REFERENCES users_group (id)
);

CREATE TABLE exercises
(
    id          INT(11) AUTO_INCREMENT,
    title       VARCHAR(255),
    description TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE solutions
(
    id          INT(11) AUTO_INCREMENT,
    created     DATETIME,
    updated     DATETIME,
    description TEXT,
    exercise_id INT,
    users_id    INT,
    PRIMARY KEY (id),
        FOREIGN KEY (exercise_id) REFERENCES exercises (id),
        FOREIGN KEY (users_id) REFERENCES users (id)
);