
CREATE TABLE ANAGRAFICA (
                            id INT  PRIMARY KEY,
                            nome NCHAR(20),
                            cognome  NCHAR(20),
                            codiceFiscale  NCHAR(20)
);

INSERT INTO anagrafica  (id, nome,cognome,codiceFiscale) VALUES
(1, 'mario', 'rossi', 'mario13f9809a'),
(2, 'ciccio', 'pasticcio', 'bello13f9809a'),
(3, 'jonh', 'smith', 'giova13f9809a'),
(4, 'gennaro', 'esposito', 'genna13f9809a');
