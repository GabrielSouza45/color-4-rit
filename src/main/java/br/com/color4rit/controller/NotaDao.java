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
        String sql = "CREATE TABLE IF NOT EXISTS `NOTA` (\n" +
                "  `ID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "  `FK_MAPA` BIGINT UNSIGNED,\n" +
                "  `COR` VARCHAR(50),\n" +
                "  `TEMPO` TIME,\n" +
                "  FOREIGN KEY (`FK_MAPA`) REFERENCES `MAPA`(`ID`)";

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
        String sql = "SELECT * FROM NOTA";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ResultSet res = ps.executeQuery();

            List<Nota> notas = new ArrayList<>();

            while (res.next()) {
                Nota notinha = new Nota();

                notinha.setId(res.getLong("ID"));
                notinha.setCor(Cor.valueOf(res.getString("COR")));
                notinha.setTempo(res.getTime("TEMPO"));
                notinha.setMapa(new MapaDao().listarPorId(res.getLong("FK_MAPA")));

                notas.add(notinha);
            }

            return notas;
        } catch (
                SQLException e) {e.printStackTrace();
            return null;
        }
    }


    public Nota listarPorId(long id) {
        String sql = "SELECT * FROM NOTA WHERE ID = ?";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, id);

            ResultSet res = ps.executeQuery();
            Nota nota = new Nota();

            if (res.next()) {
                nota.setId(res.getLong("ID"));
                nota.setCor(Cor.valueOf(res.getString("COR")));
                nota.setTempo(res.getTime("TEMPO"));
                nota.setMapa(new MapaDao().listarPorId(res.getLong("FK_MAPA")));

                return nota;
            } else {
               return  null;
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void cadastrar(Nota objeto) {
        String sql = "INSERT INTO NOTA (COR, TEMPO, FK_MAPA) VALUES(?,?,?);";

        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setString(1, String.valueOf(objeto.getCor()));
            ps.setTime(2, objeto.getTempo());
            ps.setLong(3, objeto.getMapa().getId());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Nota objeto) {
        String sql = "UPDATE NOTA SET " +
                "COR = ?, TEMPO = ?, FK_MAPA = ?" +
                "WHERE ID = ?";
        try {
            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setString(1, String.valueOf(objeto.getCor()));
            ps.setTime(2, objeto.getTempo());
            ps.setLong(3, objeto.getMapa().getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(Nota objeto) {
        String sql = "DELETE FROM NOTA WHERE ID = ? ";
        try {

            PreparedStatement ps = (PreparedStatement) getConexao().prepareStatement(sql);
            ps.setLong(1, objeto.getId());

            int rowCount = ps.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Nota excluida com sucesso!");
            } else {
                System.out.println("Nota não encontrada com o id fornecido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Nota> listarPorMapa(Mapa mapa){
        String sql = "SELECT * FROM NOTA" +
                " WHERE FK_MAPA = ?" +
                " ORDER BY TEMPO";

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
