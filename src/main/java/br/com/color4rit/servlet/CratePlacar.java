package br.com.color4rit.servlet;

import br.com.color4rit.controller.JogadorDao;
import br.com.color4rit.controller.MapaDao;
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
import java.time.LocalDateTime;

@WebServlet("/atualizar-placar")
public class CratePlacar extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Entrou Salvar Placar");
        BufferedReader reader = req.getReader();
        String json = reader.readLine();
        Gson gson = new Gson();

        PlacarRequest placarReq = gson.fromJson(json, PlacarRequest.class);
        int pontuacao = new PlacarDao()
                .listarMaiorPontuacaoDoJogadorPorMapa(placarReq.getIdJogador(), placarReq.getIdMapa());

        if (pontuacao != -2) { // -2 = Erro no banco

            if (pontuacao != -1) { // -1 = Não contem dados de placar para o usuario no mapa

                if (placarReq.getPontuacao() >= pontuacao) {
                    try {

                        Placar placar = new Placar();
                        placar.setMapa(new MapaDao().listarPorId(placarReq.getIdMapa()));
                        placar.setJogador(new JogadorDao().listarPorId(placarReq.getIdJogador()));
                        placar.setPontuacao(placarReq.getPontuacao());
                        placar.setDataIniLocalDateTime(LocalDateTime.now());

                        new PlacarDao().cadastrar(placar);

                        PrintWriter out = resp.getWriter();
                        resp.setStatus(201);
                        out.print(gson.toJson(placar));
                        out.flush();
                        return;

                    } catch (Exception e) {
                        resp.setStatus(500);
                        e.printStackTrace();
                        return;
                    }

                } else {
                    resp.setStatus(200);
                    return;
                }
            }
        } else {
            resp.setStatus(500);
        }
    }
}