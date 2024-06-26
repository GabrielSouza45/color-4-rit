import { getPlacar } from "../api/getPlacar.js";

async function atualizaPlacar(idMapa) {
    zeraPlacar();
    console.log("Entrou no atualiza placar");

    let placares = [];

    await getPlacar(idMapa)
        .then((plac) => {
            placares = plac;
        })
        .catch((erro) => {
            console.error(erro);
            throw new Error(erro);
        });

    for (let index = 1; index < 8; index++) {
        if(index-1 == placares.length){
            break;
        }
        const jogador = document.getElementById("jogador" + index);
        const pontuacao = document.getElementById("pontuacao" + index);
        const placar = placares[index - 1];
        let objJogador = placar.jogador;
        
        console.log(placar);

        pontuacao.textContent = placar.pontuacao;
        jogador.textContent = objJogador.nome;
    }
}

function zeraPlacar() {
    for (let index = 1; index < 8; index++) {

        const jogador = document.getElementById("jogador" + index);
        const pontuacao = document.getElementById("pontuacao" + index);

        jogador.textContent = "Jogador";
        pontuacao.textContent = "000";
    }
}

export {atualizaPlacar, zeraPlacar};