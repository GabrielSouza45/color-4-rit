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
        String sql = "`ID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "  `PONTUACAO` INT,\n" +
                "  `FK-JOGADOR` BIGINT UNSIGNED,\n" +
                "  `FK_MUSICA` BIGINT UNSIGNED,\n" +
                "  FOREIGN KEY (`FK_JOGADOR`) REFERENCES `JOGADOR`(`ID`),\n" +
                "  FOREIGN KEY (`FK_MUSICA`) REFERENCES `MUSICA`(`ID`)\n" +
                ");";

        PreparedStatement ps = null;

        try {
            ps.getConnection().prepareStatement(sql);
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
                placar.setMusica(new MusicaDao().listarPorId(res.getLong("FK_MUSICA")));
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
        String sql = "SELECT * FROM Placar WHERE ID = ?";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, id);

            ResultSet res = ps.executeQuery();
            Placar placar = new Placar();

            if (res.next()) {
                placar.setId(res.getLong("ID"));
                placar.setPontuacao(res.getInt("PONTUACAO"));
                placar.setMusica(new MusicaDao().listarPorId(res.getLong("FK_MUSICA")));
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
        String sql = "INSERT INTO PLACAR (PONTUACAO, FK_MUSICA, FK_JOGADOR) VALUES(?,?,?);";

        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getPontuacao());
            ps.setLong(2, objeto.getMusica().getId());
            ps.setLong(3, objeto.getJogador().getId());

            ps.execute();

        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public void editar(Placar objeto) {
        String sql = "UPDATE PLACAR SET " +
                "PONTUACAO = ?, FK_MUSICA = ?, FK_JOGADOR = ?" +
                "WHERE ID = ?";
        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getPontuacao());
            ps.setLong(2, objeto.getMusica().getId());
            ps.setLong(3, objeto.getJogador().getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Placar objeto) {
        String sql = "DELETE FROM PLACAR WHERE ID = ? ";
        try {

            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
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

    public List<Placar> listarMusicaPorId(Musica musica) {
        String sql = "SELECT * FROM ID" +
                " WHERE ID_MUSICA = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, musica.getId());

            ResultSet res = ps.executeQuery();

            List<Placar> placars = new ArrayList<>();

            if (res.next()) {
                Placar placar = new Placar();
                placar.setId(res.getLong("ID"));
                placar.setPontuacao(res.getInt("PONTUACAO"));
                placar.setMusica(new MusicaDao().listarPorId(res.getLong("FK_MUSICA")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("FK_JOGADOR")));


                placars.add(placar);
            }

            return placars;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Placar> listarPorJogador(Jogador jogador) {
        String sql = "SELECT * FROM PLACAR" +
                " WHERE ID_MUSICA = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, jogador.getId());

            ResultSet res = ps.executeQuery();

            List<Placar> placars = new ArrayList<>();

            if (res.next()) {
                Placar placar = new Placar();
                placar.setId(res.getLong("ID"));
                placar.setPontuacao(res.getInt("PONTUACAO"));
                placar.setMusica(new MusicaDao().listarPorId(res.getLong("FK_MUSICA")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("FK_JOGADOR")));


                placars.add(placar);
            }
            return placars;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
