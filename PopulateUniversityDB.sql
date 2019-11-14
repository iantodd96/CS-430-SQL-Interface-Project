/* This file is meant for MySQL, and was tested with MySQL version 5.7! Proper functionality with other types of SQL or older versions of MySQL can't be guaranteed!*/

/* Do not run this file more than once. */

USE UniversityDB;

INSERT INTO Administrators
VALUES
('111111111', 'Dummy'),
('155632193', 'Willie Schwartz'),
('546214526', 'Evelyn Tyler'),
('125436125', 'Leticia Becker'),
('848528254', 'Ivan Perry'),
('387274758', 'Rosalie Harrington'),
('238484757', 'Armando Davidson'),
('194586929', 'Edmund Hubbard'),
('299485292', 'Zachary Lynch'),
('293849285', 'Alvin Welch'),
('485787429', 'Patrick Evans');

INSERT INTO Departments
VALUES
(0, 'Dummy', 'N/A'),
(1, 'Biology', 'SCI248'),
(2, 'Physics', 'SCI121'),
(3, 'Engineering', 'EGR321'),
(4, 'Computer Science', 'EGR220'),
(5, 'Mathematics', 'EGR129'),
(6, 'History', 'HIST113'),
(7, 'English', 'HIST125'),
(8, 'Geology', 'SCI201'),
(9, 'Accounting', 'BUS110'),
(10, 'Psychology', 'SCI222');

INSERT INTO Professors
VALUES
('111111111', 'Dummy', 0, 'N/A'),
('819375426', 'Betty Walker', 34, 'Biology'),
('186739524', 'Jan Garett', 29, 'Physics'),
('391849283', 'Myra West', 50, 'Engineering'),
('214149532', 'Grady Woods', 44, 'Computer Science'),
('235667321', 'Norman Harper', 41, 'Mathematics'),
('443215673', 'Mercedes Jefferson', 35, 'History'),
('513498321', 'Cynthia Poole', 31, 'English'),
('934654321', 'Elias King', 42, 'Geology'),
('714827591', 'Tina Greene', 53, 'Accounting'),
('658290148', 'Byron Chambers', 37, 'Psychology');

/*Some of the max_enrollment values here are intentionally NOT realistic.
I aimed for realism in most areas, but for these values I wanted to make
enrollment limits more easily testable without needing to
tediously create and log into a large number of student accounts.*/
INSERT INTO Courses
VALUES
('DUMMY000', '111111111', 0, 0, 1),
('BIO221', '819375426', 1, 0, 1),
('PHYS445', '186739524', 2, 0, 1),
('EGR204', '391849283', 3, 0, 1),
('CS306', '214149532', 4, 0, 1),
('MATH115', '235667321', 5, 0, 1),
('HIST345', '443215673', 6, 0, 1),
('ENG219', '513498321', 7, 0, 1),
('GEO314', '934654321', 8, 0, 1),
('ACC313', '714827591', 9, 0, 1),
('PSYC249', '658290148', 10, 0, 1);

INSERT INTO Students
VALUES
('111111111', 'Dummy', 0, 'N/A', 0),
('476386089', 'Ian Lyons', 21, 'BS', 1),
('854838583', 'Gordon Freeman', 23, 'PhD', 2),
('583919582', 'Minnie Weaver', 22, 'BS', 3),
('928392958', 'Alton Curtis', 19, 'BS', 4),
('398493948', 'Clinton Logan', 31, 'BS', 5),
('586827483', 'Bradford Gibbs', 18, 'BA', 6),
('484828595', 'Pauline Tran', 42, 'BA', 7),
('678486843', 'Tabitha Munoz', 21, 'BS', 8),
('485893295', 'Judith Wheeler', 22, 'MBA', 9),
('757853295', 'Marion Lowe', 19, 'MS', 10);
