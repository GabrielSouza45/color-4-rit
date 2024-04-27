import { requestJson } from "../service/requestPost.js";

export function addNotas(notas) {

    const json = JSON.stringify(notas);

    const requestOptions = requestJson(json);
    console.log(json);

    const msgErro = "Erro ao adicionar notas no Backend.";

    fetch("/adiciona-notas", requestOptions)
        .then((response) => {
          
            if(!response.ok) {
                alert(msgErro);
                throw new Error (msgErro);
            } else {
                alert("Notas adicionadas com sucesso!");
            }

        }) 
        .catch((error) => {
       
            console.error(error);
            alert(msgErro);
            return null;

        })

}