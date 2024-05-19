package br.com.color4rit.servlet;

import br.com.color4rit.model.Nota;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CriaScript {

    public void gerarScriptSQL(Nota[] notas, String filePath) {
        StringBuilder sb = new StringBuilder();

        for (Nota nota : notas) {
            String sql = gerarSQL(nota);
            sb.append(sql).append("\n");
        }

        salvarArquivo(sb.toString(), filePath);
    }

    private String gerarSQL(Nota nota) {
        Long id = nota.getMapa().getId();
        String cor = nota.getCor().name();
        int tempo = nota.getTempo();
        int status = nota.getStatus();

        return String.format("INSERT INTO NOTA(FK_MAPA, COR, TEMPO, STATUS) VALUES (%d, '%s', %d, %d);",
                id, cor, tempo, status);
    }

    private void salvarArquivo(String conteudo, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(conteudo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
