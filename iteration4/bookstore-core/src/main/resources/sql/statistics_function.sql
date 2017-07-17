-- Procedures
DELIMITER //

CREATE OR REPLACE PROCEDURE customer_stat(in customerId BIGINT(20) )
    BEGIN
        SELECT count(*) as number, sum(ORDER_TOTAL) as total
        FROM `orders`
        WHERE CUSTOMER_ID = customerId;
    END //

CREATE OR REPLACE PROCEDURE book_stat(in isbn VARCHAR(255) )
    BEGIN
        SELECT sum(ITEM_QUANTITY) AS quantity, sum(ONETIME_CHARGE) AS total
        FROM `order_item`
        WHERE  ITEM_ISBN = isbn;
    END //

CREATE OR REPLACE PROCEDURE date_stat(in startDate DATE, in endDate DATE)
    BEGIN
        SELECT count(*) as number, sum(ORDER_TOTAL) as total
        FROM `orders`
        WHERE DATE_PURCHASED BETWEEN startDate AND endDate;
    END //

DELIMITER ;