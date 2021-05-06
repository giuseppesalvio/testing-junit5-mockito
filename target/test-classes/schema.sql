CREATE TABLE BOOK
(
    title            NCHAR(20),
    author          NCHAR(20),
    price       FLOAT
);

CREATE TABLE POLIZZA_UTENTE
(
    id INT PRIMARY KEY,
    numeroPolizza NCHAR(20),
    utente_id INT
);
