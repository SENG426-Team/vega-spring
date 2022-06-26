DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS userinfo;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS secrets;
DROP TABLE IF EXISTS shared_secrets;
DROP TABLE IF EXISTS keys;

CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS  authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE IF NOT EXISTS userinfo (
    username VARCHAR(50) NOT NULL,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);
-- CREATE UNIQUE  INDEX ix_auth_username on authorities (username,authority);

CREATE TABLE IF NOT EXISTS secrets (
    secret_id INTEGER NOT NULL,
    username VARCHAR(50) NOT NULL,
    date_created DATE NOT NULL,
    secret LONGBLOB NOT NULL,
    PRIMARY KEY (secret_id),
    FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE IF NOT EXISTS shared_secrets (
    shared_id INTEGER NOT NULL,
    secret_id INTEGER NOT NULL,
    sender VARCHAR(50) NOT NULL,
    recipient VARCHAR(50) NOT NULL,
    PRIMARY KEY (shared_id),
    FOREIGN KEY (secret_id) REFERENCES secrets(secret_id),
    FOREIGN KEY (sender) REFERENCES users(username),
    FOREIGN KEY (recipient) REFERENCES users(username)
);

CREATE TABLE IF NOT EXISTS keys (
    secret_id INTEGER NOT NULL,
    secret_key VARCHAR(100) NOT NULL,
    PRIMARY KEY (secret_id),
    FOREIGN KEY (secret_id) REFERENCES secrets(secret_id)
);