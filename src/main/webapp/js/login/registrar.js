import Jogador from "../ritmo/model/Jogador.js";
import { requestJson } from "../ritmo/service/requestPost.js";

const element = document.getElementById("botaoRegistrar");
element.addEventListener("click", registrarJogador);

export function registrarJogador() {
    const inputNome = document.getElementById("nome-usuario");
    const inputLogin = document.getElementById("login-usuario");
    const inputSenha = document.getElementById("senha-usuario");
    
    const jogadorJson = new Jogador(0, inputNome.value, inputLogin.value, inputSenha.value);
    const json = JSON.stringify(jogadorJson);
    const requestOptions = requestJson(json);

    return fetch("/add-registrar", requestOptions)
    .then((response) => {
        if(!response.ok){
            alert("erroooo");
            throw new Error("Erro no servidor!");
        }
        alert("Usuário criado com sucesso!");
        window.location.href = "telaLogin.html";
    })

    .catch((erro) =>{
        throw new Error(erro);
    })
}