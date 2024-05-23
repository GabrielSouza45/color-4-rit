package br.com.color4rit.servlet;

import br.com.color4rit.controller.JogadorDao;
import br.com.color4rit.controller.MapaDao;
import br.com.color4rit.model.Jogador;
import br.com.color4rit.model.Mapa;
import br.com.color4rit.model.MapaRequest;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/get-mapa-musica-dificuldade")
public class GetMapaMusica extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        System.out.println("Entrou aqui GetMapaMusica");
        System.out.println(json);
        MapaRequest mapaRequest = gson.fromJson(json, MapaRequest.class);

        Mapa mapa = new MapaDao().buscarMapaPorMusicaDificuldade(
                        mapaRequest.getIdMusica(),
                        mapaRequest.getDificuldade()
                );

        if (mapa == null) {
            resp.setStatus(404);
            return;
        }


        String jsonRetorno = gson.toJson(mapa);
        resp.setStatus(200);

        System.out.println(jsonRetorno);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(jsonRetorno);
        out.flush();

    }
}