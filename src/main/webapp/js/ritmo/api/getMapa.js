import Mapa from "../model/Mapa.js";
import { requestHeader } from "../service/requestPost.js";

export function getMapa(idMapa) {

    const requestOptions = requestHeader(`id-mapa=${idMapa}`);

    return fetch('/get-mapa', requestOptions)
        .then((response) => {
            if (!response.ok) {
                throw new Error("Erro ao obter o mapa");
            }
            return response.json();
        })
        .then(mapa => {
            console.log("Mapa-> ", mapa);
            return new Mapa(
                mapa.id, 
                mapa.dificuldade, 
                mapa.musica
            );
        })
        .catch((error) => {
            console.error(error);
            alert("Erro ao buscar música! :C");
            return null;
        })
}