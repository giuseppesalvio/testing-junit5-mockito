--Numero di accessi (dettaglio giornaliero); diviso in accessi unici (se stessa persona accede due volte lo stesso giorno, conta 1) o non unici
select T_ACCESSIUNICI.GIORNOCONSIDERATO,
    T_ACCESSIUNICI.ACCESSIUNICIGG,
    (T_ACCESSITOTALI.CONTEGGIOACCESSITOTALIGG - T_ACCESSIUNICI.ACCESSIUNICIGG) as ACCESSINONUNICIGG
from (
    select GIORNOCONSIDERATO, count(*) as ACCESSIUNICIGG
    from (
        select TO_CHAR(TMST_ACCESSO, 'dd/MM/yyyy') as GIORNOCONSIDERATO
        from HOMEINSURANCE.STATISTICHE_ACCESSO
        where TO_char(TMST_ACCESSO, 'yyyy/MM') >= '2020/10'
        group by CF, TO_CHAR(TMST_ACCESSO, 'dd/MM/yyyy')
    )
    group by GIORNOCONSIDERATO
    order by TO_DATE(GIORNOCONSIDERATO, 'dd/MM/yyyy') desc
) T_ACCESSIUNICI
         join
(
    select GIORNOCONSIDERATO, count(*) as CONTEGGIOACCESSITOTALIGG
    from (
        select TO_CHAR(TMST_ACCESSO, 'dd/MM/yyyy') as GIORNOCONSIDERATO
        from HOMEINSURANCE.STATISTICHE_ACCESSO
        where TO_char(TMST_ACCESSO, 'yyyy/MM') >= '2020/10'
        group by ID_ACCESSO, TO_CHAR(TMST_ACCESSO, 'dd/MM/yyyy')
    )
    group by GIORNOCONSIDERATO
    order by TO_DATE(GIORNOCONSIDERATO, 'dd/MM/yyyy') desc
) T_ACCESSITOTALI on T_ACCESSIUNICI.GIORNOCONSIDERATO = T_ACCESSITOTALI.GIORNOCONSIDERATO
