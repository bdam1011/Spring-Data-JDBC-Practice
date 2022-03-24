create table tist_teacher
(
    id_             int(255) not null primary key,
    name_           varchar(320),
    teaching_score_ double,
    update_time_    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table tist_student
(
    id_              int(255) not null primary key,
    name_            varchar(32),
    admission_date_  date,
    update_time_     timestamp default current_date on update current_date,
    fk_tist_teacher_ int(255),
--     constraint fk_teacher_order foreign key (teacher_id_)
--         references tist_teacher (id_)
--         on delete cascade
    foreign key (fk_tist_teacher_) references tist_teacher (id_) on delete cascade

);

create table tist_course
(
    id_              int(255) not null primary key,
    name_            varchar(32),
    fee_             double,
    update_time_     timestamp default current_date on update current_date

);

create table tist_teacher_course
(
    fk_tist_teacher_ int(255),
    fk_tist_course_ int(255),
    foreign key (fk_tist_teacher_) references tist_teacher (id_) on delete cascade,
    foreign key (fk_tist_course_) references tist_course (id_) on delete cascade
);


INSERT INTO tist_teacher (id_, name_, teaching_score_,update_time_)
values (1, 'John', 7.6,TIMESTAMP '2022-05-31 09:26:50'),
       (2, 'Frank', 8.2,TIMESTAMP '2020-06-15 09:26:50'),
       (3, 'Rose', 7.9,TIMESTAMP '2018-01-01 09:26:50');

INSERT INTO tist_student (id_, name_, admission_date_, fk_tist_teacher_,update_time_)
values (1, 'Paul', TO_DATE('2022/03/18', 'YYYY/MM/DD'), 1,TIMESTAMP '2022-05-31 09:26:50'),
       (2, 'Frank', TO_DATE('2022/03/10', 'YYYY/MM/DD'), 1,TIMESTAMP '2020-06-15 09:26:50'),
       (3, 'David', TO_DATE('2022/03/02', 'YYYY/MM/DD'), 2,TIMESTAMP '2018-01-01 09:26:50');

INSERT INTO tist_course (id_, name_, fee_, update_time_)
values (1, 'Math', 432.6,TIMESTAMP '2022-05-31 09:26:50'),
       (2, 'Logic', 8787.9,TIMESTAMP '2020-07-15 09:26:50'),
       (3, 'Science', 456.7,TIMESTAMP '2020-08-15 09:26:50'),
       (4, 'History', 183.7,TIMESTAMP '2020-09-15 09:26:50'),
       (5, 'Physic', 963.1,TIMESTAMP '2020-10-15 09:26:50');

INSERT INTO tist_teacher_course (fk_tist_teacher_, fk_tist_course_)
values(1,2),(1,1),(2,2),(2,4),(3,2),(3,3),(3,5);


