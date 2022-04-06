package com.trybe.simuladordepix;

public class ErroValorNaoPositivo extends ErroDePix {
  public ErroValorNaoPositivo() {
    super("O valor do Pix não pode ser menor nem igual a zero.");
  }
}
