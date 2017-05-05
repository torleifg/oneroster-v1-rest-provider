-- Org --
INSERT INTO Org (sourced_id, date_last_modified, status, name, type, identifier) VALUES (1, '2017-03-01 20:20:06.111', 'ACTIVE', 'Etne kommune', 'LOCAL', 'NO959435375')
INSERT INTO Org (sourced_id, date_last_modified, status, name, type, identifier, parent_sourced_id) VALUES (2, '2017-03-02 20:20:06.222', 'ACTIVE', 'Enge skule', 'SCHOOL', 'NO975281086', 1)
INSERT INTO Org (sourced_id, date_last_modified, status, name, type, identifier, parent_sourced_id) VALUES (3, '2017-03-03 20:20:06.333', 'ACTIVE', 'Skånevik skule', 'SCHOOL', 'NO975281094', 1)

INSERT INTO Org_metadata (org_sourced_id, metadata_key, metadata) VALUES (1, 'foo', 'bar')

-- AcademicSession --
INSERT INTO Academic_session (sourced_id, date_last_modified, status, title, start_date, end_date, type) VALUES (1, '2017-03-01 20:20:06.111', 'ACTIVE', 'Skoleåret 2016/2017', '2016-08-21', '2017-06-22', 'SCHOOLYEAR')
INSERT INTO Academic_session (sourced_id, date_last_modified, status, title, start_date, end_date, type, parent_sourced_id) VALUES (2, '2017-03-02 20:20:06.222', 'ACTIVE', '1. termin', '2016-08-21', '2017-12-22', 'TERM', 1)
INSERT INTO Academic_session (sourced_id, date_last_modified, status, title, start_date, end_date, type, parent_sourced_id) VALUES (3, '2017-03-03 20:20:06.333', 'ACTIVE', '2. termin', '2017-01-03', '2017-06-22', 'TERM', 1)
INSERT INTO Academic_session (sourced_id, date_last_modified, status, title, start_date, end_date, type, parent_sourced_id) VALUES (4, '2017-03-04 20:20:06.444', 'ACTIVE', '1. kvartal', '2016-08-21', '2017-10-20', 'GRADINGPERIOD', 2)
INSERT INTO Academic_session (sourced_id, date_last_modified, status, title, start_date, end_date, type, parent_sourced_id) VALUES (5, '2017-03-05 20:20:06.555', 'ACTIVE', '2. kvartal', '2016-10-21', '2017-12-22', 'GRADINGPERIOD', 2)
INSERT INTO Academic_session (sourced_id, date_last_modified, status, title, start_date, end_date, type, parent_sourced_id) VALUES (6, '2017-03-04 20:20:06.666', 'ACTIVE', '3. kvartal', '2017-01-03', '2017-03-10', 'GRADINGPERIOD', 3)
INSERT INTO Academic_session (sourced_id, date_last_modified, status, title, start_date, end_date, type, parent_sourced_id) VALUES (7, '2017-03-05 20:20:06.777', 'ACTIVE', '4. kvartal', '2017-03-11', '2017-06-22', 'GRADINGPERIOD', 3)
-- User --
INSERT INTO User (sourced_id, date_last_modified, status, username, given_name, family_name, role) VALUES (1, '2017-03-01 20:20:06.111', 'ACTIVE', 'annalog', 'Ann', 'Alog', 'TEACHER')
INSERT INTO User (sourced_id, date_last_modified, status, username, given_name, family_name, role) VALUES (2, '2017-03-02 20:20:06.222', 'ACTIVE', 'endrepassord', 'Endre', 'Passord', 'TEACHER')

INSERT INTO User (sourced_id, date_last_modified, status, username, given_name, family_name, role) VALUES (3, '2017-03-03 20:20:06.333', 'ACTIVE', 'incognito', 'Inco', 'Gnito', 'PARENT')
INSERT INTO User (sourced_id, date_last_modified, status, username, given_name, family_name, role) VALUES (4, '2017-03-04 20:20:06.444', 'ACTIVE', 'antonnym', 'Anton', 'Nym', 'PARENT')

