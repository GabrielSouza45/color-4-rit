import {getPlacar} from "../api/getPlacar.js";

export function atualizaPlacar(idMapa, idJogador){
console.log("Entrou no atualiza placar");

    for (let index = 1; index < 8; index++) {
        const jogador = document.getElementById("jogador" + index);
        const pontuacao = document.getElementById("pontuacao" + index);
        let objJogador = null;
        let placar = null;

        getPlacar(idMapa,idJogador)
            .then((plac) => {
                placar = plac;
            })
            .catch((erro) => {
                console.error(erro);
                throw new Error(erro);
            });

        objJogador = placar.jogador;

        pontuacao.innerHTML(placar.pontuacao);
        jogador.innerHTML(objJogador.nome);
    }
}