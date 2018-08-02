DROP TABLE IF EXISTS stocks;

CREATE TABLE stocks
(
  id integer NOT NULL AUTO_INCREMENT,
  symbol varchar(200) NOT NULL,
  price decimal NOT NULL,
  volume integer NOT NULL,
  date timestamp NOT NULL,
  PRIMARY KEY (id)
)