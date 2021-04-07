package com.salvio.repository;

import com.salvio.StartApplication;
import com.salvio.entitys.Anagrafica;
import com.salvio.entitys.Polizza;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = StartApplication.class)
public class AnagraficaRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AnagraficaRepository anagraficaRepository;

    @Test
    public void getAllAnagraficheTest() {

        anagraficaRepository.insert(new Anagrafica(9999,"mario","rossi","1234567890123456"));
        anagraficaRepository.insert(new Anagrafica(2222,"ciccio","pasticcio","bello13f9809a") );
        anagraficaRepository.insert(new Anagrafica(3333,"jonh","smith","giova13f9809a") );
        anagraficaRepository.insert(new Anagrafica (8888,"gennaro","esposito","gnnsps1234567890") );

        List<Anagrafica> result = anagraficaRepository.getAllAnagrafiche();
        Anagrafica anagrafica= anagraficaRepository.ottieniAnagraficaAttraversoIdAnagrafica(9999);
        Polizza polizzaTest= new Polizza(1, 9999, 9999, 9999);
        List <Anagrafica> list =anagraficaRepository.getListaAnagraficheAssociateAPolizza(polizzaTest);


        assertThat(anagrafica).isEqualTo(Anagrafica.builder().id(9999).nome("mario").cognome("rossi").codiceFiscale("1234567890123456").build());
        assertThat(list.size()).isEqualTo(3);
        assertThat(result.get(3))
            .isEqualTo(
                Anagrafica.builder()
                    .id(9999)
                    .nome("mario")
                    .cognome("rossi")
                    .codiceFiscale("1234567890123456")
                    .build());

        assertThat(result.get(0))
                .isEqualTo(
                        Anagrafica.builder()
                                .id(2222)
                                .nome("ciccio")
                                .cognome("pasticcio")
                                .codiceFiscale("bello13f9809a")
                                .build());

        assertThat(result.get(1))
                .isEqualTo(
                        Anagrafica.builder()
                                .id(3333)
                                .nome("jonh")
                                .cognome("smith")
                                .codiceFiscale("giova13f9809a")
                                .build());
        assertThat(result.get(2))
                .isEqualTo(
                        Anagrafica.builder()
                                .id(8888)
                                .nome("gennaro")
                                .cognome("esposito")
                                .codiceFiscale("gnnsps1234567890")
                                .build());

    }

    //il test probabilmente va in rosso perchè la lista ritorna le anagrafiche in ordine di id e gli altri inseriti hanno id più alti di 5 o 6
    @Test
    public void getAll2() {

        jdbcTemplate.update(
                "INSERT INTO anagrafica (id, nome,cognome,codiceFiscale) VALUES (?, ?, ?, ?)",
                5,
                "pinco",
                "pallino",
                "pinco13f9809a");
        jdbcTemplate.update(
                "INSERT INTO anagrafica (id, nome,cognome,codiceFiscale) VALUES (?, ?, ?, ?)",
                6,
                "giuseppe",
                "garibaldi",
                "gariba13f9809a");

        List<Anagrafica> result = anagraficaRepository.getAllAnagrafiche();

        assertThat(result.get(4))
                .isEqualTo(
                        Anagrafica.builder()
                                .id(5)
                                .nome("pinco")
                                .cognome("pallino")
                                .codiceFiscale("pinco13f9809a")
                                .build());
        assertThat(result.get(5))
                .isEqualTo(
                        Anagrafica.builder()
                                .id(6)
                                .nome("giuseppe")
                                .cognome("garibaldi")
                                .codiceFiscale("gariba13f9809a")
                                .build());
    }
}
