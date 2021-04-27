package com.salvio.simulazioneprogettoarca.service;

import com.salvio.simulazioneprogettoarca.db.SupermercatoDB;
import com.salvio.simulazioneprogettoarca.dto.RichiestaProdottoDto;
import com.salvio.simulazioneprogettoarca.repository.RichiestaInfoMagazzinoProdottiRepository;
import com.salvio.simulazioneprogettoarca.repository.RichiestaInfoSupermercatoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RichiestaProdottoSupermercatoService {

  private final RichiestaInfoSupermercatoRepository richiestaInfoSupermercatoRepository;
  private final RichiestaInfoMagazzinoProdottiRepository richiestaInfoMagazzinoProdottiRepository;

  public RichiestaProdottoSupermercatoService(
      RichiestaInfoSupermercatoRepository richiestaInfoSupermercatoRepository,
      RichiestaInfoMagazzinoProdottiRepository richiestaInfoMagazzinoProdottiRepository) {
    this.richiestaInfoSupermercatoRepository = richiestaInfoSupermercatoRepository;
    this.richiestaInfoMagazzinoProdottiRepository = richiestaInfoMagazzinoProdottiRepository;
  }

  public List<SupermercatoDB> ottieniSupermercatiValidiAttraverso(RichiestaProdottoDto richiestaDto) {

    List<SupermercatoDB> listaFiltrata = richiestaInfoSupermercatoRepository
        .estraiListaFiltrataPer(richiestaDto.getProvinciaRichiesta());
    List<Integer> listaIdSuper = richiestaInfoMagazzinoProdottiRepository
        .estraiIdSupermercatiDisponibili(richiestaDto.getNomeProdottoRichiesto(), richiestaDto.getNumerositaRichiesta());
    List<SupermercatoDB> listaDaRestituire= new ArrayList<>();
    for (Integer id: listaIdSuper){

      for(SupermercatoDB sup:listaFiltrata ){

        if(sup.getIdSupermercato().equals(id)){
          listaDaRestituire.add(sup);
        }
      }

      }

    return listaDaRestituire;

  }
}
