package br.com.color4rit.controller;

import br.com.color4rit.model.Jogador;
import br.com.color4rit.repository.CrudDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogadorDao extends ConectarDao implements CrudDao<Jogador> {

    public void criarTabela() {
        String sql = "";

        PreparedStatement ps = null;

        try {
            ps = getConexao().prepareStatement(sql);
            ps.execute();
            System.out.println("Jogador - Banco criado");
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Jogador> listarTodos() {
        String sql = "";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ResultSet res = ps.executeQuery();

            List<Jogador> jogadores = new ArrayList<>();

            while (res.next()) {
                Jogador jogador = new Jogador();

                jogador.setId(res.getLong(""));
                jogador.setNome(res.getString(""));
//                jogador.setSenha(res.getString(""));
                jogador.setLogin(res.getString(""));

                jogadores.add(jogador);
            }

            return jogadores;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cadastrar(Jogador objeto) {

        String sql = "";



    }

    public void editar(Jogador objeto) {

    }

    public void excluir(Jogador objeto) {

    }
}
