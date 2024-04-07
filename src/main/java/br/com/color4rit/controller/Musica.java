package br.com.color4rit.controller;

import java.sql.Blob;

public class Musica {

    private String nome;
    private String autor;
    private int id;
    private Blob musica;



    // precisa ser mexido para conversao do blob para a msc
   // public Blob getMusica() {
     //   return musica;
    //}

    //public void setMusica(Blob musica) {
     //   this.musica = musica;
    //}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
