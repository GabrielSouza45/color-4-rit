package br.com.color4rit.controller;

import br.com.color4rit.enums.Dificuldade;
import br.com.color4rit.model.Mapa;
import br.com.color4rit.model.Musica;
import br.com.color4rit.repository.CrudDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MapaDao extends ConectarDao implements CrudDao<Mapa> {


    @Override
    public void criarTabela() {

    }

    @Override
    public List<Mapa> listarTodos() {
        return null;
    }

    @Override
    public Mapa listarPorId(long idMapa) {

        String sql = "SELECT * FROM MAPA WHERE id = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, idMapa);

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


    @Override
    public void cadastrar(Mapa objeto) {

    }

    @Override
    public void editar(Mapa objeto) {

    }

    @Override
    public void excluir(Mapa objeto) {

    }


}
