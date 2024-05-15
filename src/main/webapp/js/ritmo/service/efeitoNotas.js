import { getCorTecla } from "./getCorTecla.js";

document.addEventListener("keydown", (event) => {
  const teclaPressionada = getCorTecla(event.key.toLowerCase());

  const quadrado = document.getElementById(teclaPressionada.toLowerCase());
  quadrado.style.boxShadow = "inset 0 0 10px 5px white";

  setTimeout(() => {
    quadrado.style.boxShadow = "none";
  }, 300);

});
