package br.com.color4rit.servlet;

import br.com.color4rit.controller.MusicaDao;
import br.com.color4rit.model.Musica;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/get-musica")
public class GetMusica extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idMusica = Long.parseLong(req.getParameter("id-musica"));

        Musica musica = new MusicaDao().listarPorId(idMusica);

        if (musica != null) {
            Gson gson = new Gson();
            String json = gson.toJson(musica);

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