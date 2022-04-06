package com.trybe.simuladordepix;

public class ErroValorNaoPositivo extends ErroDePix {
  public ErroValorNaoPositivo() {
    super("Valor a ser transferido menor ou igual a zero");
  }
}
