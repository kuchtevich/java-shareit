DROP TABLE IF EXISTS users, requests, items, bookings, comments;

CREATE TABLE IF NOT EXISTS users (
user_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
user_name VARCHAR(70) NOT NULL,
email VARCHAR(70) NOT NULL,
CONSTRAINT uq_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS requests (
request_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
description VARCHAR(250) NOT NULL,
requestor_id BIGINT NOT NULL,
created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
CONSTRAINT fk_requestor FOREIGN KEY (requestor_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS items (
item_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
item_name VARCHAR(70) NOT NULL,
description VARCHAR(250) NOT NULL,
is_available BOOLEAN NOT NULL,
owner_id BIGINT NOT NULL,
request_id BIGINT,
CONSTRAINT fk_owner FOREIGN KEY (owner_id) REFERENCES users(user_id) ON DELETE CASCADE,
CONSTRAINT fk_request FOREIGN KEY (request_id) REFERENCES requests(request_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS bookings (
booking_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
booking_start TIMESTAMP WITHOUT TIME ZONE NOT NULL,
booking_end TIMESTAMP WITHOUT TIME ZONE NOT NULL,
booker_id BIGINT NOT NULL,
item_id BIGINT NOT NULL,
status VARCHAR NOT NULL,
CONSTRAINT fk_booker FOREIGN KEY (booker_id) REFERENCES users(user_id) ON DELETE CASCADE,
CONSTRAINT fk_item_book FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comments (
comment_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL PRIMARY KEY,
text VARCHAR(250) NOT NULL,
item_id BIGINT NOT NULL,
author_id BIGINT NOT NULL,
comment_created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
CONSTRAINT fk_item_com FOREIGN KEY (item_id) REFERENCES items(item_id) ON DELETE CASCADE,
CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES users(user_id) ON DELETE CASCADE
);
