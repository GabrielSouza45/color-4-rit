import MapaRequest from "../ritmo/model/MapaRequest.js";
import { requestJson } from "../ritmo/service/requestPost.js";
import { iniciarGame } from "../ritmo/jogo.js";
import { getMusica } from "./getMusica.js";

window.onload = defineMusica();

async function defineMusica() {
  const selectMusica = document.getElementById("selectMusica");
  let listaDeMusicas = [];

  await getMusica()
    .then((musicas) => {
      listaDeMusicas = musicas;
    })

    .catch((error) => {
      throw new Error(error);
    });

  selectMusica.innerHTML = "";
  const option1 = document.createElement("option");
  option1.value = 0;
  option1.textContent = "Selecione uma música.";
  selectMusica.appendChild(option1);

  listaDeMusicas.forEach((musica) => {
    const option = document.createElement("option");
    option.value = musica.id;
    option.textContent = musica.nome;
    selectMusica.appendChild(option);
  });
}


const continuarBtn = document.getElementById("botaoContinuar");
continuarBtn.addEventListener("click", async () => {
  const selectMusica = document.getElementById("selectMusica");
  const selectDeficuldade = document.getElementById("selectDificuldade");


  const dificuldade = selectDeficuldade.options[selectDeficuldade.selectedIndex].value;
  const idMusica = selectMusica.options[selectMusica.selectedIndex].value;

  if (idMusica == 0) {
    alert("selecione uma musica!");
    return;
  }

  const mapaRequest = new MapaRequest(dificuldade, idMusica);
  const json = JSON.stringify(mapaRequest);

  console.log(json);

  const requestOptions = requestJson(json);

  let idMapa;

  await fetch("/get-mapa-musica-dificuldade", requestOptions)
    .then((response) => {
      if (response.status == 404) {
        alert("Mapa não localizado!");
        throw new Error("404 - Mapa não localizado!");

      } else if (!response.ok) {
        alert("Erro ao localizar Mapa!");
        throw new Error("Erro ao localizar Mapa!");

      }
      return response.json();

    })
    .then((mapa) => {

      console.log("mapa ", mapa);

      console.log(mapa);
      idMapa = mapa.id;

    })
    .catch((error) => {
      alert("Erro.");
      throw new Error(error);

    });

 
  window.location.href = "jogo.html?idMapa="+idMapa;
  

});


