create table tist_teacher
(
    id_  int(255) not null primary key,
    name_        varchar(320),
    teaching_score_ double,
    update_time_ TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table tist_student
(
    id_  int(255) not null primary key,
    name_        varchar(32),
    admission_date_ date,
    update_time_ timestamp default  current_date on update current_date,
    fk_tist_teacher_  int(255),
--     constraint fk_teacher_order foreign key (teacher_id_)
--         references tist_teacher (id_)
--         on delete cascade
    foreign key (fk_tist_teacher_) references tist_teacher (id_) on delete cascade

);

INSERT INTO tist_teacher (id_, name_,teaching_score_)
VALUES (1, 'John',7.6),
       (2, 'Frank',8.2),
       (3,'Rose',7.9);
INSERT INTO tist_student (id_, name_, admission_date_, fk_tist_teacher_)
values (1, 'Paul', TO_DATE('2022/03/18', 'YYYY/MM/DD'), 1),
       (2, 'Frank',TO_DATE('2022/03/10', 'YYYY/MM/DD'),1),
       (3, 'David',TO_DATE('2022/03/02', 'YYYY/MM/DD'), 2);
