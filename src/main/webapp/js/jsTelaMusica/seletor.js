import { iniciarJogo } from "./jogo.js";

document.addEventListener("DOMContentLoaded", function() {
    const continuarBtn = document.getElementById("continuarBtn");
    continuarBtn.addEventListener("click", function() {
        iniciarJogo();
    });
});