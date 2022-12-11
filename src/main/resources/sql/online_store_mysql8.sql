-- Database: store
USE store;

--
-- Table structure for table `client`
--
DROP TABLE if exists client;
CREATE TABLE client (
  name varchar(50),
  address varchar(50),
  nif varchar(9),
  email varchar(50) PRIMARY KEY,
  type enum('PREMIUM','STANDARD'),
  quota float,
  discount float
);

--
-- Table structure for table `item`
--
DROP TABLE if exists item;
CREATE TABLE item (
  code int PRIMARY KEY AUTO_INCREMENT,
  description varchar(255),
  sale_price float,
  shipping_costs float,
  preparation_time int NOT NULL
);

--
-- Table structure for table `orders`
--
DROP TABLE if exists orders;
CREATE TABLE orders (
  codeOrder int PRIMARY KEY AUTO_INCREMENT,
  amount int NOT NULL,
  email varchar(50) NOT NULL,
  code int NOT NULL,
  order_date_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


--
-- Constraints for table `orders`
--
ALTER TABLE orders
  ADD CONSTRAINT `FK_code` FOREIGN KEY (`code`) REFERENCES `item` (`code`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_email` FOREIGN KEY (`email`) REFERENCES `client` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

