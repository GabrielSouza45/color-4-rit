package br.com.color4rit.servlet;

import br.com.color4rit.controller.MapaDao;
import br.com.color4rit.controller.NotaDao;
import br.com.color4rit.model.Mapa;
import br.com.color4rit.model.Nota;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/get-notas")
public class GetNotas extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getParameter("id-mapa"));
        long idMapa = Long.parseLong(req.getParameter("id-mapa"));

        Mapa mapa = new MapaDao().listarPorId(idMapa);

        List<Nota> notas = new NotaDao().listarPorMapa(mapa);

        Gson gson = new Gson();
        String json = gson.toJson(notas);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Escrever o JSON na resposta
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}
