package br.com.color4rit;

import br.com.color4rit.controller.ConectarDao;
import br.com.color4rit.controller.JogadorDao;
import br.com.color4rit.enums.Cor;
import br.com.color4rit.model.Mapa;
import br.com.color4rit.model.Musica;
import br.com.color4rit.model.Nota;

public class Main {
    public static void main(String[] args) {


        Musica musica1 = new Musica();
        musica1.setId(1L);
        musica1.setNome("Loretta");
        musica1.setAutor("Ginger Root");

        Mapa mapa1 = new Mapa();
        mapa1.setMusica(musica1);
        mapa1.setId(1L);

        Nota nota1 = new Nota();
        nota1.setId(1L);
        nota1.setCor(Cor.VERMELHO);
//        nota1.setTempo();
        nota1.setMapa(mapa1);

        Nota nota2 = new Nota();
        nota1.setId(2L);
        nota1.setCor(Cor.AMARELO);
//        nota1.setTempo(1.8);
        nota1.setMapa(mapa1);

        Nota nota3 = new Nota();
        nota1.setId(3L);
        nota1.setCor(Cor.AZUL);
//        nota1.setTempo(2.0);
        nota1.setMapa(mapa1);

        ConectarDao dao = new ConectarDao();
        System.out.println(dao.getConexao());
    }
}
