package br.com.color4rit.model;

import br.com.color4rit.enums.Cor;
import br.com.color4rit.enums.Dificuldade;

public class Mapa {

    private Long id;

    private Dificuldade dificuldade;

    private Musica musica;


    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
