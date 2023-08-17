DROP TABLE IF EXISTS _user_role;

CREATE TABLE _user_role
(
    _user_id UUID NOT NULL,
    role_id BIGINT    NOT NULL,
    PRIMARY KEY (_user_id, role_id),
    FOREIGN KEY (_user_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE

);