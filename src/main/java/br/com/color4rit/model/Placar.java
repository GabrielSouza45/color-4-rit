package br.com.color4rit.model;

public class Placar {

    private Long id;

    private int pontuacao;

    private Jogador jogador;
    private Mapa mapa;
    private Long idMapa;
    private Long idJogador;

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



    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "id=" + id +
                ", pontuacao=" + pontuacao +
                ", jogador=" + jogador +
                ", musica=" + mapa +
                '}';
    }
}
