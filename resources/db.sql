CREATE TABLE car_brands(
	id_brand int NOT NULL AUTO_INCREMENT,
	brand_name varchar(255) NOT NULL,
	date_of_record date,
	PRIMARY KEY (id_brand)
);
CREATE TABLE car_models(
	id_model int NOT NULL AUTO_INCREMENT,
	id_brand int NOT NULL,
	model_name varchar(255) NOT NULL,
	date_of_record date,
	PRIMARY KEY (id_model),
	FOREIGN KEY (id_brand) REFERENCES car_brands(id_brand)
);
CREATE TABLE car_db_users(
	id_user int NOT NULL AUTO_INCREMENT,
	login varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	date_of_registration date,
	PRIMARY KEY (id_user)
);