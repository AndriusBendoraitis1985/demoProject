CREATE TABLE operations (
opr_id BIGINT NOT NULL AUTO_INCREMENT,
opr_account_number VARCHAR(128) NOT NULL,
opr_date TIMESTAMP WITH TIME ZONE NOT NULL,
opr_beneficiary VARCHAR(128) NOT NULL,
opr_comment VARCHAR(255) NOT NULL,
opr_amount DOUBLE NOT NULL,
opr_currency VARCHAR(5) NOT NULL,
PRIMARY KEY (opr_id));