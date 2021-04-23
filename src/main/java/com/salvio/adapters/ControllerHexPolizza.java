package com.salvio.adapters;

import com.salvio.entitys.HexagonalPolizza;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ControllerHexPolizza {

  @PostMapping(value = "/getAllPolizza")
  public List<HexagonalPolizza> getAllPolizza(){

    return null;
  }

}
