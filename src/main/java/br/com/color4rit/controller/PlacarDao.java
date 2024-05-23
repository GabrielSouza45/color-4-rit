package br.com.color4rit.controller;

import br.com.color4rit.model.*;
import br.com.color4rit.repository.CrudDao;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlacarDao extends ConectarDao implements CrudDao<Placar> {


    @Override
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS `PLACAR`(" +
                "`ID` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "  `PONTUACAO` INT,\n" +
                "  `FK_JOGADOR` BIGINT UNSIGNED,\n" +
                "  `FK_MAPA` BIGINT UNSIGNED," +
                "  `DATA_INI` TIMESTAMP, " +
                "  FOREIGN KEY (`FK_JOGADOR`) REFERENCES `JOGADOR`(`ID`),\n" +
                "  FOREIGN KEY (`FK_MAPA`) REFERENCES `MAPA`(`ID`)\n" +
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
    public List<Placar> listarTodos() {
        String sql = "SELECT * FROM PLACAR ORDER BY DATA_INI DESC";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ResultSet res = ps.executeQuery();

            List<Placar> placars = new ArrayList<>();

            while (res.next()) {
                Placar placar = new Placar();

                placar.setId(res.getLong("ID"));
                placar.setPontuacao(res.getInt("PONTUACAO"));
                placar.setMapa(new MapaDao().listarPorId(res.getLong("FK_MAPA")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("FK_JOGADOR")));

                Timestamp timestamp = res.getTimestamp("DATA_INI");
                placar.setDataIniLocalDateTime(timestamp.toLocalDateTime());

                placars.add(placar);
            }

            return placars;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Placar listarPorId(long id) {
        String sql = "SELECT * FROM PLACAR WHERE ID = ?";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, id);

            ResultSet res = ps.executeQuery();
            Placar placar = new Placar();

            if (res.next()) {
                placar.setId(res.getLong("ID"));
                placar.setPontuacao(res.getInt("PONTUACAO"));
                placar.setMapa(new MapaDao().listarPorId(res.getLong("FK_MAPA")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("FK_JOGADOR")));
                Timestamp timestamp = res.getTimestamp("DATA_INI");
                placar.setDataIniLocalDateTime(timestamp.toLocalDateTime());

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
        String sql = "INSERT INTO PLACAR (PONTUACAO, FK_MAPA, FK_JOGADOR, DATA_INI) VALUES(?,?,?,?);";

        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setInt(1, objeto.getPontuacao());
            ps.setLong(2, objeto.getMapa().getId());
            ps.setLong(3, objeto.getJogador().getId());
            ps.setTimestamp(4, Timestamp.valueOf(objeto.getDataIniLocalDateTime()));

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editar(Placar objeto) {
        String sql = "UPDATE PLACAR SET " +
                "PONTUACAO = ?, FK_MAPA = ?, FK_JOGADOR = ?" +
                "WHERE ID = ?";
        try {
            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setInt(1, objeto.getPontuacao());
            ps.setLong(2, objeto.getMapa().getId());
            ps.setLong(3, objeto.getJogador().getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Placar objeto) {
        String sql = "DELETE FROM PLACAR WHERE ID = ? ";
        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

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

    public int listarMaiorPontuacaoDoJogadorPorMapa(Long idJogador, Long idMapa){
        String sql = " SELECT MAX(PONTUACAO) AS PONTUACAO FROM PLACAR " +
                " WHERE FK_JOGADOR = ? " +
                " AND FK_MAPA = ? ";

        try {
            PreparedStatement ps = (PreparedStatement)
                getConexao().prepareStatement(sql);

            ps.setLong(1, idJogador);
            ps.setLong(2, idMapa);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("PONTUACAO");
            }
            return -1;

        } catch (SQLException e) {
            e.printStackTrace();
            return -2;

        }
    }

    public List<Placar> listarPorJogador(Long idJogador, Long idMapa) {
        String sql = "SELECT * FROM PLACAR" +
                " WHERE FK_JOGADOR = ? " +
                " AND FK_MAPA = ?";

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, idJogador);
            ps.setLong(2, idMapa);

            ResultSet res = ps.executeQuery();

            List<Placar> placares = new ArrayList<>();

            if (res.next()) {
                Placar placar = new Placar();

                placar.setId(res.getLong("ID"));
                placar.setPontuacao(res.getInt("PONTUACAO"));
                placar.setMapa(new MapaDao().listarPorId(res.getLong("FK_MAPA")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("FK_JOGADOR")));
                Timestamp timestamp = res.getTimestamp("DATA_INI");
                placar.setDataIniLocalDateTime(timestamp.toLocalDateTime());

                placares.add(placar);
            }
            return placares;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Placar> listarPorMapa(Long idMapa) {
        String sql = " SELECT * " +
                " FROM ( " +
                "     SELECT p.*, " +       // Select * from PLACAR
                "            ROW_NUMBER() OVER (PARTITION BY p.fk_jogador ORDER BY p.pontuacao DESC, p.data_ini DESC) AS ranking " + // resumindo -> ordena pela pontuacao e pela data Decrescente e da um numero para aquele registro, os que tiverem maior ponto e menor data recebem uma numeracao menor
                "     FROM PLACAR p " +
                "     WHERE p.fk_mapa = ? AND EXISTS( " +    // * Garante que a pontuacao pega é a maior do jogador
                "         SELECT 1 " +
                "         FROM PLACAR p2 " +
                "         WHERE p2.fk_jogador = p.fk_jogador " +
                "         GROUP BY p2.fk_jogador " +
                "         HAVING MAX(p2.pontuacao) = p.pontuacao " +
                "     ) " +                 // * fim
                " ) AS placares " +
                " WHERE ranking = 1 " +     // pega somente os registro que foram dados a numeracao 1 (maiores pontos com data menor)
                " ORDER BY pontuacao DESC, data_ini DESC " +
                " LIMIT 7; ";               // Pega apenas os 7 melhores scores de todos

        try {

            PreparedStatement ps = (PreparedStatement)
                    getConexao().prepareStatement(sql);

            ps.setLong(1, idMapa);

            ResultSet res = ps.executeQuery();
            ArrayList<Placar> placares = new ArrayList<>();

            while (res.next()) {
                Placar placar = new Placar();
                placar.setId(res.getLong("ID"));
                placar.setPontuacao(res.getInt("PONTUACAO"));
                placar.setMapa(new MapaDao().listarPorId(res.getLong("FK_MAPA")));
                placar.setJogador(new JogadorDao().listarPorId(res.getLong("FK_JOGADOR")));
                Timestamp timestamp = res.getTimestamp("DATA_INI");
                placar.setDataIniLocalDateTime(timestamp.toLocalDateTime());
                placares.add(placar);
            }
            return placares;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}