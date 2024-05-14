package br.com.color4rit.servlet;

import br.com.color4rit.controller.PlacarDao;
import br.com.color4rit.model.Placar;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/atualizar-placar")
public class GetNovo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        Gson gson = new Gson();

        Long idMapa = Long.parseLong(req.getParameter("id-mapa"));
        Long idJogador = Long.parseLong(req.getParameter("id-jogador"));

        Placar placar = gson.fromJson(json, Placar.class);
        Placar placares = new PlacarDao().listarPorJogador(idJogador, idMapa);

        if (placares != null) {
            try {
                new PlacarDao().editar(placar);
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                PrintWriter out = resp.getWriter();
                out.print(gson.toJson(placar));
                out.flush();
            } catch (Exception e) {
                resp.setStatus(500);
                e.printStackTrace();
            }
        }else{
            try {
                new PlacarDao().cadastrar(placar);
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

                PrintWriter out = resp.getWriter();
                out.print(gson.toJson(placar));
                out.flush();
            } catch (Exception e) {
                resp.setStatus(500);
                e.printStackTrace();
            }
    }
}
}