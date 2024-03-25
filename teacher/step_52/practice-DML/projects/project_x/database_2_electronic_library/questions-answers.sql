1. Сколько всего книг в библиотеке?
SELECT COUNT(*) FROM books;


2. Сколько всего пользователей в библиотеке?
SELECT COUNT(*) FROM users;


3. Какие книги были написаны в определенный год (например в 2005)?
SELECT title FROM books
WHERE publishedYear = 2005;


4. Какие книги имеют больше всего страниц?
SELECT title, pages FROM books
ORDER BY pages DESC
LIMIT 5;


5. Какие книги написал определенный автор (например "Leo Tolstoy")?
SELECT title FROM books
JOIN authors ON books.authorID = authors.authorID
WHERE authors.firstName = "Leo" AND authors.lastName = "Tolstoy";


6. Кто автор книги с определенным названием (например "Three Sisters")?
SELECT authors.firstName, authors.lastName
FROM books
JOIN authors ON books.authorID = authors.authorID
WHERE books.title = "Three Sisters";


7. Какое количество книг каждого жанра есть в библиотеке?
SELECT genre, COUNT(*) FROM books
GROUP BY genre;


8. Какие авторы пишут в определенном жанре (например "Fantasy")?
SELECT authors.firstName, authors.lastName
FROM authors
JOIN books ON authors.authorID = books.authorID
WHERE books.genre = 'Fantasy'
GROUP BY authors.authorID;


9. Какие книги брал пользователь с именем "User" и фамилией "Three"?
SELECT books.title FROM bookTransactions
JOIN books ON bookTransactions.bookID = books.bookID
JOIN users ON bookTransactions.userID = users.userID
WHERE users.firstName = "User" AND users.lastName = "Three";



10. Какие книги в данный момент на руках у пользователей (не возвращены)?
SELECT books.title, users.firstName, users.lastName
FROM bookTransactions
JOIN books ON bookTransactions.bookID = books.bookID
JOIN users ON bookTransactions.userID = users.userID
WHERE returnDate IS NULL;



11. Какое количество книг взял каждый пользователь за всё время?
SELECT users.firstName,
       users.lastName,
       COUNT(bookTransactions.bookID) AS takenBookCount
FROM users
JOIN bookTransactions ON users.userID = bookTransactions.userID
GROUP BY users.userID;


12. Какие книги были возвращены после определенной даты (например после '2023-02-01')?
SELECT books.title FROM bookTransactions
JOIN books ON bookTransactions.bookID = books.bookID
WHERE returnDate > '2023-02-01';


13. Какие книги были взяты в определенный год (например в 2023)?
SELECT books.title FROM bookTransactions
JOIN books ON bookTransactions.bookID = books.bookID
WHERE YEAR(borrowDate) = 2023;



14. Какова продолжительность чтения каждой книги в днях (для каждого раза когда книгу брали на чтение)?
SELECT users.firstName, users.lastName,
	   books.title,
       DATEDIFF(bookTransactions.returnDate, bookTransactions.borrowDate) as ReadingTimeDays
FROM bookTransactions
JOIN users ON users.userID = bookTransactions.userID
JOIN books ON books.bookID = bookTransactions.bookID
WHERE bookTransactions.returnDate IS NOT NULL;


15. Какова средняя продолжительность чтения книги для каждого пользователя?
SELECT users.firstName, users.lastName,
       AVG(DATEDIFF(bookTransactions.returnDate, bookTransactions.borrowDate)) as AverageReadingTimeDays
FROM users
JOIN bookTransactions ON users.userID = bookTransactions.userID
WHERE bookTransactions.returnDate IS NOT NULL
GROUP BY users.userID;



16. Кто автор с наибольшим количеством книг в библиотеке?
SELECT authors.firstName, authors.lastName
FROM authors
JOIN books ON authors.authorID = books.authorID
GROUP BY authors.authorID
ORDER BY COUNT(books.bookID) DESC
LIMIT 1;


17. Какие книги были взяты, но еще не возвращены, и кто их взял?
SELECT users.firstName,
       users.lastName,
       books.title
FROM users
JOIN bookTransactions ON users.userID = bookTransactions.userID
JOIN books ON bookTransactions.bookID = books.bookID
WHERE bookTransactions.returnDate IS NULL;


