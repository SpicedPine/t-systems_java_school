create table proc_and_med
(
	id bigint auto_increment
		primary key,
	NAME varchar(255) not null,
	TYPE int not null
)
engine=MyISAM;

INSERT INTO newdb.proc_and_med (id, NAME, TYPE) VALUES (1, 'kt', 0);
INSERT INTO newdb.proc_and_med (id, NAME, TYPE) VALUES (2, 'mrt', 0);
INSERT INTO newdb.proc_and_med (id, NAME, TYPE) VALUES (3, 'rentgen', 0);
INSERT INTO newdb.proc_and_med (id, NAME, TYPE) VALUES (4, 'aspirin', 1);
INSERT INTO newdb.proc_and_med (id, NAME, TYPE) VALUES (5, 'mezim', 1);