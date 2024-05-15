export function getCorTecla(tecla) {
    let cor;
    switch (tecla) {
      case "w":
        cor = "VERMELHO";
        break;
      case "a":
        cor = "AZUL";
        break;
      case "d":
        cor = "VERDE";
        break;
      case "s":
        cor = "AMARELO";
        break;
      default:
        cor = "ERRADO";
        break;
    }
    return cor;
  }