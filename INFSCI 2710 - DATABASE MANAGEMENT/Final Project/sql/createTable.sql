use dbproject;
drop table if exists product;
create table product(
	movieID int  not null ,
	movieTitle varchar(200) not null,
	contentRating varchar(50) not null,
	genres varchar(200) not null,
	language varchar(20) not null,
	country  varchar(20) not null,
	titleYear int(4) not null,
	actor1Name varchar(50) not null,
	actor2Name varchar(50) not null,
	directorName varchar(50) not null,
	unitPrice decimal(12,2) not null,
	webScore decimal(10,2) not null,
	imageFileName varchar(20) not null,
	primary key(movieID),
	unique (movieID)
) ENGINE=InnoDB;

drop table if exists login;
create table login(
	userID int NOT NULL AUTO_INCREMENT,
	email char(200) NOT NULL,
	password char(20) NOT NULL,
	userType int NOT NULL default 1, # 0 admin 1 customer
	primary key(userID),
	unique(email)
) ENGINE=InnoDB;

drop table if exists customer;
create table customer(
	customerID int primary key not null  auto_increment,
	name varchar(100) not null,
	street varchar(200),
	city varchar(200),
	state varchar(10),
	zipcode int(5),
	email varchar(200),
	phoneNumber int,
	customerType varchar(10) not null default 'individual',
	unique(customerID)
) ENGINE=InnoDB;

drop table if exists individualCustomer;
create table individualCustomer(
	customerID int primary key not null ,
	gender varchar(6),
	age int,
	annualIncome decimal(10,2),
	marriageStatus varchar(10),
	foreign key (customerID) references customer (customerID),
	unique(customerID)
) ENGINE=InnoDB;

drop table if exists businessCustomer;
create table businessCustomer(
	customerID int primary key not null ,
	category varchar(30) not null,
	grossAnnualIncome decimal(10,2),
	foreign key (customerID) references customer (customerID),
	unique(customerID)
) ENGINE=InnoDB;

drop table if exists salesperson;
create table salesperson(
	salespersonID int(5) primary key not null  auto_increment,
	name varchar(30) not null,
	jobTitle varchar(14) not null default 'salesperson',
	street varchar(200),
	city varchar(200),
	state varchar(10),
	zipcode int(5),
	storeAssigned int,
	email varchar(200),
	salary decimal(20,2),
	unique(salespersonID)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS store;
CREATE TABLE store
( storeID int,
  street varchar(200),
	city varchar(200),
	state varchar(10),
	zipcode int(5),
  managerID int,
  numberOfSalesperson int,
  regionID int,
  PRIMARY KEY(storeID)
  )ENGINE=InnoDB;

  DROP TABLE IF EXISTS region;
  CREATE TABLE region
( regionID int,
  regionName varchar(255),
  region_managerID int,
  PRIMARY KEY(regionID)
  )ENGINE=InnoDB;

DROP TABLE if EXISTS transactions;
CREATE TABLE transactions(
orderID int,
movieID int,
orderDate DATE,
userID int,
salespersonName char(200),
quantity int,
unitPrice decimal(12,2),
PRIMARY KEY (orderID)
)ENGINE=InnoDB;

DROP TABLE if EXISTS cart;
CREATE TABLE cart(
movieID int,
userID int,
quantity int,
unitPrice decimal(12,2),
PRIMARY KEY (movieID)
)ENGINE=InnoDB;

CREATE TABLE inventory(
movieID int,
storeID int,
inventoryAmount int,
PRIMARY KEY(movieID)
)

CREATE TABLE sales(
movieID int,
salespersonID int ,
PRIMARY KEY(movieID)
)