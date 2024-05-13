import { getMusica } from "./getMusica.js";

const element = document.getElementById("botaoContinuar");
element.addEventListener("click", defineMusica);


async function defineMusica (){
    const selectMusica = document.getElementById("selectMusica");    
    let listaDeMusicas = [];

    await getMusica()
        .then((musicas)=>{
            listaDeMusicas = musicas;
        })

        .catch((error) =>{
            throw new Error(error);
        });

        selectMusica .innerHTML = "";
        listaDeMusicas.forEach(musica=>{
            const option = document.createElement("option");
            option.value = musica.id;
            option.textContent = musica.nome;
            selectMusica.appendChild(option);
        })
}