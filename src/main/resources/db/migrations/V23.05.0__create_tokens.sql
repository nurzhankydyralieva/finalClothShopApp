DROP TABLE IF EXISTS tokens;

CREATE TABLE tokens
(
    id         BIGSERIAL PRIMARY KEY,
    token  VARCHAR(255),
    revoked BOOLEAN,
    expired BOOLEAN,
    token_type  VARCHAR(10),
    user_id      BIGINT
);