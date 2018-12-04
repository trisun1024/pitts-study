use dbproject;

INSERT INTO login VALUES (1,'1@pitt.edu' ,'1',1);
INSERT INTO login VALUES (2,'2@pitt.edu' ,'2',1);
INSERT INTO login VALUES (3,'3@gmail.com' ,'3',1);
INSERT INTO login VALUES (4,'4@gmail.com' ,'4',1);
INSERT INTO login VALUES (11,'1232131@gmail.com',11,0)

INSERT INTO customer VALUES (1,'John Doe', '101 Centre Ave', 'Pittsburgh', 'PA', '15213','1@gmail.com',1234567890, 'individual');
INSERT INTO customer VALUES (2,'James Doe', '102 Centre Ave', 'Pittsburgh', 'PA', '15213','2@gmail.com',1234567890, 'individual');
INSERT INTO customer VALUES (3,'Bob Doe', '103 Centre Ave', 'Pittsburgh', 'PA', '15213', '3@gmail.com',1234567890,'individual');
INSERT INTO customer VALUES (4,'Peter Doe', '104 Centre Ave', 'Pittsburgh', 'PA', '15213','4@gmail.com',1234567890, 'individual');
INSERT INTO customer VALUES (5,'Lewis Doe', '105 Centre Ave', 'Pittsburgh', 'PA', '15213', '11@gmail.com',1234567890,'individual');
INSERT INTO customer VALUES (6,'Kelly Doe', '106 Centre Ave', 'Pittsburgh', 'PA', '15213', '12@gmail.com',1234567890,'individual');
INSERT INTO customer VALUES (7,'Serena Doe', '107 Centre Ave', 'Pittsburgh', 'PA', '15213', '121@gmail.com',1234567890,'individual');
INSERT INTO customer VALUES (8,'Wally Doe', '108 Centre Ave', 'Pittsburgh', 'PA', '15213', '132@gmail.com',1234567890,'individual');
INSERT INTO customer VALUES (9,'Bill Doe', '109 Centre Ave', 'Pittsburgh', 'PA', '15213', '112323@gmail.com',1234567890,'individual');
INSERT INTO customer VALUES (10,'Steve Doe', '110 Centre Ave', 'Pittsburgh', 'PA', '15213','123232@gmail.com', 1234567890,'individual');
INSERT INTO customer VALUES (11,'Serena Doe', '107 Centre Ave', 'Pittsburgh', 'PA', '15213','1232131@gmail.com',1234567890, 'individual');
INSERT INTO customer VALUES (12,'Wally Doe', '108 Centre Ave', 'Pittsburgh', 'PA', '15213','11233@gmail.com', 1234567890,'individual');
INSERT INTO customer VALUES (13,'Bill Doe', '109 Centre Ave', 'Pittsburgh', 'PA', '15213', '421@gmail.com',1234567890,'individual');
INSERT INTO customer VALUES (14,'Steve Doe', '110 Centre Ave', 'Pittsburgh', 'PA', '15213','142@gmail.com', 1234567890,'individual');

INSERT INTO individualCustomer VALUES (1,"male",45,400000,"Married");
INSERT INTO individualCustomer VALUES (2,"male",63,400000,"Single");
INSERT INTO individualCustomer VALUES (3,"female",59,400000,"Common-Law");
INSERT INTO individualCustomer VALUES (4,"male",69,400000,"Divorced");
INSERT INTO individualCustomer VALUES (5,"female",58,400000,"Married");
INSERT INTO individualCustomer VALUES (6,"male",60,400000,"Single");
INSERT INTO individualCustomer VALUES (7,"female",46,400000,"Married");
INSERT INTO individualCustomer VALUES (8,"male",20,400000,"Married");
INSERT INTO individualCustomer VALUES (9,"male",32,400000,"Common-Law");
INSERT INTO individualCustomer VALUES (10,"male",39,400000,"Single");

INSERT INTO businessCustomer VALUES (11,"Educational",1368908);
INSERT INTO businessCustomer VALUES (12,"Corporate",7808025);
INSERT INTO businessCustomer VALUES (13,"Legal",8437584);
INSERT INTO businessCustomer VALUES (14,"Arts",8865848);



INSERT INTO transactions values (1,1, '2018/12/2', 1, 'Jack', '2', '23.7');

