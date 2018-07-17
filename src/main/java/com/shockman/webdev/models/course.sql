CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrwo7afdgjbouh3em9xmfxnjx1` (`owner_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

