package br.com.color4rit.controller;

import br.com.color4rit.model.Musica;
import br.com.color4rit.repository.CrudDao;
import com.sun.source.tree.TryTree;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MusicaDao extends ConectarDao implements CrudDao<Musica> {


    @Override
    public void criarTabela() {

    }

    @Override
    public List<Musica> listarTodos() {
        return null;
    }

    @Override
    public Musica listarPorId(long id) {

        String sql = "SELECT * FROM MUSICA WHERE ID = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

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

    }

    @Override
    public void editar(Musica objeto) {

    }

    @Override
    public void excluir(Musica objeto) {

    }
}
