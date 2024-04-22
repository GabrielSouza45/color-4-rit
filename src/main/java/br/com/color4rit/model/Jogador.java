package br.com.color4rit.model;


public class Jogador {

    private Long id;
    private String nome;
    private String login;

    private String senha;


    public Jogador(String nome, String login,String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public Jogador(){}

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
