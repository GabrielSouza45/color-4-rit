import {requestJson} from "../service/requestPost.js";
import Placar from "../model/Placar.js";
import PlacarRequest from "../model/placarRequest.js";

// const element = document.getElementById("acessar-placar"); 
// element.addEventListener("click", getPlacar);

export function getPlacar (idMapa, idJogador){
    idMapa = 1;
    idJogador = 1;
    const placarJson = new PlacarRequest(idMapa, idJogador);
    const json = JSON.stringify(placarJson);
    const requestOptions = requestJson (json);

    return fetch("/get-placar", requestOptions)
    .then((response) => {
        if(!response.ok && !response.status === 404){
            alert("erroooo");
            throw new Error("Erro ao coletar dados do placar!");
        }else if(response.status === 404){
            alert("Não foram encontrados os dados do placar!");
            throw new Error("Não foram encontrados os dados do placar!");
        }
        return response.json();
    })
    
    .then((placar) => {
        
        console.log("Placar: ", placar);

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
        throw new Error(erro);
    })
}