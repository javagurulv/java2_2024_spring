
CREATE TABLE books (
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);


CREATE TABLE readers (
    id BIGINT AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    personal_code VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);


CREATE TABLE library_cards (
    id BIGINT AUTO_INCREMENT,
    reader_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    issue_date DATE NOT NULL,
    return_date DATE NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (reader_id) REFERENCES readers(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);