18. Какие книги у данного пользователя (c userID = 2) на руках в данный момент?
SELECT books.title
FROM bookTransactions
JOIN books ON bookTransactions.bookID = books.bookID
WHERE bookTransactions.userID = 2
      AND bookTransactions.returnDate IS NULL;


19. Сколько книг было взято каждым пользователем за последний месяц?
SELECT users.firstName,
       users.lastName,
       COUNT(bookTransactions.bookID)
FROM users
JOIN bookTransactions ON users.userID = bookTransactions.userID
WHERE bookTransactions.borrowDate > DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)
GROUP BY users.userID;


20. Какой пользователь взял больше всего книг?
SELECT users.firstName,
       users.lastName,
       COUNT(bookTransactions.bookID) AS BooksTaken
FROM users
JOIN bookTransactions ON users.userID = bookTransactions.userID
GROUP BY users.userID
ORDER BY booksTaken DESC
LIMIT 1;


21. Какие книги возвращались позже всего?
SELECT books.title
FROM bookTransactions
JOIN books ON bookTransactions.bookID = books.bookID
WHERE DATEDIFF(bookTransactions.returnDate, bookTransactions.borrowDate) > (SELECT AVG(DATEDIFF(returnDate, borrowDate)) FROM bookTransactions WHERE returnDate IS NOT NULL);


22. Сколько времени в среднем проходит между тем, как пользователь берет книгу и возвращает её?
SELECT AVG(DATEDIFF(returnDate, borrowDate)) AS AvgDays
FROM bookTransactions
WHERE returnDate IS NOT NULL;


23. Какие книги ещё никогда не брали?
SELECT title FROM books
WHERE bookID NOT IN (SELECT DISTINCT bookID FROM bookTransactions);


24. Какой самый популярный жанр среди пользователей?
SELECT books.genre,
       COUNT(bookTransactions.bookID) as Popularity
FROM bookTransactions
JOIN books ON bookTransactions.bookID = books.bookID
GROUP BY books.genre
ORDER BY Popularity DESC
LIMIT 1;


25. Какой автор имеет наибольшее количество взятых на чтение его книг?
SELECT authors.firstName,
       authors.lastName,
       COUNT(bookTransactions.bookID) AS BorrowedBooksCount
FROM authors
JOIN books ON authors.authorID = books.authorID
JOIN bookTransactions ON books.bookID = bookTransactions.bookID
GROUP BY authors.authorID
ORDER BY borrowedBooksCount DESC
LIMIT 1;


26. Какая книга была взята последней?
SELECT books.title
FROM books
JOIN bookTransactions ON books.bookID = bookTransactions.bookID
ORDER BY bookTransactions.borrowDate DESC
LIMIT 1;


27. Кто является самым активным пользователем за последний год?
SELECT users.firstName,
       users.lastName,
       COUNT(bookTransactions.bookID) AS BorrowedBooksCount
FROM bookTransactions
JOIN users ON users.userID = bookTransactions.userID
WHERE YEAR(bookTransactions.borrowDate) = 2023
GROUP BY users.userID
ORDER BY BorrowedBooksCount DESC
LIMIT 1;


28. Какая книга находилась в пользовании дольше всего?
SELECT books.title,
       MAX(DATEDIFF(bookTransactions.returnDate, bookTransactions.borrowDate)) AS DaysBorrowed
FROM books
JOIN bookTransactions ON books.bookID = bookTransactions.bookID
WHERE bookTransactions.returnDate IS NOT NULL
GROUP BY books.bookID
ORDER BY DaysBorrowed DESC
LIMIT 1;


29. Какой автор имеет наибольшее количество книг, которые ещё не возвращены?
SELECT authors.firstName,
       authors.lastName,
       COUNT(bookTransactions.bookID) AS NotReturnedBooksCount
FROM authors
JOIN books ON authors.authorID = books.authorID
JOIN bookTransactions ON books.bookID = bookTransactions.bookID
WHERE bookTransactions.returnDate IS NULL
GROUP BY authors.authorID
ORDER BY NotReturnedBooksCount DESC
LIMIT 1;


30. Сколько книг было взято в определенный день недели?
SELECT COUNT(*) AS TotalBooks
FROM bookTransactions
WHERE DAYNAME(borrowDate) = "Wednesday";


