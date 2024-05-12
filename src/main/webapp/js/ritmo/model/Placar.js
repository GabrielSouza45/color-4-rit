import Mapa from "./Mapa.js";
import Jogador from "./Jogador.js";

class Placar {
    constructor(id, pontuacao, jogador, mapa, idMapa, idJogador){
        this.id = id, 
        this.pontuacao = pontuacao,
        this.jogador = new Jogador(jogador.id, jogador.nome, jogador.login, jogador.senha),
        this.mapa = new Mapa(mapa.id, mapa.dificuldade, mapa.musica);
    }
};

export default Placar;