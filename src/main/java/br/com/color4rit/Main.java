package br.com.color4rit;

import br.com.color4rit.controller.ConectarDao;
import br.com.color4rit.controller.JogadorDao;
import br.com.color4rit.enums.Cor;
import br.com.color4rit.model.Mapa;
import br.com.color4rit.model.Musica;
import br.com.color4rit.model.Nota;

public class Main {
    public static void main(String[] args) {

        new ConectarDao().testeConexao();
    }
}
