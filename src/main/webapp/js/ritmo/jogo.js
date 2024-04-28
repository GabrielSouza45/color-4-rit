
import { getNotas } from "./api/getNotas.js";
import { getTeclasPressionadas } from "./service/teclasPressionadas.js";


const element = document.getElementById("botao-registrar");
element.addEventListener("click", async function () {
  console.log("Entrou aqui em");


  const idMapa = document.getElementById("id-mapa").value;

  // Notas
  let listNotas = [];
  let teclasPressionadas = [];
  let listTempos = [];

  // Cores
  const vermelho = document.getElementById("vermelho");
  const verde = document.getElementById("verde");
  const azul = document.getElementById("azul");
  const amarelo = document.getElementById("amarelo");

  
  // Pega notas no banco de dados
  await getNotas(idMapa)
    .then((notas) => {
      console.log("Notas DB:", notas);
      listNotas = notas;
      listTempos = notas;
      
    })
    .catch((error) => {
      const msg = "Erro ao obter notas:";
      console.error(msg, error);
      alert(msg);
      throw new Error(msg);
    });
    console.log("Lista Tempos:", listTempos);

    if (listNotas.length === 0) {
      alert("Música sem notas configuradas.");
      return;
    }


  console.log("Entrou notas");

  const mapa = listNotas[0].mapa;

  const musica = mapa.musica;
  const musicaNome = musica.nome;
  const musicaDuracao = musica.duracao;

  setTimeout(() => {
    
    const audio = new Audio(`../audio/${musicaNome}.mp3`)
    audio.play();

    teclasPressionadas = getTeclasPressionadas(musicaDuracao, audio);

    const tempoInicio = Date.now();
    let count = 0;

    const set = setInterval(() => {
      if (listTempos.length != count) {
        console.log("set");

        const tempo = listTempos[count].tempo;
        console.log("/");
        console.log(tempo);
        console.log(Date.now() - tempoInicio);
        console.log("\\");

        const aux = Date.now() - tempoInicio;
        if (tempo >= (aux - 10) && tempo <= (aux + 10)) {

          console.log("Executando trocas");


          const cor = listTempos[count].cor.toLowerCase();;
          console.log(cor);

          resetaCores();

          const quadrado = document.getElementById(cor);
          quadrado.style.background = `var(--${cor})`;


          count+=1;

        }

      } else {
        console.log("ListTempo Vazia");
        clearInterval(set);
      }

    }, 0);
    





    // Averiguacao de pontos
    setTimeout(() => {

        let pontos = 0;
        const margem = 50;

        listNotas.forEach((nota) => {

          teclasPressionadas.forEach((press) => {
            const tempoPress = press.tempo;
            const tempoNota = nota.tempo;

            if(tempoPress >= (tempoNota-margem) && tempoPress <= (tempoNota+margem)) {
              console.log("tempoPress-> ", tempoPress, "tempoNota-> ", tempoNota);
              pontos+=1;
            }

          });

        });

        console.log("Pontos: ", pontos);
    }, musicaDuracao+2000);


    function resetaCores(){

      console.log("ResetaCor");
      vermelho.style.background = 'var(--vermelho-background)';
      verde.style.background = 'var(--verde-background)';
      azul.style.background = 'var(--azul-background)';
      amarelo.style.background = 'var(--amarelo-background)';

    }

  }, 2000);
});

