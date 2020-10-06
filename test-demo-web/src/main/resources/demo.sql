
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `name` varchar(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `trade_id` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `security_code` varchar(10) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `transaction_type` varchar(20) DEFAULT NULL COMMENT 'insert/update/cancel',
  `type` varchar(20) DEFAULT NULL COMMENT 'buy/sell',
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS = 1;
