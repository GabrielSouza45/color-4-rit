import Nota from "./classes/Non.js";

export function getNotas() {
  const idMapa = document.getElementById("id-mapa");
  console.log("Entrou no gatNotas()");

  const requestOptions = {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    body: `id-mapa=${idMapa.value}`,
  };

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
        return new Nota(nota.id, nota.cor, nota.tempo, nota.mapa);
      });
    })
    .catch((error) => {
      console.error("Erro:", error);
      return []; // Retorna uma lista vazia em caso de erro
    });
}
