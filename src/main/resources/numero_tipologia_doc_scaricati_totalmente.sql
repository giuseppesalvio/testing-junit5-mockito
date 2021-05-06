-- Numero e tipologia di documenti scaricati totalmente
select AZIONE as TIPO_DOCUMENTO, count(*) as NUMERO_DOWNLOAD
from (
    select CF, AZIONE, count(*)
    from HOMEINSURANCE.STATISTICHE
    where AZIONE like 'DOWNLOAD%'
    group by AZIONE, CF
)
group by AZIONE
