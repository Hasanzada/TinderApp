CREATE TABLE IF NOT EXISTS `bf88a4c93d33b2`.`likes`
(
    `id`            INT     NOT NULL AUTO_INCREMENT,
    `user_id`       INT     NOT NULL,
    `liked_user_id` INT     NOT NULL,
    `status`        TINYINT NOT NULL,
    PRIMARY KEY (`id`)
);