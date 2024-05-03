import { getNotas } from "./api/getNotas.js";
import { getTeclasPressionadas } from "./service/teclasPressionadas.js";

console.log("Entrou aqui jogo.js");

// Notas
let listNotas = [];
let teclasPressionadas = [];

// Musica
let audio;

let averiguaPontos;
let set;

let idMapa;

const buttonIniciar = document.getElementById("iniciar-jogo");
buttonIniciar.addEventListener("click", () => {
  const idMapa = document.getElementById("id-mapa").value;
  iniciarGame(idMapa);
});

const buttonReiniciar = document.getElementById("reiniciar-jogo");
buttonReiniciar.addEventListener("click", () => {
  teclasPressionadas = [];
  audio.pause();
  clearInterval(set);
  clearInterval(averiguaPontos);

  iniciarGame();
});

async function iniciarGame() {
  console.log("Entrou aqui em");

  // Cores
  const vermelho = document.getElementById("vermelho");
  const verde = document.getElementById("verde");
  const azul = document.getElementById("azul");
  const amarelo = document.getElementById("amarelo");
  const telaProximaCor = document.getElementById("tela-proxima-nota");

  // Pega notas no banco de dados
  await getNotas(idMapa)
    .then((notas) => {
      console.log("Notas DB:", notas);
      listNotas = notas;
    })
    .catch((error) => {
      const msg = "Erro ao obter notas:";
      console.error(msg, error);
      alert(msg);
      throw new Error(msg);
    });

  if (listNotas.length === 0) {
    alert("Música sem notas configuradas.");
    return;
  }

  console.log("Entrou notas");

  const mapa = listNotas[0].mapa;

  const musica = mapa.musica;
  const musicaNome = musica.nome;
  const musicaDuracao = musica.duracao;

  setProximaCor(listNotas[0].cor.toLowerCase());

  // Inicia Jogo
  setTimeout(() => {
    audio = new Audio(`../audio/${musicaNome}.mp3`);
    audio.play();

    teclasPressionadas = getTeclasPressionadas(musicaDuracao, audio);

    const tempoInicio = Date.now();
    let count = 0;

    set = setInterval(() => {
      if (listNotas.length != count) {
        console.log("set");

        const tempo = listNotas[count].tempo;
        console.log("/");
        console.log(tempo);
        console.log(Date.now() - tempoInicio);
        console.log("\\");

        const aux = Date.now() - tempoInicio;
        if (tempo >= aux - 10 && tempo <= aux + 10) {
          if (count < listNotas.length - 1) {
            const proximoTempo = listNotas[count + 1].tempo;
            const tempoRestante = proximoTempo - tempo;

            setProximaCor(
              listNotas[count + 1].cor.toLowerCase(),
              tempoRestante
            );
            console.log("Executando trocas");
          }

          const cor = listNotas[count].cor.toLowerCase();
          console.log(cor);

          resetaCores();

          const quadrado = document.getElementById(cor);
          quadrado.style.background = `var(--${cor})`;

          count += 1;
        }
      } else {
        console.log("ListTempo Vazia");
        clearInterval(set);
      }
    }, 0);

    // Averiguacao de pontos
    averiguaPontos = setTimeout(() => {
      console.log(listNotas);
      console.log(teclasPressionadas);

      let pontos = 0;
      const margem = 100;

      listNotas.forEach((nota) => {
        teclasPressionadas.forEach((press) => {
          const tempoPress = press.tempo;
          const tempoNota = nota.tempo;
          const corPress = getNotaCor(press.tecla);
          const corNota = nota.cor;

          if (
            tempoPress >= tempoNota - margem &&
            tempoPress <= tempoNota + margem
          ) {
            if (corPress === corNota) {
              console.log(
                "tempoPress-> ",
                tempoPress,
                "tempoNota-> ",
                tempoNota
              );
              pontos += 1;
            }
          }
        });
      });

      console.log("Pontos: ", pontos);
      alert(`Jogo finalizado, total de ponto: ${pontos}!`);
    }, musicaDuracao + 2000);

    function resetaCores() {
      console.log("ResetaCor");
      vermelho.style.background = "var(--vermelho-background)";
      verde.style.background = "var(--verde-background)";
      azul.style.background = "var(--azul-background)";
      amarelo.style.background = "var(--amarelo-background)";
    }
  }, 2000);

  function setProximaCor(proximaCor, tempo) {
    console.log("Setando proxima cor: ", `var(--${proximaCor})`);
    telaProximaCor.style.background = `var(--${proximaCor})`;

    const proximoQuadrado = document.getElementById(proximaCor);
    proximoQuadrado.style.transitionDuration = tempo + "ms";
    proximoQuadrado.style.backgroundColor = `var(--${proximaCor})`;
  }

  function getNotaCor(tecla) {
    let cor;
    switch (tecla) {
      case "a":
        cor = "VERMELHO";
        break;
      case "w":
        cor = "AZUL";
        break;
      case "s":
        cor = "VERDE";
        break;
      case "d":
        cor = "AMARELO";
        break;
      default:
        cor = "ERRADO";
        break;
    }
    return cor;
  }
}
