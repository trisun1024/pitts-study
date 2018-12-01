sql file import order:

create.sql
insertEmployee.sql
insertLogin.sql
insertRegion.sql
insertStore.sql
insertStoreEmployee.sql
insertProduct.sql
insertInventory.sql
insertCustomer(individual).sql (large amount of data)
insertCustomer(business).sql (large amount of data)
insertIndividualCustomer.sql (large amount of data)
insertBusinessCustomer.sql (large amount of data)
createUserAndGrant.sql

-----------------------------------------------------------------------------
The deleteUser.sql is to delete the users created by createUserAndGrant.sql .
Index.sql is to create index for columns that may be "select ... where" frequently.

When inserting large amount of data, it is better to import with mysql console, 
set autocommit=0 before inserting, and manually commit after inserting.

-----------------------------------------------------------------------------
The default username and password used by conn.php is both "infsci2710"