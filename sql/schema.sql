USE test;
CREATE TABLE tasks
(
	  id INT PRIMARY KEY auto_increment,
    name VARCHAR(50),
    priority varchar(30),
    description varchar(200),
    termEnd DATE,
    created timestamp default NOW()
);
