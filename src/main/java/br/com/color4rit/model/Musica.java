package br.com.color4rit.model;

import java.sql.Blob;
import java.sql.Time;

public class Musica {

    private String nome;
    private String autor;
    private int duracao;
    private Long id;
    private Blob musica;

    public Musica(String nome, String autor, Long id) {
        this.nome = nome;
        this.autor = autor;
        this.id = id;
    }

    public Musica() {
    }

    // precisa ser mexido para conversao do blob para a msc
   // public Blob getMusica() {
     //   return musica;
    //}

    //public void setMusica(Blob musica) {
     //   this.musica = musica;
    //}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "nome='" + nome + '\'' +
                ", autor='" + autor + '\'' +
                ", id=" + id +
                ", musica=" + musica +
                '}';
    }
}
