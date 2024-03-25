
CREATE SCHEMA IF NOT EXISTS sql_practice_electronic_library
DEFAULT CHARACTER SET utf8 ;
USE sql_practice_electronic_library;

commit;

-- Создаем таблицу авторов
CREATE TABLE authors (
    authorID INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    birthYear INT,
    deathYear INT
);

-- Создаем таблицу книг
CREATE TABLE books (
    bookID INT PRIMARY KEY,
    title VARCHAR(100),
    authorID INT,
    genre VARCHAR(50),
    publishedYear INT,
    pages INT,
    FOREIGN KEY (authorID) REFERENCES authors(authorID)
);

-- Создаем таблицу читателей
CREATE TABLE readers (
    readerID INT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    email VARCHAR(100),
    signUpDate DATE
);

-- Создаем таблицу для операций с книгами
CREATE TABLE bookTransactions (
    transactionID INT PRIMARY KEY,
    readerID INT,
    bookID INT,
    borrowDate DATE,
    returnDate DATE,
    FOREIGN KEY (readerID) REFERENCES readers(readerID),
    FOREIGN KEY (bookID) REFERENCES books(bookID)
);

commit;