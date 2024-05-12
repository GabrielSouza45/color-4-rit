package br.com.color4rit.servlet;

import br.com.color4rit.controller.JogadorDao;
import br.com.color4rit.model.Jogador;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/get-jogador")
public class GetJogador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String loginJogador = req.getParameter("login-jogador");
        System.out.println(loginJogador);

        Jogador jogador = new JogadorDao().listarPorLogin(loginJogador);

        if (jogador != null) {

            Gson gson = new Gson();
            String json = gson.toJson(jogador);
            System.out.println(json);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            PrintWriter out = resp.getWriter();
            out.print(json);
            resp.setStatus(200);
            out.flush();

        } else
            resp.setStatus(404);


    }
}

