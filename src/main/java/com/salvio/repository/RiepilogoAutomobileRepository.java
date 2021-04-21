package com.salvio.repository;

import com.salvio.entitys.RiepilogoAutomobile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RiepilogoAutomobileRepository {

  private final JdbcTemplate jdbcTemplate;

  public RiepilogoAutomobileRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  public RiepilogoAutomobile getInfoAutomobileBy(String targaDaValidare) {

    return (RiepilogoAutomobile) jdbcTemplate.queryForObject("select * from riepilogoAutomobile where numeroTarga=?", new Object[]{targaDaValidare},
        new BeanPropertyRowMapper(RiepilogoAutomobile.class));
  }
}
