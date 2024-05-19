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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/adiciona-notas")
public class AdicionarNotas extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = reader.readLine();

        System.out.println("Entrou aqui> " + json);

        if (json != null) {

            Nota[] notas = new Gson().fromJson(json, Nota[].class);

            CriaScript script = new CriaScript();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
            String formattedDate = LocalDateTime.now().format(formatter);

            // Caminho do arquivo
            String filePath = "SCRIPT/idMapa-"+ notas[0].getMapa().getId() +"-Script_Add_Notas_" + formattedDate + ".txt";

            script.gerarScriptSQL(notas, filePath);

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
