DROP TABLE IF EXISTS stocks;

CREATE TABLE stocks
(
  id varchar(36) NOT NULL,
  symbol varchar(200) NOT NULL,
  price decimal DEFAULT NULL,
  date timestamp NOT NULL,
  PRIMARY KEY (id)
)