INSERT INTO User (sourced_id, date_last_modified, status, username, given_name, family_name, role) VALUES (5, '2017-03-05 20:20:06.555', 'ACTIVE', 'piano', 'Pia', 'No', 'STUDENT')
INSERT INTO User (sourced_id, date_last_modified, status, username, given_name, family_name, role) VALUES (6, '2017-03-06 20:20:06.666', 'ACTIVE', 'ingalykke', 'Inga', 'Lykke', 'STUDENT')
INSERT INTO User (sourced_id, date_last_modified, status, username, given_name, family_name, role) VALUES (7, '2017-03-07 20:20:06.777', 'ACTIVE', 'overalt', 'Ove', 'Ralt', 'STUDENT')
INSERT INTO User (sourced_id, date_last_modified, status, username, given_name, family_name, role) VALUES (8, '2017-03-08 20:20:06.888', 'ACTIVE', 'joellernei', 'Jo', 'Ellernei', 'STUDENT')
INSERT INTO User (sourced_id, date_last_modified, status, username, given_name, family_name, role) VALUES (9, '2017-03-09 20:20:06.999', 'ACTIVE', 'kallevotter', 'Kalle', 'Votter', 'STUDENT')
INSERT INTO User (sourced_id, date_last_modified, status, username, given_name, family_name, role) VALUES (10, '2017-03-10 20:20:06.111', 'ACTIVE', 'kallefotter', 'Kalle', 'Føtter', 'STUDENT')
INSERT INTO User (sourced_id, date_last_modified, status, username, given_name, family_name, role) VALUES (11, '2017-03-11 20:20:06.222', 'ACTIVE', 'klaranett', 'Klara', 'Nett', 'STUDENT')
INSERT INTO User (sourced_id, date_last_modified, status, username, given_name, family_name, role) VALUES (12, '2017-03-12 20:20:06.333', 'ACTIVE', 'annanas', 'Anna', 'Nas', 'STUDENT')

INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (1, 2)
INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (1, 3)
INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (2, 2)
INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (2, 3)

INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (3, 2)
INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (4, 3)

INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (5, 2)
INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (6, 2)
INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (7, 2)
INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (8, 2)
INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (9, 3)
INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (10, 3)
INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (11, 3)
INSERT INTO User_orgs (user_sourced_id, orgs_sourced_id) VALUES (12, 3)

INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (5, 3)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (6, 3)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (7, 3)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (8, 3)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (9, 4)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (10, 4)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (11, 4)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (12, 4)

INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (3, 5)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (3, 6)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (3, 7)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (3, 8)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (4, 9)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (4, 10)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (4, 11)
INSERT INTO User_agents(user_sourced_id, agents_sourced_id) VALUES (4, 12)

-- Course --
INSERT INTO Course (sourced_id, date_last_modified, status, title, course_code, school_year_sourced_id, org_sourced_id) VALUES (1, '2017-03-01 20:20:06.111', 'ACTIVE', 'Matematikk 8. årstrinn', 'MAT0008', 1, 2)
INSERT INTO Course (sourced_id, date_last_modified, status, title, course_code, school_year_sourced_id, org_sourced_id) VALUES (2, '2017-03-02 20:20:06.222', 'ACTIVE', 'Naturfag 8. årstrinn', 'NAT0008', 1, 2)

INSERT INTO Course (sourced_id, date_last_modified, status, title, course_code, school_year_sourced_id, org_sourced_id) VALUES (3, '2017-03-03 20:20:06.333', 'ACTIVE', 'Matematikk 8. årstrinn', 'MAT0008', 1, 3)
INSERT INTO Course (sourced_id, date_last_modified, status, title, course_code, school_year_sourced_id, org_sourced_id) VALUES (4, '2017-03-04 20:20:06.444', 'ACTIVE', 'Naturfag 8. årstrinn', 'NAT0008', 1, 3)

-- Class --
INSERT INTO Clazz (sourced_id, date_last_modified, status, title, clazz_code, grade, school_sourced_id, course_sourced_id) VALUES (1, '2017-03-01 20:20:06.111', 'ACTIVE', 'Undervisningsgruppe i Matematikk', '8maa', '8. årstrinn', 2, 1)
INSERT INTO Clazz (sourced_id, date_last_modified, status, title, clazz_code, grade, school_sourced_id, course_sourced_id) VALUES (2, '2017-03-02 20:20:06.222', 'ACTIVE', 'Undervisningsgruppe i Naturfag', '8naa', '8. årstrinn', 2, 2)

