DROP TABLE IF EXISTS token;

CREATE TABLE token
(
    id        UUID PRIMARY KEY,
    token     VARCHAR(255) UNIQUE,
    token_type VARCHAR(40),
    revoked   BOOLEAN,
    expired   BOOLEAN,
    user_id UUID
);