31. Какой самый редко встречающийся жанр в библиотеке?
SELECT genre, COUNT(*) AS GenreCount
FROM books
GROUP BY genre
ORDER BY GenreCount ASC
LIMIT 1;


32. Какой пользователь взял наибольшее количество книг определенного автора?
SELECT users.firstName,
	   users.lastName,
       COUNT(*) AS BooksCount
FROM users
JOIN bookTransactions ON users.userID = bookTransactions.userID
JOIN books ON bookTransactions.bookID = books.bookID
JOIN authors ON books.authorID = authors.authorID
WHERE authors.firstName = "Имя_автора" AND authors.lastName = "Фамилия_автора"
GROUP BY users.userID
ORDER BY BooksCount DESC
LIMIT 1;


33. Сколько страниц прочитал каждый пользователь в сумме?
SELECT users.firstName,
       users.lastName,
       SUM(books.pages) AS TotalPagesRead
FROM users
JOIN bookTransactions ON users.userID = bookTransactions.userID
JOIN books ON bookTransactions.bookID = books.bookID
WHERE bookTransactions.returnDate IS NOT NULL
GROUP BY users.userID;


34. Какая книга взята чаще всего?
SELECT books.title,
	   COUNT(bookTransactions.bookID) AS TimesBorrowed
FROM books
JOIN bookTransactions ON books.bookID = bookTransactions.bookID
GROUP BY books.bookID
ORDER BY TimesBorrowed DESC
LIMIT 1;


35. Какие книги возвращены раньше срока (срок возврата 30 дней)?
SELECT books.title
FROM books
JOIN bookTransactions ON books.bookID = bookTransactions.bookID
WHERE DATEDIFF(bookTransactions.returnDate, bookTransactions.borrowDate) < 30 AND bookTransactions.returnDate IS NOT NULL;


36. Какой пользователь возвращает книги позже срока чаще всего (срок возврата 30 дней)?
SELECT users.firstName,
       users.lastName,
       COUNT(bookTransactions.bookID) AS LateReturns
FROM bookTransactions
JOIN users ON users.userID = bookTransactions.userID
WHERE DATEDIFF(bookTransactions.returnDate, bookTransactions.borrowDate) > 30
	AND bookTransactions.returnDate IS NOT NULL
GROUP BY users.userID
ORDER BY LateReturns DESC
LIMIT 1;


37. Какое общее количество книг было взято за определенный период времени?
SELECT COUNT(*) AS TotalBooks
FROM bookTransactions
WHERE borrowDate >= '2022-01-01' AND borrowDate <= '2024-12-31';


38. Какой день недели является самым популярным для взятия книг?
SELECT DAYNAME(borrowDate) AS PopularDay,
       COUNT(*) AS TotalBorrowed
FROM bookTransactions
GROUP BY DAYNAME(borrowDate)
ORDER BY TotalBorrowed DESC
LIMIT 7;



39. Сколько книг определенного жанра взято в данный момент?
SELECT COUNT(*) AS TotalBooks
FROM books
JOIN bookTransactions ON books.bookID = bookTransactions.bookID
WHERE books.genre = "Fantasy" AND bookTransactions.returnDate IS NULL;


40. Какой пользователь взял книги большего количества различных авторов?
SELECT users.firstName,
       users.lastName,
       COUNT(DISTINCT authors.authorID) AS UniqueAuthorsCount
FROM users
JOIN bookTransactions ON users.userID = bookTransactions.userID
JOIN books ON bookTransactions.bookID = books.bookID
JOIN authors ON books.authorID = authors.authorID
GROUP BY users.userID
ORDER BY UniqueAuthorsCount DESC
LIMIT 1;


41. Какова максимальная, минимальная и средняя продолжительность чтения книг?
SELECT MAX(DATEDIFF(returnDate, borrowDate)) AS MaxDays,
       MIN(DATEDIFF(returnDate, borrowDate)) AS MinDays,
       AVG(DATEDIFF(returnDate, borrowDate)) AS AvgDays
FROM bookTransactions
WHERE returnDate IS NOT NULL;


42. Какой жанр книг имеет наименьшее количество взятий на чтение?
SELECT books.genre, COUNT(bookTransactions.bookID) AS borrowedCount
FROM books
JOIN bookTransactions ON books.bookID = bookTransactions.bookID
GROUP BY books.genre
ORDER BY BorrowedCount
LIMIT 1;

