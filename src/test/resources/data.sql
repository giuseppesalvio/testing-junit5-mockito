
CREATE TABLE ANAGRAFICA (
                            id INT  PRIMARY KEY,
                            nome NCHAR(20),
                            cognome  NCHAR(20),
                            codiceFiscale  NCHAR(20)
);





CREATE TABLE ANAGRAFICAProva (
                            idAnagrafica INT  PRIMARY KEY,
                            nome NCHAR(20),
                            cognome  NCHAR(20),
                            codiceFiscale  NCHAR(20)
);




CREATE TABLE Polizza (

                          id INT  PRIMARY KEY,
                          idContraente INT,
                          idAssicurato  INT,
                          idBeneficiario INT
);

CREATE TABLE PolizzaProva (

                         numeroPolizza INT  PRIMARY KEY,
                         idContraente INT,
                         idAssicurato  INT,
                         idBeneficiario INT
);