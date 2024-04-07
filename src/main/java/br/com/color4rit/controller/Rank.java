package br.com.color4rit.controller;

import br.com.color4rit.controller.Jogador;
import br.com.color4rit.controller.Musica;

public class Rank {

    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
