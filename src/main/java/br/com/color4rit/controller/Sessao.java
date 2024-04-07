package br.com.color4rit.controller;

import br.com.color4rit.controller.Jogador;
import br.com.color4rit.controller.Musica;

public class Sessao {

    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
