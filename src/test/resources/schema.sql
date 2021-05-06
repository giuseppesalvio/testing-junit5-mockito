CREATE TABLE BOOK
(
    title            NCHAR(20),
    author          NCHAR(20),
    price       FLOAT
);

create table STATISTICHE_ACCESSO (
                                     ID_ACCESSO      number(15, 0) not null enable,
                                     CF              varchar2(16 char),
                                     USERNAME        varchar2(30 char),
                                     STATO           varchar2(40 char),
                                     FLG_ACCESSO_SSO varchar2(1 char),
                                     BANCA_SSO       varchar2(10 char),
                                     TMST_ACCESSO    timestamp(3) not null enable,
                                     DISPOSITIVO     varchar2(50 char)
);

create table STATISTICHE_REGISTRAZIONE (
                                           CF               varchar2(16 char),
                                           TIPO_POLIZZA_REG varchar2(10 char),
                                           STATO            varchar2(40 char),
                                           TMST_REG         timestamp(3) not null ENABLE,
                                           ORIG             varchar2(10 char),
                                           OPERAZIONE       varchar2(25 char),
                                           constraint STATISTICHE_REGISTRAZIONE_PK primary key (CF, TMST_REG)
);
