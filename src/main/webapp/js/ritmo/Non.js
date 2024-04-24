import Mapa from './Mapa.js';

class Nota {
    constructor (id, cor, tempo, mapa) {
        this.id = id
        this.cor = cor,
        this.tempo = tempo,
        this.mapa = new Mapa(mapa.id, mapa.dificuldade, mapa.musica)
    }
}

export default Nota;