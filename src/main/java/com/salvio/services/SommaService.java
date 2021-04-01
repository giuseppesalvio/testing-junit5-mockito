package com.salvio.services;

import org.springframework.stereotype.Service;

@Service
public class SommaService {
  public int sommaXY(int x, int y) {
    return x + y;
  }

  public int sommaXYZ(int x, int y, int z) {
    return x + y + z;
  }
}
