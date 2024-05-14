package br.com.color4rit.controller;

import br.com.color4rit.model.*;
import br.com.color4rit.repository.CrudDao;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlacarDao extends ConectarDao implements CrudDao<Placar> {


    @Override
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS `PLACAR`(" +
                "`ID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "  `PONTUACAO` INT,\n" +
                "  `FK_JOGADOR` BIGINT UNSIGNED,\n" +
                "  `FK_MAPA` BIGINT UNSIGNED,\n" +
                "  FOREIGN KEY (`FK_JOGADOR`) REFERENCES `JOGADOR`(`ID`),\n" +
                "  FOREIGN KEY (`FK_MAPA`) REFERENCES `MAPA`(`ID`)\n" +
                ");";

        PreparedStatement ps = null;

        try {
            ps = getConexao().prepareStatement(sql);
            ps.execute();
            System.out.println("Banco Criado");
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Placar> listarTodos() {
        String sql = "SELECT * FROM PLACAR";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ResultSet res = ps.executeQuery();

            List<Placar> placars = new ArrayList<>();

            while (res.next()) {
                Placar placar = new Placar();

                placar.setId(res.getLong("ID"));
                placar.setPontuacao(res.getInt("PONTUACAO"));
                placar.setMapa(new MapaDao().listarPorId(res.getLong("FK_MAPA")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("FK_JOGADOR")));

                placars.add(placar);
            }

            return placars;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Placar listarPorId(long id) {
        String sql = "SELECT * FROM PLACAR WHERE ID = ?";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, id);

            ResultSet res = ps.executeQuery();
            Placar placar = new Placar();

            if (res.next()) {
                placar.setId(res.getLong("ID"));
                placar.setPontuacao(res.getInt("PONTUACAO"));
                placar.setMapa(new MapaDao().listarPorId(res.getLong("FK_MAPA")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("FK_JOGADOR")));

                return placar;
            } else {
                return null;
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cadastrar(Placar objeto) {
        String sql = "INSERT INTO PLACAR (PONTUACAO, FK_MAPA, FK_JOGADOR) VALUES(?,?,?);";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setInt(1, objeto.getPontuacao());
            ps.setLong(2, objeto.getMapa().getId());
            ps.setLong(3, objeto.getJogador().getId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editar(Placar objeto) {
        String sql = "UPDATE PLACAR SET " +
                "PONTUACAO = ?, FK_MAPA = ?, FK_JOGADOR = ?" +
                "WHERE ID = ?";
        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setInt(1, objeto.getPontuacao());
            ps.setLong(2, objeto.getMapa().getId());
            ps.setLong(3, objeto.getJogador().getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Placar objeto) {
        String sql = "DELETE FROM PLACAR WHERE ID = ? ";
        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, objeto.getId());

            int rowCount = ps.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Placar excluido com sucesso!");
            } else {
                System.out.println("Placar não encontrado com o id fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Placar listarPorJogador(Long idJogador, Long idMapa) {
        String sql = "SELECT * FROM PLACAR" +
                " WHERE FK_JOGADOR = ? " +
                " AND FK_MAPA = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, idJogador);
            ps.setLong(2, idMapa);

            ResultSet res = ps.executeQuery();

            Placar placar = null;

            if (res.next()) {
                placar = new Placar();

                placar.setId(res.getLong("ID"));
                placar.setPontuacao(res.getInt("PONTUACAO"));
                placar.setMapa(new MapaDao().listarPorId(res.getLong("FK_MAPA")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("FK_JOGADOR")));

            }
            return placar;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Placar> listarPorMapa(Long idMapa) {
        String sql = "SELECT * FROM PLACAR" +
                " WHERE FK_MAPA = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, idMapa);

            ResultSet res = ps.executeQuery();
            ArrayList<Placar> placares = new ArrayList<>();

            while (res.next()) {
                Placar placar = new Placar();
                placar.setId(res.getLong("ID"));
                placar.setPontuacao(res.getInt("PONTUACAO"));
                placar.setMapa(new MapaDao().listarPorId(res.getLong("FK_MAPA")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("FK_JOGADOR")));

                placares.add(placar);
            }
            return placares;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}