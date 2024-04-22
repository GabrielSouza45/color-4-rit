package br.com.color4rit.model;

import br.com.color4rit.enums.Cor;

public class Nota {

    private Long id;
    private Mapa mapa;
    private Cor cor;
    private Double tempo;

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

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Double getTempo() {
        return tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", mapa=" + mapa +
                ", cor=" + cor +
                ", tempo=" + tempo +
                '}';
    }
}
