package br.com.color4rit.model;

import br.com.color4rit.enums.Dificuldade;

public class MapaRequest {
    private Long idMusica;
    private Dificuldade dificuldade;

    public Long getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(Long idMusica) {
        this.idMusica = idMusica;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }
}
