package br.com.color4rit.servlet;

import br.com.color4rit.controller.PlacarDao;
import br.com.color4rit.model.Placar;
import br.com.color4rit.model.PlacarRequest;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/get-placar")
public class GetPlacar extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Entrou aqui GetPlacar");

        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        System.out.println(json);
        Gson gson = new Gson();
        PlacarRequest placarReq = gson.fromJson(json, PlacarRequest.class);

        ArrayList <Placar> placar = new PlacarDao().listarPorMapa(placarReq.getIdMapa());

        if (placar.isEmpty()){
            resp.setStatus(404);
        }else {
            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            PrintWriter out = resp.getWriter();
            String jsonRespos = gson.toJson(placar);
            out.print(jsonRespos);
            out.flush();
            System.out.println(jsonRespos);
        }
    }
}
