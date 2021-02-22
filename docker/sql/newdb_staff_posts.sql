create table staff_posts
(
    id         bigint not null
        primary key,
    STAFF_POST int    null
)
    engine = MyISAM;

INSERT INTO newdb.staff_posts (id, STAFF_POST) VALUES (1, 0);
INSERT INTO newdb.staff_posts (id, STAFF_POST) VALUES (2, 1);