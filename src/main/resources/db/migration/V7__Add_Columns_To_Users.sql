ALTER TABLE users
ADD COLUMN role varchar (50) NOT NULL;

ALTER TABLE users
ADD COLUMN is_email_confirmed bit NOT NULL DEFAULT bit'0';

ALTER TABLE users
ADD COLUMN refresh_token varchar(100) NULL;