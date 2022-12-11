USE `online_store_db`;
DROP procedure IF EXISTS `create_order`;

DELIMITER $$
USE `online_store_db`$$
CREATE PROCEDURE `create_order` (IN p_amount int(11),
                                 IN p_email varchar(255),
                                 IN p_code int(11))

BEGIN

INSERT INTO orders (amount,email,code,order_date_time)
    values (p_amount,p_email,p_code,now());
END$$

DELIMITER ;
