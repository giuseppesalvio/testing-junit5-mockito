CREATE TABLE ANAGRAFICA
(
    id            INT PRIMARY KEY,
    nome          NCHAR(20),
    cognome       NCHAR(20),
    codiceFiscale NCHAR(20)
);


CREATE TABLE Polizza
(

    id             INT PRIMARY KEY,
    idContraente   INT,
    idAssicurato   INT,
    idBeneficiario INT
);


CREATE TABLE ANAGRAFICAProva
(
    idAnagrafica  INT PRIMARY KEY,
    nome          NCHAR(20),
    cognome       NCHAR(20),
    codiceFiscale NCHAR(20)
);


CREATE TABLE PolizzaProva
(

    numeroPolizza  INT PRIMARY KEY,
    idContraente   INT,
    idAssicurato   INT,
    idBeneficiario INT
);

Create table operazioniProva
(

    idOperazione             Int primary key,
    testoOperazione          nchar(50),
    numeroPolizzaInteressata int,
    idAnagraficaInteressata  int

);

Create table automobile
(

    numeroTarga nchar primary key,
    codiceFiscaleProprietario nchar(16),
    P_IvaAssicurazioneAssociata nchar(20),
    numeroPolizzaAssociata int
);

create table assicurazione
(
    P_IVA nchar(20) primary key,
    nome nchar(20)
);

create table polizzaEstesa(
    numeroPolizza int primary Key,
    idContraente int,
    idAssicurato  int ,
    idBeneficiario int ,
    dataProxQuietanzamento nchar(10) ,
    importoQuietanzamento double

);


create table anagraficaEstesa(


                              idAnagrafica     int primary Key,
                              nome         nchar(20),
                              cognome          nchar(20),
                              codiceFiscale         nchar(20)

);