import { fetchMapa } from "./api/getmapa.js";
import { iniciarJogo } from "./jogo.js";

async function obterMapaEMusica(idMusica, dificuldade) {
    try {
        const mapa = await fetchMapa(idMusica, dificuldade);
        iniciarJogo(mapa);
    } catch (error) {
        console.error("Erro ao obter mapa e iniciar jogo:", error);
    }
}

export { obterMapaEMusica };