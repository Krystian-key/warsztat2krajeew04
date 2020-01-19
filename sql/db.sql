DROP DATABASE warsztat2krajeew04;
CREATE DATABASE warsztat2krajeew04
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
USE warsztat2krajeew04;

CREATE TABLE users(
id INT AUTO_INCREMENT NOT NULL,
username varchar (255),
email varchar (255) UNIQUE NOT NULL,
password varchar(245) NOT NULL,
PRIMARY KEY(id)
);