INSERT INTO Clazz (sourced_id, date_last_modified, status, title, clazz_code, grade, school_sourced_id, course_sourced_id) VALUES (3, '2017-03-03 20:20:06.333', 'ACTIVE', 'Undervisningsgruppe i Matematikk', '8maa', '8. årstrinn', 3, 3)
INSERT INTO Clazz (sourced_id, date_last_modified, status, title, clazz_code, grade, school_sourced_id, course_sourced_id) VALUES (4, '2017-03-04 20:20:06.444', 'ACTIVE', 'Undervisningsgruppe i Naturfag', '8naa', '8. årstrinn', 3, 4)

INSERT INTO Clazz_terms (clazz_sourced_id, terms_sourced_id) VALUES (1, 2)
INSERT INTO Clazz_terms (clazz_sourced_id, terms_sourced_id) VALUES (1, 3)
INSERT INTO Clazz_terms (clazz_sourced_id, terms_sourced_id) VALUES (2, 2)
INSERT INTO Clazz_terms (clazz_sourced_id, terms_sourced_id) VALUES (2, 3)
INSERT INTO Clazz_terms (clazz_sourced_id, terms_sourced_id) VALUES (3, 2)
INSERT INTO Clazz_terms (clazz_sourced_id, terms_sourced_id) VALUES (3, 3)
INSERT INTO Clazz_terms (clazz_sourced_id, terms_sourced_id) VALUES (4, 2)
INSERT INTO Clazz_terms (clazz_sourced_id, terms_sourced_id) VALUES (4, 3)

INSERT INTO Clazz_subjects(clazz_sourced_id, subjects) VALUES (1, 'abc, def, ghi')

-- Enrollment --
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, pr1ma4ry, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (1, '2017-03-01 20:20:06.111', 'ACTIVE', 'TEACHER', true, 2, 1, 1)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, pr1ma4ry, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (2, '2017-03-02 20:20:06.222', 'ACTIVE', 'TEACHER', false, 2, 2, 1)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, pr1ma4ry, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (3, '2017-03-03 20:20:06.333', 'ACTIVE', 'TEACHER', true, 2, 1, 2)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, pr1ma4ry, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (4, '2017-03-04 20:20:06.444', 'ACTIVE', 'TEACHER', false, 2, 2, 2)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, pr1ma4ry, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (5, '2017-03-05 20:20:06.555', 'ACTIVE', 'TEACHER', true, 3, 1, 3)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, pr1ma4ry, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (6, '2017-03-06 20:20:06.666', 'ACTIVE', 'TEACHER', false, 3, 2, 3)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, pr1ma4ry, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (7, '2017-03-07 20:20:06.777', 'ACTIVE', 'TEACHER', true, 3, 1, 4)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, pr1ma4ry, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (8, '2017-03-08 20:20:06.888', 'ACTIVE', 'TEACHER', false, 3, 2, 4)

INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (9, '2017-03-09 20:20:06.111', 'ACTIVE', 'STUDENT', 2, 5, 1)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (10, '2017-03-10 20:20:06.222', 'ACTIVE', 'STUDENT', 2, 5, 2)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (11, '2017-03-11 20:20:06.333', 'ACTIVE', 'STUDENT', 2, 6, 1)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (12, '2017-03-12 20:20:06.444', 'ACTIVE', 'STUDENT', 2, 6, 2)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (13, '2017-03-13 20:20:06.555', 'ACTIVE', 'STUDENT', 2, 7, 1)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (14, '2017-03-14 20:20:06.666', 'ACTIVE', 'STUDENT', 2, 7, 2)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (15, '2017-03-15 20:20:06.777', 'ACTIVE', 'STUDENT', 2, 8, 1)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (16, '2017-03-16 20:20:06.888', 'ACTIVE', 'STUDENT', 2, 8, 2)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (17, '2017-03-17 20:20:06.999', 'ACTIVE', 'STUDENT', 3, 9, 3)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (18, '2017-03-18 20:20:06.111', 'ACTIVE', 'STUDENT', 3, 9, 4)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (19, '2017-03-19 20:20:06.222', 'ACTIVE', 'STUDENT', 3, 10, 3)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (20, '2017-03-20 20:20:06.333', 'ACTIVE', 'STUDENT', 3, 10, 4)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (21, '2017-03-21 20:20:06.444', 'ACTIVE', 'STUDENT', 3, 11, 3)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (22, '2017-03-22 20:20:06.555', 'ACTIVE', 'STUDENT', 3, 11, 4)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (23, '2017-03-23 20:20:06.666', 'ACTIVE', 'STUDENT', 3, 12, 3)
INSERT INTO Enrollment (sourced_id, date_last_modified, status, role, school_sourced_id, user_sourced_id, clazz_sourced_id) VALUES (24, '2017-03-24 20:20:06.777', 'ACTIVE', 'STUDENT', 3, 12, 4)

