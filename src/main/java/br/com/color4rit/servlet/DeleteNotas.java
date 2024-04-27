package br.com.color4rit.servlet;

import br.com.color4rit.controller.NotaDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-notas")
public class DeleteNotas extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long idMapa = Long.parseLong(req.getParameter("id-mapa"));
        System.out.println(idMapa);

        int retorno = new NotaDao().excluirPorMapa(idMapa);

        if (retorno == 1) {
            resp.setStatus(200);
        } else {
            resp.setStatus(500);
        }

    }
}
