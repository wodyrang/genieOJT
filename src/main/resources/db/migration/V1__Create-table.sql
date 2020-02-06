


CREATE TABLE `album` (
  `album_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `album_title` varchar(200) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`album_id`),
  UNIQUE INDEX (`album_title`)
) DEFAULT CHARSET=utf8mb4;

CREATE TABLE `locale` (
  `album_id` bigint(20) NOT NULL,
  `locale_type` varchar(50) NOT NULL,
  PRIMARY KEY (`album_id`,`locale_type`),
  CONSTRAINT `locale_FK` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`)
) DEFAULT CHARSET=utf8mb4;

CREATE TABLE `song` (
  `song_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `length` int(11) NOT NULL,
  `track` int(11) NOT NULL,
  `album_id` bigint(20) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`song_id`),
  KEY `song_FK` (`album_id`),
  CONSTRAINT `song_FK` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`)
) DEFAULT CHARSET=utf8mb4;


CREATE TABLE `play_group` (
  `play_group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(200) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`play_group_id`)
) DEFAULT CHARSET=utf8mb4;

CREATE TABLE `play_item` (
  `song_id` bigint(20) NOT NULL,
  `play_group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`play_group_id`,`song_id`),
  KEY `play_item_song_FK` (`song_id`),
  CONSTRAINT `play_item_group_FK` FOREIGN KEY (`play_group_id`) REFERENCES `play_group` (`play_group_id`),
  CONSTRAINT `play_item_song_FK` FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`)
) DEFAULT CHARSET=utf8mb4;