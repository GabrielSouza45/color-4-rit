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
import java.util.List;

@WebServlet("/get-musica")
public class GetMusica extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List <Musica> musica = new MusicaDao().listarTodos();

        for(Musica obj: musica){

            System.out.println(obj.getId());
            System.out.println(obj.getDuracao());
            System.out.println(obj.getAutor());
            System.out.println(obj.getNome());
        }

        if (musica.isEmpty()) {
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