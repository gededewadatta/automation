CREATE TABLE COMPETENCY (
    ID int NOT NULL,
    DEPARTMENT_CODE varchar(255),
    GRADE_CODE varchar(255),
    COMPETENCY_NAME varchar(255),
    COMPETENCY_CODE varchar(255),
    CREATED_DATE timestamp,
    CREATED_BY varchar(255)
    PRIMARY KEY (ID)
);

CREATE TABLE DEPARTEMENT (
    ID int NOT NULL,
    DIVISION_CODE varchar(255),
    DEPARTEMENT_CODE varchar(255),
    DEPARTEMENT_NAME varchar(255),
    CREATED_DATE timestamp,
    CREATED_BY varchar(255)
    PRIMARY KEY (ID)
);

CREATE TABLE DIVISION (
    ID int NOT NULL,
    DIVISION_CODE varchar(255),
    DEPARTEMENT_CODE varchar(255),
    DEPARTEMENT_NAME varchar(255),
    CREATED_DATE timestamp,
    CREATED_BY varchar(255)
    PRIMARY KEY (ID)
);

CREATE TABLE EMPLOYEE (
    ID int NOT NULL,
    DEPARTEMENT_CODE varchar(255),
    GRADE_CODE varchar(255),
    DIVISION_CODE varchar(255),
    SUBGRADE_CODE varchar(255),
    EMPLOYEE_CODE varchar(255),
    EMPLOYEE_NAME varchar(255),
    CREATED_DATE timestamp,
    CREATED_BY varchar(255)
    PRIMARY KEY (ID)
);

CREATE TABLE GRADE (
    ID int NOT NULL,
    DEPARTEMENT_CODE varchar(255),
    GRADE_CODE varchar(255),
    GRADE_NAME varchar(255),
    CREATED_DATE timestamp,
    CREATED_BY varchar(255)
    PRIMARY KEY (ID)
);

CREATE TABLE QUESTION (
    ID int NOT NULL,
    GRADE varchar(255),
    SUB_GRADE varchar(255),
    QUESTIONS varchar(255),
    ANSWER_1 varchar(255),
    ANSWER_2 varchar(255),
    ANSWER_3 varchar(255),
    ANSWER_4 varchar(255),
    ANSWER_5 varchar(255),
    CORRECT_ANSWER varchar(255),
    CREATED_DATE timestamp,
    CREATED_BY varchar(255),
    COMPETENCY varchar(255)
    PRIMARY KEY (ID)
);

CREATE TABLE SUBGRADE (
    ID int NOT NULL,
    GRADE_CODE varchar(255),
    SUB_GRADE_CODE varchar(255),
    SUB_GRADE_NAME varchar(255),
    CREATED_DATE timestamp,
    CREATED_BY varchar(255)
    PRIMARY KEY (ID)
);





