package br.com.color4rit.model;

import br.com.color4rit.enums.Cor;

import java.sql.Time;

public class Nota {

    private Long id;
    private Mapa mapa;
    private Cor cor;
    private Time tempo;

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

    public Time getTempo() {
        return tempo;
    }

    public void setTempo(Time tempo) {
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
