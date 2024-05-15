import { getNotas } from "./api/getNotas.js";
import { getTeclasPressionadas } from "./service/teclasPressionadas.js";
import { atualizaPlacar } from "./service/placar.js";
import { salvarPlacar } from "./api/salvarPlacar.js";
import PlacarRequest from "./model/placarRequest.js";
import { getCorTecla } from "./service/getCorTecla.js";

// Notas
let listNotas = [];
let teclasPressionadas = [];

// Musica
let audio;

let averiguaPontos;
let set;

let idMapa;
let idJogador;

// Cores
const vermelho = document.getElementById("vermelho");
const verde = document.getElementById("verde");
const azul = document.getElementById("azul");
const amarelo = document.getElementById("amarelo");
const telaProximaCor = document.getElementById("tela-proxima-nota");

const buttonIniciar = document.getElementById("iniciar-jogo");
buttonIniciar.addEventListener("click", () => {
  idMapa = document.getElementById("id-mapa").value;
  idJogador = 1;

  atualizaPlacar(idMapa, idJogador);
 
  iniciarGame(idMapa);
});

const buttonReiniciar = document.getElementById("reiniciar-jogo");
buttonReiniciar.addEventListener("click", () => {
  teclasPressionadas = [];
  audio.pause();
  clearInterval(set);
  clearInterval(averiguaPontos);
  resetaCores();

  iniciarGame();
});

async function iniciarGame() {

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

  console.log("Inicio Jogo!");

  const mapa = listNotas[0].mapa;

  const musica = mapa.musica;
  const musicaNome = musica.nome;
  const musicaDuracao = musica.duracao;

  setProximaCor(listNotas[0].cor.toLowerCase(), 0);

  // Inicia Jogo
  let tempoInicio = 0;
  let count = 0;

  setTimeout(() => {
    // Define Música
    audio = new Audio(`../audio/${musicaNome}.mp3`);
    audio.play();

    // Inicia GetTeclas
    teclasPressionadas = getTeclasPressionadas(musicaDuracao, audio);

    tempoInicio = Date.now();
    set = setInterval(() => {
      if (listNotas.length != count) {
        const tempoNota = listNotas[count].tempo;
        const tempoAtual = Date.now() - tempoInicio;

        if (tempoNota >= tempoAtual - 10 && tempoNota <= tempoAtual + 10) {
          if (count < listNotas.length - 1) {
            const proximoTempo = listNotas[count + 1].tempo;
            const tempoRestante = proximoTempo - tempoNota;

            setProximaCor(
              listNotas[count + 1].cor.toLowerCase(),
              tempoRestante
            );
          } else {
            setUltimaCor();
          }

          const cor = listNotas[count].cor.toLowerCase();
          console.log(cor);

          resetaCores();

          const quadrado = document.getElementById(cor);
          quadrado.style.background = `var(--${cor})`;

          count += 1;
        }
      } else {
        clearInterval(set);
      }
    }, 0);

    // Averiguacao de pontos
    averiguaPontos = setTimeout(async () => {
      let pontos = 0;
      const margem = 200;

      listNotas.forEach((nota) => {
        teclasPressionadas.forEach((press) => {
          const tempoPress = press.tempo;
          const tempoNota = nota.tempo;
          const corPress = getCorTecla(press.tecla);
          const corNota = nota.cor;

          if (
            tempoPress >= tempoNota - margem &&
            tempoPress <= tempoNota + margem
          ) {
            if (corPress === corNota) {
              pontos += 1;
            }
          }
        });
      });

      resetaCores();

      alert(`Jogo finalizado, total de pontos: ${pontos}/${listNotas.length}!`);

      // Salvar Placar
      await salvarPlacar(new PlacarRequest(idMapa, idJogador, pontos)) 
        .then ( () => {
          setTimeout(() => {
            atualizaPlacar(idMapa, idJogador); 
          }, 2000);
        });

    }, musicaDuracao + 2000);
  }, 2000);

  // Set Cor Display
  function setProximaCor(proximaCor) {
    console.log("Setando proxima cor: ", `var(--${proximaCor})`);
    telaProximaCor.style.background = `var(--${proximaCor})`;
  }

  function setUltimaCor() {
    telaProximaCor.style.background = `#000`;
  }

  // Feedback Visual
  document.addEventListener("keydown", (event) => {
    const teclaPressionada = getCorTecla(event.key.toLowerCase());
    const notaAtual = listNotas[count - 1];
    const timing = verificaTiming(notaAtual);

    const quadrado = document.getElementById(teclaPressionada.toLowerCase());
    if (notaAtual.cor === teclaPressionada && timing) {
      quadrado.style.boxShadow = "inset 0 0 10px 5px white";
    } else {
      quadrado.style.boxShadow = "inset 0 0 10px 5px red";
    }
    setTimeout(() => {
      quadrado.style.boxShadow = "none";
    }, 300);
  });

  function verificaTiming(nota) {
    const margem = 200;
    const tempoAtual = Date.now() - tempoInicio;
    return (
      tempoAtual >= nota.tempo - margem && tempoAtual <= nota.tempo + margem
    );
  }
}

// Reseta para Cores Opacas
function resetaCores() {
  vermelho.style.background = "var(--vermelho-background)";
  verde.style.background = "var(--verde-background)";
  azul.style.background = "var(--azul-background)";
  amarelo.style.background = "var(--amarelo-background)";
}
