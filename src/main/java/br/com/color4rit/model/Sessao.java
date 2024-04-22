package br.com.color4rit.model;

public class Sessao {

    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "id=" + id +
                ", jogador=" + jogador +
                ", musica=" + musica +
                '}';
    }
}
