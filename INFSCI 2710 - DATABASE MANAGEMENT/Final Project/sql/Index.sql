use infsci2710;
CREATE INDEX customerNameIndex ON customer (name) USING BTREE;
CREATE INDEX inventoryIndex ON inventory (storeID,productID);