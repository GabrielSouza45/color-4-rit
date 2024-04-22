package br.com.color4rit.controller;

import java.sql.*;

public class ConectarDao {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private final static String url =
            "jdbc:mysql://127.0.0.1:3306/color4hit?useTimeZone=true&serverTimeZone=UTC";
    private final static String usuario = "";
    private final static String senha = "";
    private static Connection conexao;


    public Connection getConexao() {

        try {
            if (conexao == null) {
                Class.forName(driver);
                conexao = DriverManager.getConnection(url, usuario, senha);
            }
            return conexao;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void testeConexao() {
        try {
            Connection con = getConexao();
            System.out.println(con);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}