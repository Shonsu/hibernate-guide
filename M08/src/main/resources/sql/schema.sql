DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(100) NULL,
    `description` VARCHAR(800) NULL,
    `created`     DATETIME,
    `updated`     DATETIME,
    `price`       DECIMAL(5, 2),
    `type`        VARCHAR(10),
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `review`;
CREATE TABLE IF NOT EXISTS `review`
(
    `id`
             BIGINT
                          NOT
                              NULL
        AUTO_INCREMENT,
    `product_id`
             BIGINT
                          NOT
                              NULL,
    `content`
             VARCHAR(400) NULL,
    `rating` INT          NULL,
    PRIMARY KEY
        (
         `id`
            ),
    CONSTRAINT `fk_review_product`
        FOREIGN KEY
            (
             `product_id`
                )
            REFERENCES `product`
                (
                 `id`
                    )
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(100) NULL,
    `description` VARCHAR(800) NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE `product`
    ADD COLUMN `category_id` BIGINT NULL;

INSERT INTO `category`
    (`id`, `name`, `description`)
VALUES (1, 'Kategoria 1', 'Opis 1'),
       (2, 'Kategoria 2', 'Opis 2'),
       (3, 'Kategoria 3', 'Opis 3');

UPDATE product
SET category_id=1
WHERE id = 3;
UPDATE product
SET category_id=2
WHERE id = 4;
UPDATE product
SET category_id=3
WHERE id = 5;

ALTER TABLE `product`
    ADD CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

CREATE TABLE `attribute`
(
    `id`    BIGINT       NOT NULL AUTO_INCREMENT,
    `name`  VARCHAR(100) NULL,
    `value` VARCHAR(800) NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `product_attribute`
(
    `product_id`   BIGINT NOT NULL,
    `attribute_id` BIGINT NOT NULL,
    PRIMARY KEY (`product_id`, `attribute_id`),
    CONSTRAINT `fk_product_attribute_product_id`
        FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
    CONSTRAINT `fk_product_attribute_attribute_id`
        FOREIGN KEY (`attribute_id`) REFERENCES `attribute` (`id`)
);

INSERT INTO `attribute`
    (`id`, `name`, `value`)
VALUES (1, 'COLOR', 'RED'),
       (2, 'COLOR', 'GREEN'),
       (3, 'COLOR', 'BLUE');
INSERT INTO `product_attribute`
    (`product_id`, `attribute_id`)
VALUES (3, 1),
       (3, 2),
       (3, 3),
       (4, 1),
       (4, 2),
       (5, 3);

DROP TABLE IF EXISTS `customer_address`;
CREATE TABLE `customer_address`
(
    `customer_id`  BIGINT       NOT NULL,
    `address_type` VARCHAR(12)  NOT NULL,
    `postal_code`  VARCHAR(6),
    `street`       VARCHAR(120) NOT NULL,
    `city`         VARCHAR(80)  NOT NULL
);

DROP TABLE IF EXISTS `customer_details`;
CREATE TABLE `customer_details`
(
    `id`          BIGINT       NOT NULL,
    `birth_place` VARCHAR(100) NOT NULL,
    `birth_day`   DATETIME     NOT NULL,
    `father_name` VARCHAR(50),
    `mother_name` VARCHAR(50),
    `pesel`       VARCHAR(11),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id`) REFERENCES customer (`id`)
);

ALTER TABLE `order_row`
    DROP FOREIGN KEY `fk_order_row_order_id`;
ALTER TABLE `order_row`
    CHANGE COLUMN `order_id` `order_id` BIGINT NULL;
ALTER TABLE `order_row`
    ADD CONSTRAINT `fk_order_row_order_id`
        FOREIGN KEY (`order_id`) REFERENCES `order` (`id`);

DROP TABLE IF EXISTS `base_product`;
CREATE TABLE `base_product`
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(100) NOT NULL,
    `description`  VARCHAR(100) NOT NULL,
    `created`      DATETIME     NOT NULL,
    `product_type` VARCHAR(15)  NOT NULL,
    `weight`       DECIMAL(5, 2),
    `width`        INT,
    `length`       INT,
    `height`       INT,
    `downloadable` boolean,
    `file_path`    VARCHAR(100),
    `file_name`    VARCHAR(100),
    PRIMARY KEY (`id`)
);


ALTER TABLE `order` ADD COLUMN uuid BINARY(16) NULL AFTER total;
