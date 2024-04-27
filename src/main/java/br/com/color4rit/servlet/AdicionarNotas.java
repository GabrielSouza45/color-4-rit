package br.com.color4rit.servlet;

import br.com.color4rit.controller.NotaDao;
import br.com.color4rit.model.Nota;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/adiciona-notas")
public class AdicionarNotas extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = reader.readLine();

        System.out.println("Entrou aqui> " + json);

        if (json != null) {

            Nota[] notas = new Gson().fromJson(json, Nota[].class);

            for (Nota n : notas) {

                try {
                    new NotaDao().cadastrar(n);

                } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendError(500, "Erro ao adicionar no banco de dados.");
                    return;
                }
            }
            resp.setStatus(200);


        } else {
            resp.sendError(400, "Objeto nulo.");
            resp.flushBuffer();
        }

    }
}
