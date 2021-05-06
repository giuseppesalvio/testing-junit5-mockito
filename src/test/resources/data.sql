create table STATISTICHE_ACCESSO (
    ID_ACCESSO      number primary key,
    CF              varchar2(16 char),
    USERNAME        varchar2(30 char),
    STATO           varchar2(40 char),
    FLG_ACCESSO_SSO varchar2(1 char),
    BANCA_SSO       varchar2(10 char),
    TMST_ACCESSO    timestamp(3),
    DISPOSITIVO     varchar2(50 char)
);
