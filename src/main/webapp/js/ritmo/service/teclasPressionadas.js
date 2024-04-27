export function getTeclasPressionadas(duracaoMusica) {

    const tempoFinal = duracaoMusica;

    let tempoInicio = 0;
    let teclasPressionadas = [];

    iniciarMusica();

    // Função para iniciar a reprodução da música
    function iniciarMusica() {

      tempoInicio = Date.now(); // Registra o tempo de início
      console.log("Música começou a tocar.");


      const verificarTeclasInterval = setInterval(() => {
        console.log("a");
        if (Date.now() - tempoInicio >= tempoFinal) {

          clearInterval(verificarTeclasInterval); // Para de verificar as teclas pressionadas
          console.log("Música terminou de tocar.");

        } else {

          document.addEventListener("keydown", registrarTeclaPressionada);

        }

      }, 0); // Verificar a cada 0 milissegundos (o tempo todo)

    }


    // Função para registrar teclas pressionadas
    function registrarTeclaPressionada(event) {
      if (Date.now() - tempoInicio <= tempoFinal) {
        const tempoAtual = Date.now() - tempoInicio; // Calcula o tempo atual
        
        const teclaPressionada = {
          tecla: event.key,
          tempo: tempoAtual,
        };

        teclasPressionadas.push(teclaPressionada);
        console.log("Tecla pressionada:", teclaPressionada);
        return teclasPressionadas;

      }
    }

}
