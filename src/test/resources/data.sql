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

    numeroTarga                 nchar primary key,
    codiceFiscaleProprietario   nchar(16),
    P_IvaAssicurazioneAssociata nchar(20),
    numeroPolizzaAssociata      int
);

create table assicurazione
(
    P_IVA nchar(20) primary key,
    nome  nchar(20)
);

create table polizzaEstesa
(
    numeroPolizza          int primary Key,
    idContraente           int,
    idAssicurato           int,
    idBeneficiario         int,
    dataProxQuietanzamento nchar(10),
    importoQuietanzamento  double

);


create table anagraficaEstesa
(


    idAnagrafica  int primary Key,
    nome          nchar(20),
    cognome       nchar(20),
    codiceFiscale nchar(20)

);



create table riepilogoAnagrafica
(


    idAnagrafica  int primary Key,
    nome          nchar(20),
    cognome       nchar(20),
    codiceFiscale nchar(20)

);

Create table riepilogoAutomobile
(
    numeroTarga               nchar primary key,
    codiceFiscaleProprietario nchar(16),
    codiceAssicurazione       nchar(20),
    numeroPolizzaAssociata    int
);


create table riepilogoPolizza
(
    numeroPolizza          int primary Key,
    idContraente           int,
    idAssicurato           int,
    idBeneficiario         int,
    dataProxQuietanzamento nchar(10),
    importoQuietanzamento  double

);



create table Azienda
(

    codiceAzienda                    nchar(10) primary key,
    nomeAzienda                      nchar(20),
    codiceFiscaleProprietarioAzienda nchar(10),
    dataFondazione                   nchar(10),
    nazione                          nchar(20)

);


create table Dipendente
(

    idDipendente   Integer primary Key,
    codiceAzienda  nchar(10) not null,
    ruolo          nchar(15),
    dataAssunzione nchar(10),
    stipendio      DOUBLE


);

create table AnagraficaAziende
(


    idAnagrafica  int primary Key,
    nome          nchar(20),
    cognome       nchar(20),
    codiceFiscale nchar(20)

);


create table ProdottoSupermercato
(


    codiceProdotto      nchar(10) primary key,
    nomeProdotto        nchar(15),
    costoProdotto       double,
    provenienzaProdotto nchar(20),
    scadenzaProdotto    nchar(10),
    categoriaProdotto   nchar(15)
);


create table InfoMagazzinoSupermercato
(

    codiceProdotto nchar(10) primary key,
    disponibilita  int

);


create table persona
(
    id            int primary key,
    nome          nchar(25),
    cognome       nchar(25),
    codiceFiscale nchar(16),
    indirizzo     int
);

create table indirizzo
(
    id        int primary key,
    via       nchar(20),
    citta     nchar(20),
    provincia nchar(20),
    cap       int
);



create table SupermercatoDB
(


    idSupermercato   int primary key,
    nomeSupermercato nchar(20),
    via              nchar(20),
    citta            nchar(20),
    provincia        nchar(20)

);

create table MagazzinoProdottiDB
(


    idSupermercato int,
    codiceProdotto int,
    nomeProdotto   nchar(20),
    costoUnitario  double,
    disponibilita  int

);



create table AziendaDB
(

    pIvaAzienda               int primary key,
    codiceFiscaleProprietario nchar(10),
    nomeAzienda               nchar(20),
    nazione                   nchar(20),
    dataFondazione            date

);

create table DipendenteDB
(

    pIvaAzienda    int,
    codiceFiscale  nchar(10),
    nome           nchar(20),
    cognome        nchar(20),
    ruolo          nchar(20),
    stipendio      double,
    dataAssunzione date

);



create table personaTestDB
(
    id            int primary key,
    nome          nchar(25),
    cognome       nchar(25),
    codiceFiscale nchar(16),
    indirizzo     nchar(20),
    sesso         nchar(1)
);




