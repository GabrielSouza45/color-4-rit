const canvas = document.querySelector('canvas');
const ctx = canvas.getContext('2d');
//tamanho do canvas
const canvasLargura = 350;
const canvasAltura = 350;

canvas.width = canvasLargura;
canvas.height = canvasAltura;


//funcao de renderizaçao e atualizacao da tela
setInterval(()=>{
    ctx.clearRect(0,0,350,350)

    jogador.update()
    funcaoDoMovimento()
    
})



