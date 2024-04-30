import {requestJson} from "../service/requestPost.js";
import Placar from "../model/Placar.js";

export function getPlacar (idMapa, idJogador){
    const placarJson = new Placar(0, 0, null, null, idMapa, idJogador);
    const json = JSON.stringify(placarJson);
    const requestOptions = requestJson (json);

    fetch("/get-placar", requestOptions)
    .then((response) => {
        if(!response.ok)
    })
}