create table prescriptions
(
    id                  bigint auto_increment
        primary key,
    FORMED_PRESCRIPTION varchar(255) not null,
    PATIENT_ID          bigint       not null,
    PROC_OR_MED_ID      bigint       not null
)
    engine = MyISAM;

create index FK3waix0jv78s1roocj5eqb17wg
    on prescriptions (PATIENT_ID);

create index FKsdfpxaajcor0sa37d0l1x4l27
    on prescriptions (PROC_OR_MED_ID);

INSERT INTO newdb.prescriptions (id, FORMED_PRESCRIPTION, PATIENT_ID, PROC_OR_MED_ID) VALUES (1, 'procedure kt 2 week on tuesday and friday 3 week', 1, 1);
INSERT INTO newdb.prescriptions (id, FORMED_PRESCRIPTION, PATIENT_ID, PROC_OR_MED_ID) VALUES (6, 'medicine aspirin 2 day before meal at morning and evening 7 day 1/2', 4, 4);
INSERT INTO newdb.prescriptions (id, FORMED_PRESCRIPTION, PATIENT_ID, PROC_OR_MED_ID) VALUES (3, 'procedure mrt 2 week  on thursday and monday 2 week', 2, 2);