-- Category -- 
INSERT INTO Category (sourced_id, date_last_modified, status, title) VALUES (1, '2017-03-01 20:20:06.111', 'ACTIVE', 'Hjemmelekser')
INSERT INTO Category (sourced_id, date_last_modified, status, title) VALUES (2, '2017-03-02 20:20:06.222', 'ACTIVE', 'Skolearbeid')

-- LineItem --
INSERT INTO Line_item (sourced_id, date_last_modified, status, title, assign_date, due_date, min, max, clazz_sourced_id, category_sourced_id, grading_period_sourced_id) VALUES (1, '2016-08-20 20:20:06.111', 'ACTIVE', 'Matematikkquiz 1', '2016-09-01 20:20:06', '2016-09-10 20:20:06', '1.0', '10.0', 1, 1, 4)
INSERT INTO Line_item (sourced_id, date_last_modified, status, title, assign_date, due_date, min, max, clazz_sourced_id, category_sourced_id, grading_period_sourced_id) VALUES (2, '2016-11-01 20:20:06.222', 'ACTIVE', 'Matematikkprøve 1', '2016-11-10 20:20:06', '2016-11-20 20:20:06', '1.0', '6.0', 1, 2, 5)
INSERT INTO Line_item (sourced_id, date_last_modified, status, title, assign_date, due_date, min, max, clazz_sourced_id, category_sourced_id, grading_period_sourced_id) VALUES (3, '2017-01-10 20:20:06.333', 'ACTIVE', 'Naturfagquiz 1', '2017-01-20 20:20:06', '2017-01-30 20:20:06', '1.0', '10.0', 2, 1, 6)
INSERT INTO Line_item (sourced_id, date_last_modified, status, title, assign_date, due_date, min, max, clazz_sourced_id, category_sourced_id, grading_period_sourced_id) VALUES (4, '2017-03-15 20:20:06.444', 'ACTIVE', 'Naturfagprøve 1', '2017-03-20 20:20:06', '2017-03-25 20:20:06', '1.0', '6.0', 2, 2, 7)

INSERT INTO Line_item (sourced_id, date_last_modified, status, title, assign_date, due_date, min, max, clazz_sourced_id, category_sourced_id, grading_period_sourced_id) VALUES (5, '2016-08-20 20:20:06.555', 'ACTIVE', 'Matematikkquiz 2', '2016-09-01 20:20:06', '2016-09-10 20:20:06', '1.0', '10.0', 3, 1, 4)
INSERT INTO Line_item (sourced_id, date_last_modified, status, title, assign_date, due_date, min, max, clazz_sourced_id, category_sourced_id, grading_period_sourced_id) VALUES (6, '2016-11-01 20:20:06.666', 'ACTIVE', 'Matematikkprøve 2', '2016-11-10 20:20:06', '2016-11-20 20:20:06', '1.0', '6.0', 3, 2, 5)
INSERT INTO Line_item (sourced_id, date_last_modified, status, title, assign_date, due_date, min, max, clazz_sourced_id, category_sourced_id, grading_period_sourced_id) VALUES (7, '2017-01-10 20:20:06.777', 'ACTIVE', 'Naturfagquiz 2', '2017-01-20 20:20:06', '2017-01-30 20:20:06', '1.0', '10.0', 4, 1, 6)
INSERT INTO Line_item (sourced_id, date_last_modified, status, title, assign_date, due_date, min, max, clazz_sourced_id, category_sourced_id, grading_period_sourced_id) VALUES (8, '2017-03-15 20:20:06.888', 'ACTIVE', 'Naturfagprøve 2', '2017-03-20 20:20:06', '2017-03-25 20:20:06', '1.0', '6.0', 4, 2, 7)

