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
        String sql = "CREATE TABLE IF NOT EXISTS `MAPA` (" +
                "`ID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "`DIFICULDADE` VARCHAR(50), " +
                "`FK_MUSICA` BIGINT UNSIGNED, " +
                "FOREIGN KEY (`FK_MUSICA`) REFERENCES `MUSICA`(`ID`)" +
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
    public List<Mapa> listarTodos(){
        String sql ="SELECT * FROM MAPA";

        try {
        PreparedStatement ps = (PreparedStatement)
                getConexao().prepareStatement(sql);

        ResultSet res = ps.executeQuery();

        List<Mapa> mapas = new ArrayList<>();

        while (res.next()) {
            Mapa mapinha = new Mapa();

            mapinha.setId(res.getLong("ID"));
            mapinha.setDificuldade(Dificuldade.valueOf(res.getString("DIFICULDADE")));
            mapinha.setMusica(new MusicaDao().listarPorId(res.getLong("FK_MUSICA")));

            mapas.add(mapinha);
        }

        return mapas;
    } catch(
    SQLException e) {
            e.printStackTrace();
        return null;
    }
}

    public Mapa listarPorId(long id) {

        String sql = "SELECT * FROM MAPA WHERE ID = ?";

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
                        new MusicaDao().listarPorId(rs.getLong("FK_MUSICA")));

                return mapa;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Mapa buscarMapaPorMusicaDificuldade(Long idMusica, Dificuldade dificuldade){
        String sql = "SELECT * FROM MAPA WHERE FK_MUSICA = ?" +
                " AND DIFICULDADE = ? ";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, idMusica);
            ps.setString(2, dificuldade.name());

            ResultSet res = ps.executeQuery();

            Mapa mapa = null;
            if (res.next()) {

                mapa = new Mapa();
                mapa.setId(res.getLong("ID"));
                mapa.setMusica(new MusicaDao().listarPorId(res.getLong("FK_MUSICA")));
                mapa.setDificuldade(Dificuldade.valueOf(res.getString("DIFICULDADE")));

            }

            return mapa;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cadastrar(Mapa objeto) {
        String sql = "INSERT INTO MAPA (DIFICULDADE, FK_MUSICA) VALUES(?,?);";

        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setString(1, String.valueOf(objeto.getDificuldade()));
            ps.setLong(2, objeto.getMusica().getId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Mapa objeto) {
        String sql = "UPDATE MAPA SET " +
                "DIFICULDADE = ?, FK_MUSICA = ?" +
                "WHERE ID = ?";
        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setString(1, String.valueOf(objeto.getDificuldade()));
            ps.setLong(2, objeto.getMusica().getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(Mapa objeto) {
        String sql = "DELETE FROM MAPA WHERE ID = ? ";
        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, objeto.getId());

            int rowCount = ps.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Mapa excluido com sucesso!");
            } else {
                System.out.println("Mapa não encontrado com o id fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
