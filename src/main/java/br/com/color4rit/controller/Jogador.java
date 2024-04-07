package br.com.color4rit.controller;

import sun.security.util.Password;

public class Jogador {

    private int id;
    private String nome;
    private String login;

    private Password senha;


    public Jogador(String nome, String login,Password senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public Password getSenha() {
        return senha;
    }

    public void setSenha(Password senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha=" + senha +
                '}';
    }
}
