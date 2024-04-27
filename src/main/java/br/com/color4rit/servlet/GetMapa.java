package br.com.color4rit.servlet;

import br.com.color4rit.controller.MapaDao;
import br.com.color4rit.model.Mapa;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/get-mapa")
public class GetMapa extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long idMapa = Long.parseLong(req.getParameter("id-mapa"));
        System.out.println(idMapa);

        Mapa mapa = new MapaDao().listarPorId(idMapa);

        if (mapa != null) {

            Gson gson = new Gson();
            String json = gson.toJson(mapa);
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
