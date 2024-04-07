package br.com.color4rit.controller;

public class Mapa {

    private int id;

    private Enum dificuldade;

    private Musica musica;

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Mapa{" +
                "id=" + id +
                ", dificuldade=" + dificuldade +
                ", musica=" + musica +
                '}';
    }
}
