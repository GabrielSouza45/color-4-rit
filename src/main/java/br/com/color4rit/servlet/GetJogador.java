package br.com.color4rit.servlet;

import br.com.color4rit.controller.JogadorDao;
import br.com.color4rit.model.Jogador;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/get-jogador")
public class GetJogador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        Gson gson = new Gson();
        Jogador jogador = gson.fromJson(json, Jogador.class);

        Jogador jogadorLogado = new JogadorDao().listarPorLogin(jogador.getLogin());

        if (jogadorLogado != null) {

            if(jogadorLogado.getSenha().equals(jogador.getSenha())){
                resp.setStatus(200);

                jogadorLogado.setSenha(null);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                PrintWriter out = resp.getWriter();
                out.print(gson.toJson(jogadorLogado));
                out.flush();

            } else {
                resp.setStatus(401);
            }

        } else
            resp.setStatus(404);


    }
}

