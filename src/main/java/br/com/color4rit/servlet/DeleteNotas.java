package br.com.color4rit.servlet;

import br.com.color4rit.controller.NotaDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/delete-notas")
public class DeleteNotas extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = req.getReader();
        String json = reader.readLine();

        if (json == null) {
            resp.setStatus(400);
            return;
        }

        Long idMapa = new Gson().fromJson(json, Long.class);
        System.out.println(idMapa);

        int retorno = new NotaDao().excluirPorMapa(idMapa);

        if (retorno == 1) {
            resp.setStatus(200);
        } else {
            resp.setStatus(500);
        }

    }
}
