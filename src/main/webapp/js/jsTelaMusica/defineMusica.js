import MapaRequest from "../ritmo/model/MapaRequest.js";
import { atualizaPlacar, zeraPlacar } from "../ritmo/service/placar.js";
import { requestJson } from "../ritmo/service/requestPost.js";
import { getMusica } from "./getMusica.js";

window.onload = () => {
  const userLogado = sessionStorage.getItem('loginUserLogado');
  if(!userLogado) {
      window.location.href = "index.html";
  
  } else {
    defineMusica();
  }
}

// Selects
const selectMusica = document.getElementById("selectMusica");
const selectDeficuldade = document.getElementById("selectDificuldade");


async function defineMusica() {

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

  const dificuldade = selectDeficuldade.options[selectDeficuldade.selectedIndex].value;
  const idMusica = selectMusica.options[selectMusica.selectedIndex].value;

  if (idMusica == 0) {
    alert("selecione uma musica!");
    return;
  }

  const idMapa = await getIdMapa(dificuldade, idMusica);
  sessionStorage.setItem("idMapa", idMapa);
  console.log(sessionStorage.getItem("idMapa"));

  window.location.href = "jogo.html";

});

async function getIdMapa(dificuldade, idMusica){

  const mapaRequest = new MapaRequest(dificuldade, idMusica);
  const json = JSON.stringify(mapaRequest);

  console.log(json);

  const requestOptions = requestJson(json);

  return await fetch("/get-mapa-musica-dificuldade", requestOptions)
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

      return mapa.id;

    })
    .catch((error) => {
      alert("Erro.");
      throw new Error(error);

    });
}

const btnSair = document.getElementById("botao-sair");
btnSair.addEventListener('click', () => {
  sessionStorage.removeItem("idUserLogado");
  sessionStorage.removeItem("nomeUserLogado", null);
  sessionStorage.removeItem("loginUserLogado", null);

  window.location.href = "index.html";
})



selectMusica.addEventListener('change', async () => {
  atualizaSelect();
});
selectDeficuldade.addEventListener('change', async () => {
  atualizaSelect();
});

async function atualizaSelect(){
  const idMusica = selectMusica.options[selectMusica.selectedIndex].value;
  const dificuldade = selectDeficuldade.options[selectDeficuldade.selectedIndex].value;

  if (idMusica == 0 || idMusica == null) {
    zeraPlacar();
    return;
  }

  const idMapa = await getIdMapa(dificuldade, idMusica);

  atualizaPlacar(idMapa);
}