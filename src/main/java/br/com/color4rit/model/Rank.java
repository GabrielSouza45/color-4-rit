package br.com.color4rit.model;

public class Rank {

    private Long id;

    private int pontuacao;

    private Jogador jogador;
    private Musica musica;



    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
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
    @Override
    public String toString() {
        return "Rank{" +
                "id=" + id +
                ", pontuacao=" + pontuacao +
                ", jogador=" + jogador +
                ", musica=" + musica +
                '}';
    }
}
