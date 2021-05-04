package com.roberto.services;

import com.roberto.response.AziendaAssociataResponse;
import com.roberto.entitys.AnagraficaAzienda;
import com.roberto.entitys.Azienda;
import com.roberto.entitys.Dipendente;
import com.roberto.repository.GestioneAnagraficheRepository;
import com.roberto.repository.GestioneAziendeRepository;
import com.roberto.repository.GestioneDipendentiRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GestioneDipendentiService {

  private final GestioneDipendentiRepository gestioneDipendentiRepository;
  private final GestioneAziendeRepository gestioneAziendeRepository;
  private final GestioneAnagraficheRepository gestioneAnagraficheRepository;

  public GestioneDipendentiService(GestioneDipendentiRepository gestioneDipendentiRepository,
      GestioneAziendeRepository gestioneAziendeRepository,
      GestioneAnagraficheRepository gestioneAnagraficheRepository) {
    this.gestioneDipendentiRepository = gestioneDipendentiRepository;
    this.gestioneAziendeRepository = gestioneAziendeRepository;
    this.gestioneAnagraficheRepository = gestioneAnagraficheRepository;
  }


  public List<AziendaAssociataResponse> getAziendeCorrelate(String codiceFiscale) {

    List<AziendaAssociataResponse> listaResponse = new ArrayList<>();
    AnagraficaAzienda anagraficaAzienda = gestioneAnagraficheRepository.getAnagraficaCorrelataA(codiceFiscale);
    Dipendente dipendente = gestioneDipendentiRepository.getDipendenteAttraverso(anagraficaAzienda.getIdAnagrafica());
    Azienda infoAziendaOttenute = gestioneAziendeRepository.getInfoAziendaBy(dipendente.getCodiceAzienda());

    AnagraficaAzienda proprietario = gestioneAnagraficheRepository
        .getAnagraficaCorrelataA(infoAziendaOttenute.getCodiceFiscaleProprietarioAzienda());

    List<Dipendente> listaDipendenti = gestioneDipendentiRepository.getListaDipendentiBy(infoAziendaOttenute.getCodiceAzienda());

    Double monteStipendi = 0.0;

    for (Dipendente dipendenteA : listaDipendenti) {
      monteStipendi += dipendenteA.getStipendio();
    }

    listaResponse.add(AziendaAssociataResponse.builder().codiceAzienda(infoAziendaOttenute.getCodiceAzienda())
        .nomeAzienda(infoAziendaOttenute.getNomeAzienda()).nomeProprietario(proprietario.getNome())
        .cognomeProprietario(proprietario.getCognome()).dataFondazione(infoAziendaOttenute.getDataFondazione())
        .nazione(infoAziendaOttenute.getNazione()).numeroDipendenti(listaDipendenti.size()).monteStipendiTotale(monteStipendi)
        .build());

    return listaResponse;
  }
}

