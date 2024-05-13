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
