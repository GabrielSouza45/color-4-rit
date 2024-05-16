import { requestJson } from "../service/requestPost.js";

export async function salvarPlacar(placar) {
    const json = JSON.stringify(placar);
    const requestOptions = requestJson(json);
    
    fetch("/atualizar-placar", requestOptions)
        .then((response) => {
            if (!response.ok) {
                alert("Erro ao salvar o placar.");
                return;
            }
            alert("Placar salvo com sucesso!");
            console.log("Placar salvo com sucesso!");
        })
        .catch ((error) => {
            console.error(error);
            alert("Erro ao salvar o placar.");
        });
        
}
