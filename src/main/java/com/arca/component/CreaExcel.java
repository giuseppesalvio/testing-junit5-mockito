package com.arca.component;

public class CreaExcel {

  private final Writer writer;

  public CreaExcel(Writer writer) {
    this.writer = writer;
  }


  public void crea() {
    writer.write();
  }
}
