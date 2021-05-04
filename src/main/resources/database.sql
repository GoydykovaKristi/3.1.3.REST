-- DaoJdbcAuthentication

# -- Table: users
CREATE TABLE users(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(80) NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    age INT,
    email VARCHAR(50) UNIQUE

)
    ENGINE  = InnoDB;



-- Table: roles
CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
)
    ENGINE  = InnoDB;


-- Table: for mapping user add roles: user_roles
CREATE TABLE  user_roles (
    user_id BIGINT NOT NULL ,
    role_id BIGINT NOT NULL ,

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),

    UNIQUE (user_id, role_id)

)
    ENGINE  = InnoDB;


