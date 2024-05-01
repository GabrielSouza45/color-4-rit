import {requestJson} from "../service/requestPost.js";
import Placar from "../model/Placar.js";

const element = document.getElementById("acessar-placar"); 
element.addEventListener("click", getPlacar);

export function getPlacar (idMapa, idJogador){
    const placarJson = new Placar(0, 0, null, null, idMapa, idJogador);
    const json = JSON.stringify(placarJson);
    const requestOptions = requestJson (json);

    return fetch("/get-placar", requestOptions)
    .then((response) => {
        if(!response.ok && !response.status === 404){
           throw new Error("Erro ao coletar dados do placar!");
        }else if(response.status === 404){
            throw new Error("Não foram encontrados os dados do placar!");
        }
        return response.json();
    })
    
    .then((placar) => {
        
        console.log(placar);

        return new Placar(
            placar.id,
            placar.pontuacao,
            placar.jogador,
            placar.mapa,
            placar.idMapa,
            placar.idJogador
        );
    })

    .catch((erro) =>{
        alert("Erro ao pegar o placar no Backend");
        console.error(erro);
        throw new Error(erro);
    })
}