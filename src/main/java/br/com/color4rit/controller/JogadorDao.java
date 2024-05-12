package br.com.color4rit.controller;

import br.com.color4rit.model.Jogador;
import br.com.color4rit.model.Musica;
import br.com.color4rit.repository.CrudDao;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogadorDao extends ConectarDao implements CrudDao<Jogador> {

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS `JOGADOR` (" +
                "`ID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "`NOME` VARCHAR(255)," +
                "`LOGIN` VARCHAR(255)," +
                "`SENHA` VARCHAR(255)" +
                ");";

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
        String sql = "SELECT * FROM JOGADOR";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ResultSet res = ps.executeQuery();

            List<Jogador> jogadores = new ArrayList<>();

            while (res.next()) {
                Jogador jogador = new Jogador();

                jogador.setId(res.getLong("ID"));
                jogador.setNome(res.getString("NOME"));
                jogador.setLogin(res.getString("LOGIN"));
                jogador.setSenha(res.getString("SENHA"));

                jogadores.add(jogador);
            }

            return jogadores;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Jogador listarPorLogin(String login) {
        String sql = "SELECT * FROM JOGADOR WHERE LOGIN = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setString(1, login);

            ResultSet res = ps.executeQuery();

            Jogador objeto = new Jogador();

            if (res.next()){
                objeto.setId(res.getLong("ID"));
                objeto.setNome(res.getString("NOME"));
                objeto.setLogin(res.getString("LOGIN"));
                objeto.setSenha(res.getString("SENHA"));
                return objeto;
            }

            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public Jogador listarPorId(long id) {
        String sql = "SELECT * FROM JOGADOR WHERE ID = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, id);

            ResultSet res = ps.executeQuery();

            Jogador objeto = new Jogador();

            if (res.next()){
                objeto.setId(res.getLong("ID"));
                objeto.setNome(res.getString("NOME"));
                objeto.setLogin(res.getString("LOGIN"));
                objeto.setSenha(res.getString("SENHA"));
                return objeto;
            }

            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void cadastrar(Jogador objeto) {

        String sql = "INSERT INTO JOGADOR (NOME, LOGIN, SENHA) VALUES(?,?,?);";
        try{

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getLogin());
            ps.setString(3, objeto.getSenha());

            ps.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void editar(Jogador objeto) {
        String sql = "UPDATE JOGADOR SET" +
                "NOME = ?, LOGIN = ?, SENHA = ?" +
                "WHERE ID = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getLogin());
            ps.setString(3, objeto.getSenha());

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void excluir(Jogador objeto) {
        String sql = "DELETE FROM JOGADOR WHERE ID = ? ";
        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, objeto.getId());

            int rowCount = ps.executeUpdate();

            if (rowCount > 0){
                System.out.println("Jogador excluido com sucesso!");
            }else {
                System.out.println("Jogador não encontrado com o id fornecido.");
            }
        }catch(SQLException e){
            e.printStackTrace();
            }
        }
    }