INSERT INTO cart values ('1', '1', '2', '23.7');
INSERT INTO cart values ('2', '4', '1', '30');
INSERT INTO cart values ('3', '13', '1', '24.5');
INSERT INTO cart values ('4', '15', '1', '25');
INSERT INTO cart values ('15', '2', '2', '22.5');
INSERT INTO cart values ('7', '7', '6', '26');
INSERT INTO cart values ('8', '6', '5', '25');
INSERT INTO cart values ('24', '3', '4', '18');
INSERT INTO cart values ('2', '4', '5', '30');
INSERT INTO cart values ('10', '24', '3', '25');
INSERT INTO cart values ('5', '3', '2', '26.37');
INSERT INTO cart values ('8', '8', '1', '25');
INSERT INTO cart values ('12', '10', '1', '20');
INSERT INTO cart values ('14', '17', '1', '21.5');
INSERT INTO cart values ('13', '37', '3', '22.5');
INSERT INTO cart values ('15', '22', '4', '22.5');
INSERT INTO cart values ('5', '17', '10', '26.37');
INSERT INTO cart values ('5', '2', '15', '26.37');
INSERT INTO cart values ('8', '1', '1', '25');
INSERT INTO cart values ('3', '19', '9', '24.5');
INSERT INTO cart values ('12', '18', '2', '20');
INSERT INTO cart values ('46', '15', '2', '7');
INSERT INTO cart values ('24', '9', '15', '18');
INSERT INTO cart values ('35', '25', '6', '20');
INSERT INTO cart values ('13', '27', '4', '22.5');
INSERT INTO cart values ('20', '28', '5', '25');
INSERT INTO cart values ('10', '14', '1', '25');


 INSERT INTO store VALUES (1, 'Centre Ave','Pittsburgh','PA',15213, 111, 3, 1);
 INSERT INTO store VALUES (2, 'Forbes Ave','Pittsburgh','PA',15213, 222, 3, 1);
 INSERT INTO store VALUES (3, 'Fifth Ave','Pittsburgh','PA',15213, 333, 3, 1);
 INSERT INTO store VALUES (4, 'Craig Ave','Pittsburgh','PA',15213, 444, 3, 2);
 INSERT INTO store VALUES (5, 'Dithridge Ave','Pittsburgh','PA',15213, 555, 3, 2);


  INSERT INTO region VALUES (1, 'Oakland', 777);
  INSERT INTO region VALUES (2, 'Downtown', 888);

  INSERT INTO salesperson VALUES (111, 'Jack', 'Store Manager', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 1, '123@pitt.edu', 100000);
  INSERT INTO salesperson VALUES (11, 'Jerry', 'Salesman', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 1, '1234@pitt.edu', 60000);
  INSERT INTO salesperson VALUES (12, 'Jeff', 'Salesman', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 1, '234@pitt.edu', 60000);
  INSERT INTO salesperson VALUES (222, 'Deck', 'Store Manager', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 2, '1@pitt.edu', 100000);
  INSERT INTO salesperson VALUES (13, 'Jerry', 'Salesman', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 2, '1234@pitt.edu', 60000);
  INSERT INTO salesperson VALUES (14, 'Jimmy', 'Salesman', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 2, '2345@pitt.edu', 60000);
  INSERT INTO salesperson VALUES (333, 'Mary', 'Store Manager', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 3, '223@pitt.edu', 100000);
  INSERT INTO salesperson VALUES (15, 'Rose', 'Salesman', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 3, '528@pitt.edu', 60000);
  INSERT INTO salesperson VALUES (16, 'Mike', 'Salesman', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 3, '41@pitt.edu', 60000);
  INSERT INTO salesperson VALUES (444, 'Jack', 'Store Manager', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 4, '152@pitt.edu', 100000);
  INSERT INTO salesperson VALUES (17, 'Ford', 'Salesman', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 4, '418@pitt.edu', 60000);
  INSERT INTO salesperson VALUES (18, 'Jerry', 'Salesman', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 4, '529@pitt.edu', 60000);
  INSERT INTO salesperson VALUES (555, 'Amy', 'Store Manager', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 5, '5292@pitt.edu', 100000);
  INSERT INTO salesperson VALUES (19, 'Harry', 'Salesman', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 5, '941@pitt.edu', 60000);
  INSERT INTO salesperson VALUES (20, 'Ted', 'Salesman', 'Centre Ave', 'Pittsburgh', 'PA', 15213, 5, '539@pitt.edu', 60000);



INSERT INTO sales VALUES (1,11);
INSERT INTO sales VALUES (2,11);
INSERT INTO sales VALUES (3,11);
INSERT INTO sales VALUES (4,11);
INSERT INTO sales VALUES (5,11);
INSERT INTO sales VALUES (6,111);
INSERT INTO sales VALUES (7,11);
INSERT INTO sales VALUES (8,11);
INSERT INTO sales VALUES (9,111);
INSERT INTO sales VALUES (10,11);
INSERT INTO sales VALUES (11,111);
INSERT INTO sales VALUES (12,11);
INSERT INTO sales VALUES (13,11);
INSERT INTO sales VALUES (14,111);
INSERT INTO sales VALUES (15,11);
INSERT INTO sales VALUES (16,111);
INSERT INTO sales VALUES (17,11);
INSERT INTO sales VALUES (18,11);