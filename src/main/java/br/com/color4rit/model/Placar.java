package br.com.color4rit.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Placar {

    private Long id;

    private int pontuacao;

    private Jogador jogador;
    private Mapa mapa;
    private Date dataIni;


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

    public LocalDateTime getDataIniLocalDateTime() {
        Instant instant = this.dataIni.toInstant();
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public Date getDataIni() {
        return dataIni;
    }

    public void setDataIni(Date dataIni) {
        this.dataIni = dataIni;
    }

    public void setDataIniLocalDateTime(LocalDateTime dataIni){
        Instant instant = dataIni.atZone(ZoneId.systemDefault()).toInstant();
        this.dataIni = Date.from(instant);
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
