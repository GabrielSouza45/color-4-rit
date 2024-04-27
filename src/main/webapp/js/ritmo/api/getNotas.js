import Nota from "../model/Nota.js";

export function getNotas(idMapa) {
  if (idMapa == null) {
    idMapa = document.getElementById("id-mapa").value;
  }
  console.log("Entrou no getNotas() ", idMapa);

  const requestOptions = {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    body: `id-mapa=${idMapa}`,
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
        return new Nota(nota.id, nota.cor, nota.tempo, nota.mapa, nota.status);
      });
    })
    .catch((error) => {
      console.error("Erro:", error);
      return []; // Retorna uma lista vazia em caso de erro
    });
}
