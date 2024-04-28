import Mapa from "./Mapa.js";

class Nota {
  constructor(id, cor, tempo, status, mapa) {
      this.id = id,
      this.cor = cor,
      this.tempo = tempo,
      this.status = status
      this.mapa = new Mapa(mapa.id, mapa.dificuldade, mapa.musica);
      console.log("Nota");
  }
}

export default Nota;
