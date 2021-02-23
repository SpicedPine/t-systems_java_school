create table staff
(
	id bigint auto_increment
		primary key,
	EMAIL varchar(255) not null,
	FIRST_NAME varchar(255) not null,
	LAST_NAME varchar(255) not null,
	PASSWORD varchar(255) not null,
	POST_ID bigint not null
)
engine=MyISAM;

create index FKcv4ffv2vuhwxm46emcmqxfrxb
	on staff (POST_ID);

INSERT INTO newdb.staff (id, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, POST_ID) VALUES (3, 't-systems@physician.com', 'Ivan', 'Ivanov', '$2a$10$ftkPpvRyVHqcZlKkN8AHYOMc./PGNFgA1gKH6IJhazlEirTUIRNIq', 1);
INSERT INTO newdb.staff (id, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, POST_ID) VALUES (4, 't-systems@physician2.com', 'Petya', 'Petrov', '$2a$10$cIwJH/IYTOst0hs3P3ffSudw4Qw9HucpZUOwU99LmGlNOk6/8zHKG', 1);
INSERT INTO newdb.staff (id, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, POST_ID) VALUES (6, 't-systems@nurse1.com', 'Kolya', 'Retisov', '$2a$10$.pVve.NjZZcOEKo27IX00eHGmfw6cr.YU6O71j8T4jpoy4q9shi9.', 2);