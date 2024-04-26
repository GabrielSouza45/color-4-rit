package br.com.color4rit.controller;

import br.com.color4rit.enums.Cor;
import br.com.color4rit.model.Mapa;
import br.com.color4rit.model.Nota;
import br.com.color4rit.repository.CrudDao;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaDao extends ConectarDao implements CrudDao<Nota> {


    @Override
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS `Nota` (\n" +
                "  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "  `fk_mapa` BIGINT UNSIGNED,\n" +
                "  `cor` VARCHAR(50),\n" +
                "  `tempo` TIME,\n" +
                "  FOREIGN KEY (`fk_mapa`) REFERENCES `Mapa`(`id`)";

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
    public List<Nota> listarTodos() {
        String sql = "SELECT * FROM Nota";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ResultSet res = ps.executeQuery();

            List<Nota> notas = new ArrayList<>();

            while (res.next()) {
                Nota notinha = new Nota();

                notinha.setId(res.getLong("id"));
                notinha.setCor(Cor.valueOf(res.getString("cor")));
                notinha.setTempo(res.getTime("tempo"));
                notinha.setMapa(new MapaDao().listarPorId(res.getLong("fk_mapa")));

                notas.add(notinha);
            }

            return notas;
        } catch (
                SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }


    public Nota listarPorId(long id) {
        String sql = "SELECT * FROM Nota WHERE id = ?";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, id);

            ResultSet res = ps.executeQuery();
            Nota nota = new Nota();

            if (res.next()) {
                nota.setId(res.getLong("id"));
                nota.setCor(Cor.valueOf(res.getString("cor")));
                nota.setTempo(res.getTime("tempo"));
                nota.setMapa(new MapaDao().listarPorId(res.getLong("fk_mapa")));

                return nota;
            } else {
                throw new RuntimeException("Erro ao encontrar a Nota");
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void cadastrar(Nota objeto) {
        String sql = "INSERT INTO Nota (cor, tempo, fk_mapa) VALUES(?,?,?);";

        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setString(1, String.valueOf(objeto.getCor()));
            ps.setTime(2, objeto.getTempo());
            ps.setLong(3, objeto.getMapa().getId());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Nota cadastrada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar a Nota. \n" + e.getMessage());
        }
    }

    @Override
    public void editar(Nota objeto) {
        String sql = "UPDATE Nota SET " +
                "cor = ?, tempo = ?, fk_mapa = ?" +
                "WHERE id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setString(1, String.valueOf(objeto.getCor()));
            ps.setTime(2, objeto.getTempo());
            ps.setLong(3, objeto.getMapa().getId());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Nota atualizada com sucesso");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar a Nota. \n" + e.getMessage());
        }
    }

    @Override
    public void excluir(Nota objeto) {
        String sql = "DELETE FROM Nota WHERE id = ? ";
        try {

            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setLong(1, objeto.getId());

            int rowCount = ps.executeUpdate();

            if (rowCount > 0) {
                JOptionPane.showMessageDialog(null, "Nota excluida com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Nota não encontrada com o id fornecido.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar a Nota.\n" + e.getMessage());
        }
    }

    public List<Nota> listarPorMapa(Mapa mapa){
        String sql = "SELECT * FROM NOTA" +
                " WHERE FK_MAPA = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, mapa.getId());

            ResultSet rs = ps.executeQuery();

            List<Nota> notas = new ArrayList<>();

            while (rs.next()) {

                Nota nota = new Nota();
                nota.setId(rs.getLong("ID"));
                nota.setTempo(rs.getTime("TEMPO"));
                nota.setCor(Cor.valueOf(rs.getString("COR")));
                nota.setMapa(mapa);

                notas.add(nota);
            }

            return notas;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
