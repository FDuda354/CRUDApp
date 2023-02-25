drop table if exists `doctor`;
create table `doctor` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(60),
`surname` VARCHAR(60),
`docktorType` VARCHAR(20),
PRIMARY KEY (`id`)
)
