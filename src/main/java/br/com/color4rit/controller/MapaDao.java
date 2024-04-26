package br.com.color4rit.controller;

import br.com.color4rit.enums.Dificuldade;
import br.com.color4rit.model.Mapa;
import br.com.color4rit.model.Musica;
import br.com.color4rit.repository.CrudDao;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MapaDao extends ConectarDao implements CrudDao<Mapa> {


    @Override
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS `Mapa` (" +
                "`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "`dificuldade` VARCHAR(50), " +
                "`fk_musica` BIGINT UNSIGNED, " +
                "FOREIGN KEY (`fk_musica`) REFERENCES `Musica`(`id`)";

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
    public List<Mapa> listarTodos(){
        String sql ="SELECT * FROM Mapa";

        try {
        PreparedStatement ps = (PreparedStatement)
                getConexao().prepareStatement(sql);

        ResultSet res = ps.executeQuery();

        List<Mapa> mapas = new ArrayList<>();

        while (res.next()) {
            Mapa mapinha = new Mapa();

            mapinha.setId(res.getLong("id"));
            mapinha.setDificuldade(Dificuldade.valueOf(res.getString("dificuldade")));
            mapinha.setMusica(new MusicaDao().listarPorId(res.getLong("fk_musica")));

            mapas.add(mapinha);
        }

        return mapas;
    } catch(
    SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
        return null;
    }
}

    public Mapa listarPorId(long id) {

        String sql = "SELECT * FROM Mapa WHERE id = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Mapa mapa = new Mapa();
                mapa.setId(rs.getLong("ID"));
                mapa.setDificuldade(Dificuldade.valueOf(rs.getString("DIFICULDADE")));
                mapa.setMusica(
                        new MusicaDao()
                                .listarPorId(rs.getLong("FK_MUSICA")));

                return mapa;
            } else {
                throw new RuntimeException("Erro ao encontrar Mapa");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Mapa buscarMusicaPorId(Musica musica){
        String sql = "SELECT * FROM Mapa WHERE fk_musica = ?";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, musica.getId());

            ResultSet res = ps.executeQuery();

            Mapa mapa = new Mapa();
            mapa.setId(res.getLong("id"));
            mapa.setMusica(new Musica("Loretta", "Ginger Root", 1L));
            mapa.setDificuldade(Dificuldade.valueOf(res.getString("dificuldade")));
            mapa.setMusica(new MusicaDao().listarPorId(res.getLong("fk_musica")));

            return mapa;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar Música.\n" +
                    e.getMessage());
            return null;
        }
    }

    @Override
    public void cadastrar(Mapa objeto) {
        String sql = "INSERT INTO Mapa (dificuldade, fk_musica) VALUES(?,?);";

        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setString(1, String.valueOf(objeto.getDificuldade()));
            ps.setLong(2, objeto.getMusica().getId());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Mapa cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o mapa. \n" + e.getMessage());
        }
    }

    @Override
    public void editar(Mapa objeto) {
        String sql = "UPDATE Mapa SET " +
                "dificuldade = ?, fk_musica = ?" +
                "WHERE id = ?";
        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setString(1, String.valueOf(objeto.getDificuldade()));
            ps.setLong(2, objeto.getMusica().getId());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Mapa atualizado com sucesso");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar mapa. \n" + e.getMessage());
        }
    }

    @Override
    public void excluir(Mapa objeto) {
        String sql = "DELETE FROM Mapa WHERE id = ? ";
        try {

            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setLong(1, objeto.getId());

            int rowCount = ps.executeUpdate();

            if (rowCount > 0) {
                JOptionPane.showMessageDialog(null, "Mapa excluido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Mapa não encontrado com o id fornecido.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar Mapa.\n" + e.getMessage());
        }
    }
}
