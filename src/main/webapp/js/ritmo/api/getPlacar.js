import {requestJson} from "../service/requestPost.js";
import Placar from "../model/Placar.js";
import PlacarRequest from "../model/placarRequest.js";


export function getPlacar (idMapa){
    const placarJson = new PlacarRequest(idMapa);

    const json = JSON.stringify(placarJson);
    const requestOptions = requestJson (json);

    return fetch("/get-placar", requestOptions)
    .then((response) => {
        if(!response.ok && !response.status === 404){
            alert("Erro ao coletar dados do placar!");
            throw new Error("Erro ao coletar dados do placar!");
        }else if(response.status === 404){
            throw new Error("Não foram encontrados os dados do placar!");
        }
        return response.json();
    })
    
    .then((placares) => {
        
        console.log("Placar: ", placares);

        return placares.map(placar =>{
            return new Placar(
                placar.id,
                placar.pontuacao,
                placar.jogador,
                placar.mapa,
                placar.idMapa,
                placar.idJogador
            );
        });

    })

    .catch((erro) =>{
        throw new Error(erro);
    })
}