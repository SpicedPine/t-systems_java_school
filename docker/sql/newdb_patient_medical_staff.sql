create table patient_medical_staff
(
    PATIENT_ID       bigint not null,
    MEDICAL_STAFF_ID bigint not null,
    primary key (MEDICAL_STAFF_ID, PATIENT_ID)
)
    engine = MyISAM;

create index FKfrlkqsg9mr5r7o8s39169993l
    on patient_medical_staff (PATIENT_ID);

