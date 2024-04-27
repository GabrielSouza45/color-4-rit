import { getMapa } from "./api/getMapa.js";
import { getTeclasPressionadas } from "./service/teclasPressionadas.js";

const element = document.getElementById("iniciar-gravacao");
element.addEventListener('click', () => {

 setTimeout( // Adiciona um atraso de 5 segundos para iniciar a musica
    async () => {

        console.log("Entrou notas");
        const inputMapa = document.getElementById("id-mapa");
        const mapa = await getMapa(inputMapa.value);

        const musica = mapa.musica;
        const musicaNome = musica.nome;
        const musicaDuracao = musica.duracao;

        const audio = new Audio(`../audio/${musicaNome}.mp3`)
        audio.play();

        getTeclasPressionadas(musicaDuracao);

    } , 5000);
    
});


