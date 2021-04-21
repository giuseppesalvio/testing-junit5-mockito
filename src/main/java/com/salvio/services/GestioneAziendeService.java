package com.salvio.services;

import com.salvio.Response.DipendentiAziendaResponse;
import com.salvio.entitys.AnagraficaAzienda;
import com.salvio.entitys.Dipendente;
import com.salvio.repository.GestioneAnagraficheRepository;
import com.salvio.repository.GestioneDipendentiRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GestioneAziendeService {

  private GestioneAnagraficheRepository gestioneAnagraficheRepository;
  private GestioneDipendentiRepository gestioneDipendentiRepository;

  public GestioneAziendeService(GestioneAnagraficheRepository gestioneAnagraficheRepository,
      GestioneDipendentiRepository gestioneDipendentiRepository) {
    this.gestioneAnagraficheRepository = gestioneAnagraficheRepository;
    this.gestioneDipendentiRepository = gestioneDipendentiRepository;
  }


  public List<DipendentiAziendaResponse> getAllBy(String codiceAzienda) {

    List<Dipendente> listaDipendenti = gestioneDipendentiRepository.getListaDipendentiBy(codiceAzienda);
    List<DipendentiAziendaResponse> responseList = new ArrayList<>();

    for (Dipendente dipendente : listaDipendenti) {

      AnagraficaAzienda anagraficaAzienda = gestioneAnagraficheRepository.getAnagraficaBy(dipendente.getIdDipendente());
      DipendentiAziendaResponse dipendenteResponse = DipendentiAziendaResponse.builder()
          .idDipendente(dipendente.getIdDipendente()).nome(anagraficaAzienda.getNome()).cognome(anagraficaAzienda.getCognome())
          .codiceFiscale(anagraficaAzienda.getCodiceFiscale()).stipendio(
              dipendente.getStipendio()).build();

      responseList.add(dipendenteResponse);

    }

    return responseList;
  }
}
