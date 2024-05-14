export default class PlacarRequest {
    constructor(idMapa, idJogador = null, pontuacao = null) {
        this.idMapa = idMapa,
        this.idJogador = idJogador,
        this.pontuacao = pontuacao
    }
}