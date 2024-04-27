import { getNotas } from "./TesteNotas.js";

let listNotas = [];

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
});

// const arrow =
//   () => {
    // Duracao da musica EM MILISSEGUNDOS
    const tempoFinal = 12000;

    let tempoInicio = 0;
    let teclasPressionadas = [];


    // Função para iniciar a reprodução da música
    function iniciarMusica() {

      tempoInicio = Date.now(); // Registra o tempo de início
      console.log("Música começou a tocar.");


      // Registrar teclas pressionadas durante a reprodução da música
      // Verificar continuamente se uma tecla é pressionada
      const verificarTeclasInterval = setInterval(() => {
        console.log("a");
        if (Date.now() - tempoInicio >= tempoFinal) {

          // Verifica se a música terminou
          clearInterval(verificarTeclasInterval); // Para de verificar as teclas pressionadas
          console.log("Música terminou de tocar.");

        } else {

          document.addEventListener("keydown", registrarTeclaPressionada);

        }

    }, 100); // Verificar a cada 100 milissegundos

      // Cod para iniciar musica
    }



    // Função para registrar teclas pressionadas
    function registrarTeclaPressionada(event) {
      const tempoAtual = Date.now() - tempoInicio; // Calcula o tempo atual

      const teclaPressionada = {
        tecla: event.key,
        tempo: tempoAtual,
      };

      teclasPressionadas.push(teclaPressionada);
      console.log("Tecla pressionada:", teclaPressionada);

    }



    // Iniciar a reprodução da música quando a tecla "Enter" for pressionada
    document.addEventListener("keydown", function (event) {
      if (event.key === "Enter") {
        iniciarMusica();
      }
    });




    // Tempo final da música (substitua isso pelo tempo real da sua música)

  // };
