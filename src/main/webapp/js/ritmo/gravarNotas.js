import { addNotas } from "./api/addNotas.js";
import { deleteNotas } from "./api/deleteNotas.js";
import { getMapa } from "./api/getMapa.js";
import { getNotas } from "./api/getNotas.js";
import Nota from "./model/Nota.js";
import { getTeclasPressionadas } from "./service/teclasPressionadas.js";

const element = document.getElementById("iniciar-gravacao");
element.addEventListener('click', async () => {

    const confirm = window.confirm("Se continuar, excluirá todas as notas já registradas!");

    if(!confirm) {
        return;
    }
        
    const timer = 5000;

    setTimeout( // Adiciona um atraso de 5 segundos para iniciar a musica
        async () => {
        

            let teclasPressionadas = [];
            let listNotas = [];
            let idMapa;
            let musicaDuracao;
            let mapa;

            console.log("Entrou notas");
            const inputMapa = document.getElementById("id-mapa");
            idMapa = inputMapa.value;
            mapa = await getMapa(idMapa);
        
            const musica = mapa.musica;
            const musicaNome = musica.nome;
            musicaDuracao = musica.duracao;
        
            const audio = new Audio(`../audio/${musicaNome}.mp3`)
            audio.play();
        
            teclasPressionadas = getTeclasPressionadas(musicaDuracao, audio);
        

            setTimeout( 
                async () => {
                    console.log("AVANCOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO ", idMapa);

                    await getNotas(idMapa)
                    .then((notas) => {
                        listNotas = notas;
                    })
                    .catch((err) => {
                        console.error(err);
                        throw new Error(err);
                    })

                    if (listNotas.length > 0) {
                        await deleteNotas(idMapa)
                            .catch((err) => {
                                throw new Error(err);
                            });
                    } 
                    let listaPressionados = [];

                    console.log(teclasPressionadas);

                    try {
                        teclasPressionadas.forEach(press => {
                        console.log("entrou");
                        console.log(press);
                            let cor;
                        
                            const tecla = press.tecla;
                            const tempo = press.tempo;
                        
                            switch (tecla) {
                                case 'a':
                                    cor = 'VERMELHO'
                                    break;
                                case 'w':
                                    cor = 'AZUL'
                                    break;
                                case 's':
                                    cor = 'VERDE'
                                    break;   
                                case 'd':
                                    cor = 'AMARELO'
                                    break;   
                                default:
                                    cor = 'VERMELHO'
                                    break;  
                            }
                        
                            listaPressionados.push(new Nota(0, cor, tempo, 1, mapa))
                        
                        });
                    } catch (err) {
                        throw new Error(err);
                    }
                    
                    addNotas(listaPressionados);

                }, musicaDuracao+2000
            );

        }, timer
    );
    
});


