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
        String sql = "CREATE TABLE IF NOT EXISTS `Jogador` (" +
                "`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "`nome` VARCHAR(255)," +
                "`login` VARCHAR(255)," +
                "`senha` VARCHAR(255));";

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
        String sql = "SELECT * FROM Jogador";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ResultSet res = ps.executeQuery();

            List<Jogador> jogadores = new ArrayList<>();

            while (res.next()) {
                Jogador jogador = new Jogador();

                jogador.setId(res.getLong(""));
                jogador.setNome(res.getString(""));
                jogador.setSenha(res.getString(""));
                jogador.setLogin(res.getString(""));

                jogadores.add(jogador);
            }

            return jogadores;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Jogador listarPorId(long id) {
        String sql = "SELECT * FROM Jogador WHERE id = ?";

        try {

            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet res = ps.executeQuery();

            Jogador objeto = new Jogador();

            if (res.next()){
                objeto.setId(res.getLong("id"));
                objeto.setNome(res.getString("nome"));
                objeto.setLogin(res.getString("login"));
                objeto.setSenha(res.getString("senha"));
                return objeto;
            }

            return null;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar Jogador. \n" + e.getMessage());
            return null;
        }
    }

    public void cadastrar(Jogador objeto) {

        String sql = "INSERT INTO Jogador (nome, login, senha) VALUES(?,?,?);";
        try{

            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getLogin());
            ps.setString(3, objeto.getSenha());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Jogador cadastrado com sucesso!");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar jogador. \n" + e.getMessage());
        }
    }

    public void editar(Jogador objeto) {
        String sql = "UPDATE Jogador SET" +
                "nome = ?, login = ?, senha = ?" +
                "WHERE id = ?";

        try {

            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getLogin());
            ps.setString(3, objeto.getSenha());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Jogador editado com sucesso!");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao editar o jogador. \n" + e.getMessage());
        }
    }

    public void excluir(Jogador objeto) {
        String sql = "DELETE FROM Jogador WHERE id = ? ";
        try {

            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setLong(1, objeto.getId());

            int rowCount = ps.executeUpdate();

            if (rowCount > 0){
                JOptionPane.showMessageDialog(null, "Jogador excluido com sucesso!");
            }else {
                JOptionPane.showMessageDialog(null, "Jogador não encontrado com o id fornecido.");
            }
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro ao deletar jogador.\n"+ e.getMessage());
            }
        }
    }
