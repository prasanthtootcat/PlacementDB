create table college
( code integer,
name varchar(20),
type varchar(20),
dean varchar(20),
primary key(code)
);

create table department
( 
d_id integer,
name varchar(20),
hod varchar(20),
college_code integer,
foreign key (college_code) references college(code),
primary key(d_id,college_code)
);

create table course
( c_id integer,
name varchar(20),
department_id integer,
college_code integer,
foreign key(department_id,college_code) references department(d_id,college_code),
primary key(c_id,department_id,college_code)
);

create table internship
( intern_id integer,
name_of_company varchar(30),
no_of_months integer,
salary integer,
primary key(intern_id)
);

create table company
( company_id integer,
name varchar(20),
location varchar(45),
criteria float,
primary key(company_id)
);


create table student
( 
s_id integer,
name varchar(20),
age integer,
course_id integer,
department_id integer,
college_code integer,
exam_marks integer,
gender varchar(2),
primary key(s_id),
foreign key(course_id,department_id,college_code) references course(c_id,department_id,college_code)
);

create table academics
( a_id integer,
cgpa float,
no_of_arrears integer,
student_id integer,
foreign key(student_id) references student(s_id),
primary key(a_id)
);

create table attends
( student_id integer,
intern_id integer,
foreign key(student_id) references student(s_id),
foreign key(intern_id) references internship(intern_id),
primary key(student_id,intern_id)
);



create table interviewer
( i_id integer,
name varchar(20),
company_id integer,
foreign key(company_id) references company(company_id),
primary key(i_id)
);

create table interviews
( interviewer_id integer,
company_id integer,
student_id integer,
foreign key(interviewer_id) references interviewer(i_id),
foreign key(company_id) references company(company_id),
foreign key(student_id) references student(s_id),
primary key(interviewer_id,student_id)
);

create table hires
(
company_id integer,
student_id integer,
status varchar(10),
foreign key(company_id) references company(company_id),
foreign key(student_id) references student(s_id),
primary key(company_id,student_id)
);

create table project
( p_id integer,
p_name varchar(40),
marks_for_project float,
student_id integer,
foreign key(student_id) references student(s_id),
primary key(p_id)
);




