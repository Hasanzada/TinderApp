CREATE TABLE IF NOT EXISTS `heroku_0db43a596d5905f`.`likes`
(
    `id`            INT     NOT NULL AUTO_INCREMENT,
    `user_id`       INT     NOT NULL,
    `liked_user_id` INT     NOT NULL,
    `status`        TINYINT NOT NULL,
    PRIMARY KEY (`id`)
);