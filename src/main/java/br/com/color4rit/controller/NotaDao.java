package br.com.color4rit.controller;

import br.com.color4rit.enums.Cor;
import br.com.color4rit.model.Mapa;
import br.com.color4rit.model.Nota;
import br.com.color4rit.repository.CrudDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaDao extends ConectarDao implements CrudDao<Nota> {


    @Override
    public void criarTabela() {

    }

    @Override
    public List<Nota> listarTodos() {
        return null;
    }

    @Override
    public Nota listarPorId(long id) {
        return null;
    }

    @Override
    public void cadastrar(Nota objeto) {

    }

    @Override
    public void editar(Nota objeto) {

    }

    @Override
    public void excluir(Nota objeto) {

    }

    public List<Nota> listarPorMapa(Mapa mapa){
        String sql = "SELECT * FROM nota" +
                " WHERE id_mapa = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, mapa.getId());

            ResultSet rs = ps.executeQuery();

            List<Nota> notas = new ArrayList<>();

            while (rs.next()) {

                Nota nota = new Nota();
                nota.setId(rs.getLong("id"));
                nota.setTempo(rs.getDouble("tempo"));
                nota.setCor(Cor.valueOf(rs.getString("cor")));
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
