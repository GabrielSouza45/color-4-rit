package br.com.color4rit.servlet;

import br.com.color4rit.controller.JogadorDao;
import br.com.color4rit.model.Jogador;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/get-mapa"})
public class GetMapaJogador extends HttpServlet {
    public GetMapaJogador() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long idMapa = Long.parseLong(req.getParameter("id-mapa"));
        System.out.println(idMapa);

        Jogador jogador = (new JogadorDao()).listarPorLogin(loginJogador);

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

        } else {

            resp.setStatus(404);
        }

    }
}