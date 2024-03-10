const controles = {
	w :{pressed:false},
	a :{pressed:false},
	s :{pressed:false},
	d :{pressed:false}
}

const velocidade = 6

window.addEventListener("keydown", teclaPressionada => {
	let key = teclaPressionada.key.toLowerCase()

	switch(key){
		case "ArrowUp":
		case "w":
			controles.w.pressed = true;
			 break

		case "ArrowLeft":
		case "a":
			controles.a.pressed = true;
			break

		case "ArrowDown":
		case "s":
			controles.s.pressed = true;
			 break

		case "ArrowRight":
	    case "d":
			controles.d.pressed = true;
			 break
	 }
})

window.addEventListener("keyup", teclaSoltada => {
	let key = teclaSoltada.key.toLowerCase()

	switch(key){
		case "ArrowUp":
		case "w":
			controles.w.pressed = false;
			break
        
        case "ArrowLeft":
        case "a":
            controles.a.pressed = false;
            break

        case "ArrowRight":
        case "d":
            controles.d.pressed = false;
            break

		case "ArrowDown":
		case "s":
			controles.s.pressed = false;
			break
	}
})

function funcaoDoMovimento(){
	movimento()
	
	function movimento(){
		jogador.adicionaMovimento.x = 0
		jogador.adicionaMovimento.y = 0

		if(controles.w.pressed){	
			jogador.adicionaMovimento.y = -velocidade
		}

		if(controles.a.pressed){
			jogador.adicionaMovimento.x = -velocidade
		}

		if(controles.s.pressed){
			jogador.adicionaMovimento.y = velocidade
		}

		if(controles.d.pressed){
			jogador.adicionaMovimento.x = velocidade	
		}
	}
}