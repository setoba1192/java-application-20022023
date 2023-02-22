CREATE DATABASE `nisum_users`;

USE `nisum_users`;


--
-- Table structure for table `usuario`
--
DROP TABLE IF EXISTS `nisum_users`.`usuario`;

CREATE TABLE `nisum_users`.`usuario` (
  `id` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `created` datetime(6) DEFAULT NULL,
  `isactive` bit(1) DEFAULT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `modified` datetime(6) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2mlfr087gb1ce55f2j87o74t` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `telefono`
--
DROP TABLE IF EXISTS `nisum_users`.`telefono`;

CREATE TABLE `nisum_users`.`telefono` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo_ciudad` varchar(255) NOT NULL,
  `codigo_pais` varchar(255) NOT NULL,
  `numero` varchar(255) NOT NULL,
  `usuario_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpi2c7iq0lw09d1ovc7bn86f85` (`usuario_id`),
  CONSTRAINT `FKpi2c7iq0lw09d1ovc7bn86f85` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
