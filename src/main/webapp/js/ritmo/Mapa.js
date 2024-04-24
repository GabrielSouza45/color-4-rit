import Musica from './Musica.js'

class Mapa {
    constructor(id, dificuldade, musica) {
        this.id = id,
        this.dificuldade = dificuldade,
        this.musica = new Musica(musica.autor, musica.nome, musica.duracao)
    }
};

export default Mapa;