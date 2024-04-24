import Nota from "./Non.js";

const element = document.getElementById("botao-registrar");
element.addEventListener("click", getNotas);

function getNotas() {

    const idMapa = document.getElementById('id-mapa');

    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `id-mapa=${idMapa.value}`
    };

    fetch('/get-notas', requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao obter as notas');
            }
            return response.json();
        })
        .then(notas => {
            // Manipular os dados das notas aqui
            console.log('Notas recebidas:', notas);
            // Criar objeto JS modular para cada nota
            const notasModulares = notas.map(nota => {
                return new Nota(nota.id, nota.cor, nota.tempo, nota.mapa)
            });
            console.log('Notas modulares:', notasModulares);
            // Fazer algo mais com as notas modulares, como passá-las para um outro módulo JS
        })
        .catch(error => {
            console.error('Erro:', error);
        });
}


