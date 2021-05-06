
DROP TABLE IF EXISTS BOOK;

CREATE TABLE BOOK
(
    title  NCHAR(20),
    author NCHAR(20),
    price  FLOAT
);



insert into BOOK (title,
                  author,
                  price)
values (
        'asd',
        'pop',
        999
       );



DROP TABLE IF EXISTS billionaires;

CREATE TABLE billionaires (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              first_name VARCHAR(250) NOT NULL,
                              last_name VARCHAR(250) NOT NULL,
                              career VARCHAR(250) DEFAULT NULL
);

INSERT INTO billionaires (first_name, last_name, career) VALUES
('Aliko', 'Dangote', 'Billionaire Industrialist'),
('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');




select TO_char(TMST_ACCESSO, 'dd/MM/yyyy') as DATA, BANCA_SSO, count(*) as conteggioAccessi
from STATISTICHE_ACCESSO
where TO_char(TMST_ACCESSO, 'yyyy/MM') >= '2020/10' and FLG_ACCESSO_SSO = 'S'
group by BANCA_SSO, TO_char(TMST_ACCESSO, 'dd/MM/yyyy')
order by TO_date(TO_char(TMST_ACCESSO, 'dd/MM/yyyy'), 'dd/MM/yyyy') desc
