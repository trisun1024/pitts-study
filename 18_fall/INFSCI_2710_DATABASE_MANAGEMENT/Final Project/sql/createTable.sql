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
	unitPrice decimal(10,2) not null,
	webScore decimal(10,2) not null ,
	imageFileName varchar(20) not null,
	primary key(movieID),
	unique (movieID),
	CONSTRAINT unitPrice CHECK (unitPrice>0)
) ENGINE=InnoDB;

drop table if exists user;
create table user(
	userID int NOT NULL  auto_increment,
	password char(20) NOT NULL,
	userType int NOT NULL,
	primary key(userID),
	CONSTRAINT userType CHECK (userType='0' or userType='1')
) ENGINE=InnoDB;

drop table if exists customer;
create table customer(
	customerID int primary key not null,
	name varchar(100) not null,
	street varchar(200),
	city varchar(200),
	state varchar(10),
	zipcode int(5),
	customerType varchar(10) not null,
	unique(customerID),
	FOREIGN KEY (customerID) references user (userID) on delete cascade,
	CONSTRAINT customerType check(customerType in ('individual', 'business'))
) ENGINE=InnoDB;

drop table if exists individualCustomer;
create table individualCustomer(
	customerID int primary key not null ,
	gender varchar(6) ,
	age int check(age>0),
	annualIncome decimal(10,2),
	marriageStatus varchar(10) ,
	foreign key (customerID) references customer (customerID) on delete cascade,
	unique(customerID),
	CONSTRAINT annualIncome check(annualIncome>0),
	CONSTRAINT marriageStatus check(marriageStatus in ('single', 'married', 'divorced')),
	CONSTRAINT gender check(gender in ('female', 'male'))
) ENGINE=InnoDB;

drop table if exists businessCustomer;
create table businessCustomer(
	customerID int primary key not null ,
	category varchar(30) not null,
	grossAnnualIncome decimal(10,2),
	foreign key (customerID) references customer (customerID) on delete cascade,
	unique(customerID),
	CONSTRAINT grossAnnualIncome check (grossAnnualIncome >0)
) ENGINE=InnoDB;

drop table if exists salesperson;
create table salesperson(
	salespersonID int(5) primary key not null  auto_increment,
	name varchar(30) not null,
	jobTitle varchar(14) not null,
	street varchar(200),
	city varchar(200),
	state varchar(10),
	zipcode int(5),
	storeAssigned int,
	email varchar(200),
	salary decimal(20,2) check (salary>0),
	unique(salespersonID),
	foreign key (salespersonID) references user (userID) on delete cascade
) ENGINE=InnoDB;

DROP TABLE IF EXISTS store;
CREATE TABLE store
( storeID int,
  street varchar(200),
	city varchar(200),
	state varchar(10),
	zipcode int(5),
  managerID int,
  numberOfSalesperson int ,
  regionID int,
  PRIMARY KEY(storeID),
  foreign key (managerID) references salesperson (salespersonID) on delete set null,
  foreign key (regionID) references region (regionID) on delete set null,
  CONSTRAINT numberOfSalesperson check (numberOfSalesperson>=0)
  )ENGINE=InnoDB;

  alter table salesperson add constraint storeAssign foreign key (storeAssigned) references store (storeID) on delete cascade

  DROP TABLE IF EXISTS region;
  CREATE TABLE region
( regionID int,
  regionName varchar(255),
  regionmanagerID int,
  PRIMARY KEY(regionID)
  )ENGINE=InnoDB;

   DROP TABLE IF EXISTS storemanage;
  CREATE TABLE storemanage
( storeID int,
  storemanagerID  int,
  PRIMARY KEY(storeID),
  foreign key (storeID) references store (storeID) on delete cascade,
  foreign key (storemanagerID) references salesperson (salespersonID) on delete set null
  )ENGINE=InnoDB;

    DROP TABLE IF EXISTS regionmanage;
  CREATE TABLE regionmanage
( regionID int,
  regionmanagerID  int,
  PRIMARY KEY(regionID,regionmanagerID),
  foreign key (regionID) references region (regionID) on delete cascade
  )ENGINE=InnoDB;

DROP TABLE if EXISTS transactions;
CREATE TABLE transactions(
orderID int auto_increment,
movieID int,
orderDate DATE,
userID int,
salespersonID int,
quantity int,
unitPrice decimal(12,2),
PRIMARY KEY (orderID),
foreign key (movieID) references product (movieID) on delete no action,
foreign key (userID) references user (userID) on delete no action,
foreign key (salespersonID) references salesperson(salespersonID) on delete no action
)ENGINE=InnoDB;

DROP TABLE if EXISTS cart;
CREATE TABLE cart(
movieID int,
userID int,
quantity int check(quantity>0),
unitPrice decimal(12,2),
ordertime datetime,
PRIMARY KEY (movieID),
foreign key (movieID) references product (movieID) on delete cascade,
foreign key (userID) references user (userID) on delete cascade
)ENGINE=InnoDB;

DROP TABLE if EXISTS inventory;
CREATE TABLE inventory(
movieID int,
storeID int,
inventoryAmount int check(inventoryAmount>0),
PRIMARY KEY(movieID),
foreign key (movieID) references product (movieID) on delete cascade,
foreign key (storeID) references store (storeID) on delete cascade
)

  DROP TABLE IF EXISTS sell;
  CREATE TABLE sell
( movieID int,
  salespersonID  int,
  PRIMARY KEY(movieID),
  foreign key (movieID) references product (movieID) on delete cascade,
  foreign key (salespersonID) references salesperson (salespersonID) on delete cascade
  )ENGINE=InnoDB;

    DROP TABLE IF EXISTS works_in;
  CREATE TABLE works_in
( salespersonID int,
  storeID  int,
  PRIMARY KEY(salespersonID,storeID),
  foreign key (salespersonID) references salesperson (salespersonID) on delete cascade,
  foreign key (storeID) references store (storeID) on delete cascade
  )ENGINE=InnoDB;
