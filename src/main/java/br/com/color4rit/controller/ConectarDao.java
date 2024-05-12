package br.com.color4rit.controller;

import java.sql.*;

public class ConectarDao {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private final static String url =
            "jdbc:mysql://color4rit.cpgiqwu4wvyq.us-east-1.rds.amazonaws.com:3306/color4rit?useTimeZone=true&serverTimeZone=UTC";
    private final static String usuario = "admin";
    private final static String senha = "color#123";
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
            e.printStackTrace();
        }
    }

}