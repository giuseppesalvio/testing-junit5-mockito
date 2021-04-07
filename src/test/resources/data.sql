CREATE TABLE ANAGRAFICA
(
    id            INT PRIMARY KEY,
    nome          NCHAR(20),
    cognome       NCHAR(20),
    codiceFiscale NCHAR(20)
);

CREATE TABLE POLIZZA
(
    id             INT PRIMARY KEY,
    idContraente   INT,
    idAssicurato   INT,
    idBeneficiario INT

);
