DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles
(
    user_id UUID NOT NULL,
    role_id BIGINT    NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE

);