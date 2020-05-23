CREATE TABLE IF NOT EXISTS `tinderdb`.`user`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(45) NOT NULL,
    `surname`    VARCHAR(45) NOT NULL,
    `imgurl`     TEXT        NOT NULL,
    `email`      VARCHAR(50) NOT NULL,
    `password`   VARCHAR(45) NOT NULL,
    `job`        VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC)
);