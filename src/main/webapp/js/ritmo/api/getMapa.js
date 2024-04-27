import Mapa from "../model/Mapa.js";

export function getMapa(idMapa) {
    const requestOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: `id-mapa=${idMapa}`,
    };


    return fetch('/get-mapa', requestOptions)
        .then((response) => {
            if (!response.ok) {
                throw new Error("Erro ao obter a nota");
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