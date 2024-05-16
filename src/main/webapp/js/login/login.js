import Jogador from "../ritmo/model/Jogador.js";
import { requestJson } from "../ritmo/service/requestPost.js";

const element = document.getElementById("botaoLogin");
element.addEventListener("click", loginJogador);

export function loginJogador() {
    const inputLogin = document.getElementById("login-usuario");
    const inputSenha = document.getElementById("senha-usuario");
    
    const jogadorJson = new Jogador(0, '', inputLogin.value, inputSenha.value);
    const json = JSON.stringify(jogadorJson);
    const requestOptions = requestJson(json);

    return fetch("/get-jogador", requestOptions)
    .then((response) => {
        if(!response.ok && !response.status === 404 && !response.status === 401){
            alert("erroooo");
            throw new Error("Erro no servidor!");
        }else if(response.status === 404){
            alert("Login não encontrado!");
            throw new Error("Login ou senha incorretos!");
        }else if(response.status === 401){
            alert("Login ou senha incorretos!");
            throw new Error("Login ou senha incorretos!");
        }
        window.location.href = "jogo.html"
    })

    .catch((erro) =>{
        throw new Error(erro);
    })
}