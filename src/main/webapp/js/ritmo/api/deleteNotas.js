import { requestHeader } from "../service/requestPut.js";

export function deleteNotas(idMapa) {

    const requestOptions = requestHeader(`id-mapa=${idMapa}`);
    const msgErro = "Erro ao deletar notas no Backend.";

    fetch('/delete-notas', requestOptions) 
        .then((response) => {

            if(!response.ok) {
                alert(msgErro);
                throw new Error (msgErro);
            } else {
                alert("Notas deletadas com sucesso!");
            }
            return response.json();

        })
        .catch((error) => {

            console.error(error);
            alert(msgErro);
            return null;

        })
}