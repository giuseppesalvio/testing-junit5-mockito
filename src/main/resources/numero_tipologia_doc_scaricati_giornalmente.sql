-- Numero e tipologia di documenti scaricati (dettaglio giornaliero)
select TO_char(TIMESTAMP, 'yyyy/MM/dd') as DATA, AZIONE as TIPO_DOCUMENTO, count(*) as NUMERO_DOWNLOAD
from HOMEINSURANCE.STATISTICHE
where TO_char(TIMESTAMP, 'yyyy/MM') >= '2020/10' and AZIONE like 'DOWNLOAD%'
group by AZIONE, TO_char(TIMESTAMP, 'yyyy/MM/dd')
order by TO_char(TIMESTAMP, 'yyyy/MM/dd') desc
