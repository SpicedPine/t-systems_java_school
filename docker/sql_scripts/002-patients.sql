create table patients
(
	id bigint auto_increment
		primary key,
	DIAGNOSE varchar(255) not null,
	FIRST_NAME varchar(255) not null,
	LAST_NAME varchar(255) not null,
	PHYSICIAN varchar(255) null,
	SOCIAL_NUMBER int not null,
	STATUS int not null
)
engine=MyISAM;

INSERT INTO newdb.patients (id, DIAGNOSE, FIRST_NAME, LAST_NAME, PHYSICIAN, SOCIAL_NUMBER, STATUS) VALUES (1, 'artrit', 'Ivan', 'pupkin', 'Ivan Ivanov', 432, 0);
INSERT INTO newdb.patients (id, DIAGNOSE, FIRST_NAME, LAST_NAME, PHYSICIAN, SOCIAL_NUMBER, STATUS) VALUES (2, 'broken arm', 'vova', 'pupkin', 'Ivan Ivanov', 23456, 0);
INSERT INTO newdb.patients (id, DIAGNOSE, FIRST_NAME, LAST_NAME, PHYSICIAN, SOCIAL_NUMBER, STATUS) VALUES (4, 'reabilitation', 'vova', 'romanov', 'Ivan Ivanov', 432235, 0);
