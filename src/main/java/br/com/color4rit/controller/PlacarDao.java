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
        String sql = "`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "  `pontuacao` INT,\n" +
                "  `fk_jogador` BIGINT UNSIGNED,\n" +
                "  `fk_musica` BIGINT UNSIGNED,\n" +
                "  FOREIGN KEY (`fk_jogador`) REFERENCES `Jogador`(`id`),\n" +
                "  FOREIGN KEY (`fk_musica`) REFERENCES `Musica`(`id`)\n" +
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
        String sql = "SELECT * FROM Placar";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ResultSet res = ps.executeQuery();

            List<Placar> placars = new ArrayList<>();

            while (res.next()) {
                Placar placar = new Placar();

                placar.setId(res.getLong("id"));
                placar.setPontuacao(res.getInt("pontuacao"));
                placar.setMusica(new MusicaDao().listarPorId(res.getLong("fk_musica")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("fk_jogador")));

                placars.add(placar);
            }

            return placars;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }


    public Placar listarPorId(long id) {
        String sql = "SELECT * FROM Placar WHERE id = ?";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, id);

            ResultSet res = ps.executeQuery();
            Placar placar = new Placar();

            if (res.next()) {
                placar.setId(res.getLong("id"));
                placar.setPontuacao(res.getInt("pontuacao"));
                placar.setMusica(new MusicaDao().listarPorId(res.getLong("fk_musica")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("fk_jogador")));

                return placar;
            } else {
                throw new RuntimeException("Erro ao encontrar o Placar");
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cadastrar(Placar objeto) {
        String sql = "INSERT INTO Placar (pontuacao, fk_musica, fk_jogador) VALUES(?,?,?);";

        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getPontuacao());
            ps.setLong(2, objeto.getMusica().getId());
            ps.setLong(3, objeto.getJogador().getId());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Placar cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o Placar. \n" + e.getMessage());
        }
    }

    public void editar(Placar objeto) {
        String sql = "UPDATE Placar SET " +
                "pontuacao = ?, fk_musica = ?, fk_jogador = ?" +
                "WHERE id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getPontuacao());
            ps.setLong(2, objeto.getMusica().getId());
            ps.setLong(3, objeto.getJogador().getId());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Placar atualizado com sucesso");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar o Placar. \n" + e.getMessage());
        }
    }

    public void excluir(Placar objeto) {
        String sql = "DELETE FROM Placar WHERE id = ? ";
        try {

            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setLong(1, objeto.getId());

            int rowCount = ps.executeUpdate();

            if (rowCount > 0) {
                JOptionPane.showMessageDialog(null, "Placar excluido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Placar não encontrado com o id fornecido.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar o Placar.\n" + e.getMessage());
        }
    }

    public List<Placar> listarMusicaPorId(Musica musica) {
        String sql = "SELECT * FROM id" +
                " WHERE id_musica = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, musica.getId());

            ResultSet res = ps.executeQuery();

            List<Placar> placars = new ArrayList<>();

            if (res.next()) {
                Placar placar = new Placar();
                placar.setId(res.getLong("id"));
                placar.setPontuacao(res.getInt("pontuacao"));
                placar.setMusica(new MusicaDao().listarPorId(res.getLong("fk_musica")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("fk_jogador")));


                placars.add(placar);
            }

            return placars;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Placar> listarPorJogador(Jogador jogador) {
        String sql = "SELECT * FROM Placar" +
                " WHERE id_musica = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, jogador.getId());

            ResultSet res = ps.executeQuery();

            List<Placar> placars = new ArrayList<>();

            if (res.next()) {
                Placar placar = new Placar();
                placar.setId(res.getLong("id"));
                placar.setPontuacao(res.getInt("pontuacao"));
                placar.setMusica(new MusicaDao().listarPorId(res.getLong("fk_musica")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("fk_jogador")));


                placars.add(placar);
            }
            return placars;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
