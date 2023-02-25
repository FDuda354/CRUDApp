drop table if exists `visit`;
create table `visit` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`doctor_id` BIGINT NOT NULL ,
`description` VARCHAR(800) NULL,
`createdDate` DATETIME,
`visitDate` DATETIME,
`docktorType` VARCHAR(20),
PRIMARY KEY (`id`)
constraint `fk_visit_docktor`
foreign key (`doctor_id`)
references `doctor` (`id`)
on delete cascade
on update cascade
);
