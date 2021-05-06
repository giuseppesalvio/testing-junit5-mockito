--Numero di accessi effettuati con SSO (dettaglio giornaliero) e suddiviso per banca bper e sondrio
select TO_char(TMST_ACCESSO, 'dd/MM/yyyy') as DATA, BANCA_SSO, count(*) as conteggioAccessi
from STATISTICHE_ACCESSO
where TO_char(TMST_ACCESSO, 'yyyy/MM') >= '2020/10' and FLG_ACCESSO_SSO = 'S'
group by BANCA_SSO, TO_char(TMST_ACCESSO, 'dd/MM/yyyy')
order by TO_date(TO_char(TMST_ACCESSO, 'dd/MM/yyyy'), 'dd/MM/yyyy') desc

