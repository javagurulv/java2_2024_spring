
CREATE TABLE movies (
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    director VARCHAR(100) NOT NULL,
    year_of_issue INT NOT NULL,
    length_minutes INT NOT NULL,
    PRIMARY KEY(id)
);
