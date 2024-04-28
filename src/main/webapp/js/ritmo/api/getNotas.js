import Nota from "../model/Nota.js";
import { requestHeader } from "../service/requestPost.js";

export function getNotas(idMapa) {
  if (idMapa == null) {
    idMapa = document.getElementById("id-mapa").value;
  }
  console.log("Entrou no getNotas() ", idMapa);

  const requestOptions = requestHeader(`id-mapa=${idMapa}`);

  return fetch("/get-notas", requestOptions)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Erro ao obter a nota");
      }
      return response.json();
    })
    .then((notas) => {
      console.log("Notas .then: ", notas);
      return notas.map((nota) => {
        return new Nota(nota.id, nota.cor, nota.tempo, nota.status, nota.mapa);
      });
    })
    .catch((error) => {
      console.error("Erro:", error);
      return []; // Retorna uma lista vazia em caso de erro
    });
}
