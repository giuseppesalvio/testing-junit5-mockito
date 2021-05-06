-- numero di registrazioni con registrazione veloce o con processo standard suddivise per stato.  suddivise per mese. (conto le utenze in un certo stato in ogni mese), nel 2021. Per ogni codice fiscale va preso lo stato più alto nel mese

select *
from (
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '10/2020'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '11/2020'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '12/2020'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '01/2021'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '02/2021'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '03/2021'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '04/2021'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '05/2021'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '06/2021'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '07/2021'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '08/2021'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '09/2021'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '10/2021'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '11/2021'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    )
    union
    select *
    from (
        select TO_char(TMST_REG, 'yyyy/MM') as DATA, ORIG, STATO, count(*)
        from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                 right join (select CF, max(TMST_REG) as MAX_TMST_REG
                             from HOMEINSURANCE.STATISTICHE_REGISTRAZIONE
                             where TO_CHAR(TMST_REG, 'MM/yyyy') = '12/2021'
                             group by CF) ULTIMOCF
        on STATISTICHE_REGISTRAZIONE.CF = ULTIMOCF.CF and STATISTICHE_REGISTRAZIONE.TMST_REG = ULTIMOCF.MAX_TMST_REG
        group by TO_char(TMST_REG, 'yyyy/MM'), STATO, ORIG
    ))
order by DATA desc
