package br.com.color4rit.model;

public class PlacarRequest {

    private Long idMapa;
    private Long idJogador;

    private int pontuacao;

    public Long getIdMapa() {
        return idMapa;
    }

    public void setIdMapa(Long idMapa) {
        this.idMapa = idMapa;
    }

    public Long getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Long idJogador) {
        this.idJogador = idJogador;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
