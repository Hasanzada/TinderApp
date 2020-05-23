CREATE TABLE IF NOT EXISTS `bf88a4c93d33b2`.`chat`
(
    `id`           INT         NOT NULL AUTO_INCREMENT,
    `user_from_id` INT         NULL,
    `user_to_id`   INT         NULL,
    `message_text` TINYTEXT    NULL,
    `message_time` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
);