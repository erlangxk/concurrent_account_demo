
CREATE DATABASE `ccw`;

CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL,
  `balance` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DELIMITER //
CREATE PROCEDURE proc_withdraw (IN uid BIGINT,IN delta BIGINT)
BEGIN
  DECLARE cur_balance BIGINT;
  SELECT balance INTO cur_balance FROM accounts WHERE id = uid;
  IF cur_balance is not null && cur_balance >= delta && delta >0 THEN
      UPDATE accounts set balance=balance-delta where id=uid;
      select cur_balance-delta as balance;
  ELSE
      select cur_balance as balance;
  END IF;
END //
DELIMITER ;