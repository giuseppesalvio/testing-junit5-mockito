package com.salvio.repository;

import com.salvio.domain.objects.DettaglioPolizzaDomain.AnagraficaDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AnagraficaDomainRepository {
  @Autowired
  JdbcTemplate jdbcTemplate;

  public AnagraficaDomain ottieniAnagraficaAttraversoCodiceFiscale(String codiceFiscale) {
    return (AnagraficaDomain) jdbcTemplate.queryForObject("select * from anagrafica where codiceFiscale = ? ",
        new Object[]{codiceFiscale}, new BeanPropertyRowMapper(AnagraficaDomain.class));
  }

  public boolean check(String codiceFiscale) {

    AnagraficaDomain anagraficaDomain= ottieniAnagraficaAttraversoCodiceFiscale(codiceFiscale);
    if(anagraficaDomain.getIdAnagrafica().equals(null))
        return false;
    else
      return true;
  }
}
