import Musica from "../ritmo/model/Musica.js";
import { requestJson } from "../ritmo/service/requestPost.js";
export function getMusica (){

    const json = JSON.stringify("");
    const requestOptions = requestJson("");
    
    return fetch("/get-musica", requestOptions)
    .then((response) => {
        if(!response.ok && !response.status === 404){
           throw new Error("Erro ao coletar dados da música!");
        }else if(response.status === 404){
            throw new Error("Não foram encontrados os dados da música!");
        }
        return response.json();
    })
    .then((musicas)=>{
        return musicas.map((musica) => {
            return new Musica(musica.id, musica.nome, musica.autor, musica.duracao);
          });
    }) 
.catch((erro)=>{
    console.error(erro);
    throw new Error(erro);
});
}