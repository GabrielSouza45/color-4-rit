async function iniciar () {

    console.log("Entrou notas");
    const inputMapa = document.getElementById("id-mapa");
    idMapa = inputMapa.value;
    mapa = await getMapa(idMapa);
    
    const musica = mapa.musica;
    const musicaNome = musica.nome;
    musicaDuracao = musica.duracao;
    
    const audio = new Audio(`../audio/${musicaNome}.mp3`)
    audio.play();
    
    teclasPressionadas = getTeclasPressionadas(musicaDuracao, audio);

}