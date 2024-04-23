package br.com.color4rit.model;

import java.sql.Blob;

public class Musica {

    private String nome;
    private String autor;
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
