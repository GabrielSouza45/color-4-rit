package br.com.color4rit.controller;

import br.com.color4rit.enums.Cor;
import br.com.color4rit.model.Musica;
import br.com.color4rit.model.Nota;
import br.com.color4rit.repository.CrudDao;
import com.sun.source.tree.TryTree;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicaDao extends ConectarDao implements CrudDao<Musica> {

    @Override
    public void criarTabela() {
        String sql = "`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "  `nome` VARCHAR(255),\n" +
                "  `autor` VARCHAR(255),\n" +
                "  `duracao` TIME\n" +
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
    public List<Musica> listarTodos() {
        String sql = "SELECT * FROM Musica";

        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            List<Musica> musicas = new ArrayList<>();

            while (rs.next()) {
                Musica musica = new Musica();

                musica.setId(rs.getLong("id"));
                musica.setNome(rs.getString("nome"));
                musica.setAutor(rs.getString("autor"));

                musicas.add(musica);
            }

            return musicas;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public Musica listarPorId(long id) {
        String sql = "SELECT * FROM Musica WHERE id = ?";

        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);

            ps.setLong(1, id);

            ResultSet res = ps.executeQuery();

            if (res.next()) {
                Musica musica = new Musica();
                musica.setId(res.getLong("ID"));
                musica.setNome(res.getString("NOME"));
                musica.setAutor(res.getString("AUTOR"));
                musica.setDuracao(res.getTime("DURACAO"));

                return musica;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cadastrar(Musica objeto) {
        String sql = "INSERT INTO Musica (nome, autor) VALUES(?,?);";

        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setString(1, (objeto.getNome()));
            ps.setString(2, objeto.getAutor());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Musica cadastrada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar a Musica. \n" + e.getMessage());
        }
    }

    @Override
    public void editar(Musica objeto) {
        String sql = "UPDATE Musica SET " +
                "nome = ?,autor = ?" +
                "WHERE id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getAutor());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Musica atualizada com sucesso");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar Musica. \n" + e.getMessage());
        }
    }

    @Override
    public void excluir(Musica objeto) {
        String sql = "DELETE FROM Musica WHERE id = ? ";
        try {

            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setLong(1, objeto.getId());

            int rowCount = ps.executeUpdate();

            if (rowCount > 0) {
                JOptionPane.showMessageDialog(null, "Musica excluida com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Musica não encontrada com o id fornecido.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar Musica.\n" + e.getMessage());
        }
    }
}
