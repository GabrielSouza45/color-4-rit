package br.com.color4rit.servlet;

import br.com.color4rit.controller.JogadorDao;
import br.com.color4rit.controller.NotaDao;
import br.com.color4rit.model.Jogador;
import br.com.color4rit.model.Nota;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/add-registrar")
public class CreateRegistro extends HttpServlet {

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = reader.readLine();

        if (json != null) {

            Jogador jogador = new Gson().fromJson(json, Jogador.class);
            System.out.println(" ");
            System.out.println("Vendo json");
            System.out.println(jogador.getNome());
            System.out.println(jogador.getLogin());
            System.out.println(jogador.getSenha());

            try {
                new JogadorDao().cadastrar(jogador);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(500, "Erro ao adicionar no banco de dados.");
                return;
            }
            response.setStatus(200);
        }



    }
}
