package com.salvio.repository;

import com.salvio.StartApplication;
import com.salvio.simulazioneprogettoarca.db.PersonaDB;
import com.salvio.simulazioneprogettoarca.dto.DatiAnagraficiDTO;
import com.salvio.simulazioneprogettoarca.repository.PersonaRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes= StartApplication.class)
public class PersonaRepositoryIntegrationTest {

  @Autowired
  private PersonaRepository personaRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void verificaPersonaRepositoryTestOk(){

    DatiAnagraficiDTO inputDto= new DatiAnagraficiDTO("mario","rossi");

    inserisciPersonaDB(jdbcTemplate,1,"mario","rossi","mrossi70",99999);
    inserisciPersonaDB(jdbcTemplate,2,"mario","rossi","mrossi95",33333);
    inserisciPersonaDB(jdbcTemplate,3,"luigi","bianchi","lbianchi60",66666);

    List<PersonaDB> listaEstratta= personaRepository.estraiAttraverso(inputDto);

    Assertions.assertThat(listaEstratta.size()).isEqualTo(2);
  }

  public static void inserisciPersonaDB(JdbcTemplate jdbcTemplate,Integer id,String nome,String cognome,String codiceFiscale,Integer indirizzo){

    jdbcTemplate.update("insert into Persona(id,nome,cognome,codiceFiscale,indirizzo) values(?,?,?,?,?)",

        id,
        nome,
        cognome,
        codiceFiscale,
        indirizzo);


  }

}
