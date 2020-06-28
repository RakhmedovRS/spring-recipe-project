CREATE DATABASE development;
CREATE DATABASE production;

CREATE USER 'development_spring'@'localhost' IDENTIFIED BY 'spring';
CREATE USER 'production_spring'@'localhost' IDENTIFIED BY 'spring';

GRANT INSERT ON development.* to 'development_spring'@'localhost';
GRANT DELETE ON development.* to 'development_spring'@'localhost';
GRANT UPDATE ON development.* to 'development_spring'@'localhost';
GRANT SELECT ON production.* to 'production_spring'@'localhost';
GRANT INSERT ON production.* to 'production_spring'@'localhost';
GRANT DELETE ON production.* to 'production_spring'@'localhost';
GRANT UPDATE ON production.* to 'production_spring'@'localhost';
GRANT SELECT ON development.* to 'development_spring'@'localhost';
GRANT INSERT ON development.* to 'development_spring'@'localhost';
GRANT DELETE ON development.* to 'development_spring'@'localhost';
GRANT UPDATE ON development.* to 'development_spring'@'localhost';
GRANT SELECT ON production.* to 'production_spring'@'localhost';
GRANT INSERT ON production.* to 'production_spring'@'localhost';
GRANT DELETE ON production.* to 'production_spring'@'localhost';
GRANT UPDATE ON production.* to 'production_spring'@'localhost';