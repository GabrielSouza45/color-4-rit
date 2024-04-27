export function getTeclasPressionadas(duracaoMusica, audio) {

    const tempoFinal = duracaoMusica;

    let tempoInicio = 0;
    let teclasPressionadas = [];

    iniciarMusica();

    
    function iniciarMusica() {

      tempoInicio = Date.now(); 
      console.log("Música começou a tocar.");


      const verificarTeclasInterval = setInterval(() => {
        console.log("--");
        if (Date.now() - tempoInicio >= tempoFinal) {

          clearInterval(verificarTeclasInterval); 
          console.log("Música terminou de tocar.");
          audio.pause();
        } else {

          document.addEventListener("keydown", registrarTeclaPressionada);

        }

      }, 0); // Verificar a cada 0 milissegundos (o tempo todo)

    }


    function registrarTeclaPressionada(event) {
      if (Date.now() - tempoInicio <= tempoFinal) {
        const tempoAtual = Date.now() - tempoInicio; 
        
        const teclaPressionada = {
          tecla: event.key,
          tempo: tempoAtual,
        };

        teclasPressionadas.push(teclaPressionada);
        console.log("Tecla pressionada:", teclaPressionada);

      }
    }

    return teclasPressionadas;
}
