
import { getNotas } from "./api/getNotas.js";
import { getTeclasPressionadas } from "./service/teclasPressionadas.js";

let listNotas = [];
let teclasPressionadas = [];

const element = document.getElementById("botao-registrar");
element.addEventListener("click", async function () {
  console.log("Entrou aqui em");

  await getNotas()
    .then((notas) => {
      console.log("Notas:", notas);
      listNotas = notas;
      // arrow();
    })
    .catch((error) => {
      console.error("Erro ao obter notas:", error);
      alert("Erro ao obter notas!");
    });

  console.log("Lista Nova:", listNotas);

  getTeclasPressionadas();
});