-- Result --
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (1, '2016-09-15 20:20:06.111', 'ACTIVE', '7.1', 'FULLYGRADED', 5, 1)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (2, '2016-11-25 20:20:06.222', 'ACTIVE', '5.2', 'FULLYGRADED', 5, 2)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (3, '2017-02-05 20:20:06.333', 'ACTIVE', '8.3', 'FULLYGRADED', 5, 3)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (4, '2017-03-30 20:20:06.444', 'ACTIVE', '4.4', 'FULLYGRADED', 5, 4)

INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (5, '2016-09-15 20:20:06.555', 'ACTIVE', '6.5', 'FULLYGRADED', 6, 1)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (6, '2016-11-25 20:20:06.666', 'ACTIVE', '4.6', 'FULLYGRADED', 6, 2)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (7, '2017-02-05 20:20:06.777', 'ACTIVE', '7.7', 'FULLYGRADED', 6, 3)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (8, '2017-03-30 20:20:06.888', 'ACTIVE', '3.8', 'FULLYGRADED', 6, 4)

INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (9, '2016-09-15 20:20:06.999', 'ACTIVE', '5.0', 'FULLYGRADED', 7, 1)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (10, '2016-11-25 20:20:06.111', 'ACTIVE', '3.1', 'FULLYGRADED', 7, 2)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (11, '2017-02-05 20:20:06.222', 'ACTIVE', '6.2', 'FULLYGRADED', 7, 3)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (12, '2017-03-30 20:20:06.333', 'ACTIVE', '2.3', 'FULLYGRADED', 7, 4)

INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (13, '2016-09-15 20:20:06.444', 'ACTIVE', '8.4', 'FULLYGRADED', 8, 1)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (14, '2016-11-25 20:20:06.555', 'ACTIVE', '6.5', 'FULLYGRADED', 8, 2)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (15, '2017-02-05 20:20:06.666', 'ACTIVE', '9.6', 'FULLYGRADED', 8, 3)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (16, '2017-03-30 20:20:06.777', 'ACTIVE', '5.7', 'FULLYGRADED', 8, 4)

INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (17, '2016-09-15 20:20:06.888', 'ACTIVE', '7.8', 'FULLYGRADED', 9, 5)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (18, '2016-11-25 20:20:06.999', 'ACTIVE', '5.9', 'FULLYGRADED', 9, 6)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (19, '2017-02-05 20:20:06.111', 'ACTIVE', '8.0', 'FULLYGRADED', 9, 7)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (20, '2017-03-30 20:20:06.222', 'ACTIVE', '4.1', 'FULLYGRADED', 9, 8)

INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (21, '2016-09-15 20:20:06.333', 'ACTIVE', '6.2', 'FULLYGRADED', 10, 5)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (22, '2016-11-25 20:20:06.444', 'ACTIVE', '4.3', 'FULLYGRADED', 10, 6)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (23, '2017-02-05 20:20:06.555', 'ACTIVE', '7.4', 'FULLYGRADED', 10, 7)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (24, '2017-03-30 20:20:06.666', 'ACTIVE', '3.5', 'FULLYGRADED', 10, 8)

INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (25, '2016-09-15 20:20:06.777', 'ACTIVE', '5.6', 'FULLYGRADED', 11, 5)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (26, '2016-11-25 20:20:06.888', 'ACTIVE', '3.7', 'FULLYGRADED', 11, 6)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (27, '2017-02-05 20:20:06.999', 'ACTIVE', '6.8', 'FULLYGRADED', 11, 7)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (28, '2017-03-30 20:20:06.111', 'ACTIVE', '2.9', 'FULLYGRADED', 11, 8)

INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (29, '2016-09-15 20:20:06.222', 'ACTIVE', '8.0', 'FULLYGRADED', 12, 5)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (30, '2016-11-25 20:20:06.333', 'ACTIVE', '6.1', 'FULLYGRADED', 12, 6)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (31, '2017-02-05 20:20:06.444', 'ACTIVE', '9.2', 'FULLYGRADED', 12, 7)
INSERT INTO Result (sourced_id, date_last_modified, status, score, result_status, student_sourced_id, line_item_sourced_id) VALUES (32, '2017-03-30 20:20:06.555', 'ACTIVE', '5.3', 'FULLYGRADED', 12, 8)