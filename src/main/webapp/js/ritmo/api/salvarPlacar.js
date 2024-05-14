import { requestJson } from "../service/requestPost.js";

export async function salvarPlacar(placar) {
    const json = JSON.stringify(placar);
    const requestOptions = requestJson(json);

    try {
        const response = await fetch("/atualizar-placar", requestOptions);
        if (!response.ok) {
            throw new Error("Erro ao salvar o placar.");
        }

        console.log("Placar salvo com sucesso!");
    } catch (error) {
        console.error("Erro ao salvar o placar:", error);
        throw error;
    }
}
