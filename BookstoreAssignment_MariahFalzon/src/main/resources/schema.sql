--Create a Books Table 

CREATE TABLE books(
id LONG PRIMARY KEY AUTO_INCREMENT,
isbn LONG,
title VARCHAR(255),
author VARCHAR(255),
price DOUBLE,
description VARCHAR(500)
);
