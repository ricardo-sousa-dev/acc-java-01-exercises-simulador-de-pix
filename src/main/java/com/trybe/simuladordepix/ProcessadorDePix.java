package com.trybe.simuladordepix;

import java.io.IOException;

public class ProcessadorDePix {

  private final Servidor servidor;

  public ProcessadorDePix(Servidor servidor) {
    this.servidor = servidor;
  }

  /**
   * Executa a operação do pix. Aqui é implementada a lógica de negócio sem envolver as interações
   * do aplicativo com a pessoa usuária.
   *
   * @param valor Valor em centavos a ser transferido.
   * @param chave Chave Pix do beneficiário da transação.
   * @throws ErroDePix Erro de aplicação, caso ocorra qualquer inconformidade.
   * @throws IOException Caso aconteça algum problema relacionado à comunicação entre o aplicativo e
   *     o servidor na nuvem.
   */
  public void executarPix(int valor, String chave) throws ErroDePix, IOException {
    try {
      if (valor <= 0) {
        throw new ErroValorNaoPositivo();
      }
      if (chave.isBlank()) {
        throw new ErroChaveEmBranco();
      }

      Conexao conexaoOK = servidor.abrirConexao();
      String retorno = conexaoOK.enviarPix(valor, chave);

      switch (retorno) {
        case "sucesso":
          System.out.println("Pix realizado com sucesso.");
          break;
        case "saldo_insuficiente":
          throw new ErroSaldoInsuficiente();
        case "chave_pix_nao_encontrada":
          throw new ErroChaveNaoEncontrada();
        default:
          throw new ErroInterno();
      }

    } finally {
      servidor.abrirConexao().close();
    }
  }
}
