package br.com.color4rit.controller;

import java.sql.*;

public class ConectarDao {
    private String driver = "com.mysql.cj.jdbc.Driver";


    private final static String urlLocal =
            "jdbc:mysql://localhost:3306/color4rit";
    private final static String usuarioLocal = "root";
    private final static String senhaLocal = "59762358Gfs#"; // Defina a senha do seu banco local


    private final static String urlAws =
            "jdbc:mysql://color4rit.cpgiqwu4wvyq.us-east-1.rds.amazonaws.com:3306/color4rit?useTimeZone=true&serverTimeZone=UTC";
    private final static String usuarioAws = "admin";
    private final static String senhaAws = "color#123";


    private static Connection conexao;


    public Connection getConexao() {

        try {
            if (conexao == null) {
                Class.forName(driver);

                try {
                    conexao = DriverManager.getConnection(urlAws, usuarioAws, senhaAws);
                    System.out.println("Conectado Banco AWS");
                } catch (Exception e) {
                    System.out.println("Erro ao conectar na AWS -> " + e.getMessage());
                }

                if (conexao == null) {
                    try {
                        conexao = DriverManager.getConnection(urlLocal, usuarioLocal, senhaLocal);
                        System.out.println(" \nConectado Banco Local");

                    } catch (Exception e) {
                        System.out.println(" \nErro ao conectar no BD Local -> " + e.getMessage());
                    }
                }
            }
            return conexao;
        } catch (ClassNotFoundException e) {
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