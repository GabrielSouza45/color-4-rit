import { requestJson } from "../service/requestPut.js";

export function deleteNotas(idMapa) {

    const json = JSON.stringify(idMapa);
    console.log(json);


    const requestOptions = requestJson(json);
    const msgErro = "Erro ao deletar notas no Backend.";

    return fetch('/delete-notas', requestOptions) 
        .then((response) => {

            if(!response.ok) {
                alert(msgErro);
                throw new Error (msgErro);
            } else {
                alert("Notas deletadas com sucesso!");
            }

        })
        .catch((error) => {

            console.error(error);
            alert(msgErro);
            throw new Error(error);

        })
}