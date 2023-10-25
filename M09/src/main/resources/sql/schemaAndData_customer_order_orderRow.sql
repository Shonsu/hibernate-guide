DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
                            `id` BIGINT NOT NULL AUTO_INCREMENT,
                            `firstname` VARCHAR(100) NOT NULL,
                            `lastname` VARCHAR(800) NOT NULL,
                            `created` DATETIME,
                            `updated` DATETIME,
                            PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
                         `id` BIGINT NOT NULL AUTO_INCREMENT,
                         `customer_id` BIGINT NOT NULL,
                         `created` DATETIME,
                         `total` DECIMAL(5,2),
                         PRIMARY KEY (`id`),
                         CONSTRAINT `fk_order_customer_id`
                             FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
);

DROP TABLE IF EXISTS `order_row`;
CREATE TABLE `order_row` (
                             `id` BIGINT NOT NULL AUTO_INCREMENT,
                             `order_id` BIGINT NOT NULL,
                             `product_id` BIGINT NOT NULL,
                             `price` DECIMAL(5,2),
                             PRIMARY KEY (`id`),
                             CONSTRAINT `fk_order_row_order_id`
                                 FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
                             CONSTRAINT `fk_order_row_product_id`
                                 FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
);

UPDATE `product` SET `category_id` = '2' WHERE (`id` = '2');
UPDATE `product` SET `category_id` = '3' WHERE (`id` = '3');
UPDATE `product` SET `category_id` = '2' WHERE (`id` = '5');

INSERT INTO `customer`(`id`,`firstname`,`lastname`,`created`,`updated`)
VALUES
    (1, 'Jan', 'Kowalski', '2020-09-01T12:00:00', '2020-10-10T12:00:00'),
    (2, 'Jan', 'Nowak', '2020-09-01T12:00:00', '2020-10-10T12:00:00'),
    (3, 'Jan', 'Szewczyk', '2020-09-01T12:00:00', '2020-10-10T12:00:00'),
    (4, 'Jan', 'Wójcik ', '2020-09-01T12:00:00', '2020-10-10T12:00:00'),
    (5, 'Jan', 'Kowalczyk', '2020-09-01T12:00:00', '2020-10-10T12:00:00');

INSERT INTO `order`(`id`,`customer_id`,`created`,`total`)
VALUES
    (1, 1, '2020-10-10T12:00:00', 59.97), -- 3
    (2, 1, '2020-10-10T12:00:00', 39.98), -- 2
    (3, 1, '2020-10-10T12:00:00', 19.99), -- 1
    (4, 1, '2020-10-10T12:00:00', 19.99), -- 1
    (5, 1, '2020-10-10T12:00:00', 79.96), -- 4
    (6, 1, '2020-10-10T12:00:00', 19.99), -- 1
    (7, 2, '2020-10-10T12:00:00', 39.98), -- 2
    (8, 2, '2020-10-10T12:00:00', 19.99), -- 1
    (9, 2, '2020-10-10T12:00:00', 19.99), -- 1
    (10, 2, '2020-10-10T12:00:00', 39.98), -- 2
    (11, 3, '2020-10-10T12:00:00', 19.99), -- 1
    (12, 3, '2020-10-10T12:00:00', 19.99), -- 1
    (13, 3, '2020-10-10T12:00:00', 39.98), -- 2
    (14, 4, '2020-10-10T12:00:00', 39.98); -- 2


INSERT INTO `order_row`(`id`,`order_id`,`product_id`,`price`)
VALUES
    (1, 1, 1, 19.99),
    (2, 1, 2, 19.99),
    (3, 1, 3, 19.99),
    (4, 2, 1, 19.99),
    (5, 2, 2, 19.99),
    (6, 3, 1, 19.99),
    (7, 4, 1, 19.99),
    (8, 5, 2, 19.99),
    (9, 5, 3, 19.99),
    (10, 5, 4, 19.99),
    (11, 5, 5, 19.99),
    (12, 6, 4, 19.99),
    (13, 7, 5, 19.99),
    (14, 7, 1, 19.99),
    (15, 8, 1, 19.99),
    (16, 9, 2, 19.99),
    (17, 10, 3, 19.99),
    (18, 10, 4, 19.99),
    (19, 11, 5, 19.99),
    (20, 12, 3, 19.99),
    (21, 13, 3, 19.99),
    (22, 13, 3, 19.99),
    (23, 14, 3, 19.99),
    (24, 14, 3, 19.99);