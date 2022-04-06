DROP DATABASE IF EXISTS pa2;
CREATE DATABASE pa2;

USE pa2;

DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS rating_details;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS restaurant_details;
DROP TABLE IF EXISTS users;

CREATE TABLE `category` (
  `category_name` varchar(100) NOT NULL,
  `restaurant_id` varchar(45) NOT NULL,
  `category_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rating_details` (
  `rating_id` int NOT NULL AUTO_INCREMENT,
  `review_count` int NOT NULL,
  `rating` float NOT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `restaurant` (
  `restaurant_id` varchar(45) NOT NULL,
  `restaurant_name` varchar(500) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `restaurant_details` (
  `details_id` int NOT NULL AUTO_INCREMENT,
  `image_url` varchar(500) NOT NULL,
  `address` varchar(500) NOT NULL,
  `phone_no` varchar(45) NOT NULL,
  `estimated_price` varchar(45) NOT NULL,
  `yelp_url` varchar(500) NOT NULL,
  PRIMARY KEY (`details_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `useremail` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `userpassword` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



