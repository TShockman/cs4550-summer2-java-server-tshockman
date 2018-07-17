CREATE TABLE `module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfq09oddpwjoxcirvkh9vnfnsg` (`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

