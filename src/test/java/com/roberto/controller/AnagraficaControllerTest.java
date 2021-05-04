package com.roberto.controller;

import com.roberto.entitys.Anagrafica;
import com.roberto.repository.AnagraficaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class AnagraficaControllerTest {
    @InjectMocks
    private AnagraficaController anagraficaController;

    @Mock
    private AnagraficaRepository anagraficaRepository;

    @Test
    public void getAll_testTdd() {
        //arrange

        when(anagraficaRepository.getAllAnagrafiche()).thenReturn(getListaExpected());

        //execute
        List<Anagrafica> lista = anagraficaController.getAllTestTdd();
        
        //assertion
        verify(anagraficaRepository).getAllAnagrafiche();



//        List<Anagrafica> listaExpected = getListaExpected();
//        assertThat(listaExpected.size()).isEqualTo(lista.size());
//        for (int i = 0; i < listaExpected.size(); i++) {
//            assertThat(listaExpected.get(i).getId()).isEqualTo(lista.get(i).getId());
//            assertThat(listaExpected.get(i).getNome()).isEqualTo(lista.get(i).getNome());
//            assertThat(listaExpected.get(i).getCognome()).isEqualTo(lista.get(i).getCognome());
//            assertThat(listaExpected.get(i).getCodiceFiscale()).isEqualTo(lista.get(i).getCodiceFiscale());
//        }

    }

    private List<Anagrafica> getListaExpected() {
        List<Anagrafica> lista = new ArrayList<>();
        lista.add(new Anagrafica(1,"ciccio","pasticcio","ciccioCodFiscale"));
        lista.add(new Anagrafica(2,"pinco","pallino","pincoCodFiscale"));
        lista.add(new Anagrafica(3,"mario","esposito","marioCodFiscale"));
        return lista;
    }




    @Test
    public void getAll_testTdd_by_id() {
        when(anagraficaRepository.getAllAnagrafiche(11)).thenReturn(new ArrayList<>());

        anagraficaController.getAllTestTdd(11);

        verify(anagraficaRepository).getAllAnagrafiche(11);
    }
}
