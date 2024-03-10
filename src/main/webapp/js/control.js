const controles = {
	w :{
		pressed:false
	},
	a :{
		pressed:false
	},s :{
		
		pressed:false
		
	}, d: {
		
		pressed:false
		
	},
	
	
}




window.addEventListener("keydown", teclaPressionada => {
	 let key = teclaPressionada.key
	
	 
	 switch(key){
		 case "ArrowUp":
		 case "w":
			controles.w.pressed = true;
			funcaoDoMovimento()
			controles.w.pressed = false;
			 break
		case "ArrowLeft":
		case "a":
			controles.a.pressed = true;
			funcaoDoMovimento()
			controles.a.pressed = false;
			break
		case "ArrowDown":
		case "s":
			controles.s.pressed = true;
			funcaoDoMovimento()
			controles.s.pressed = false;
			 break
		case "ArrowRight":
	   case "d":
			controles.d.pressed = true;
			funcaoDoMovimento()
			 controles.d.pressed = false;
			 break
			  
		 
	 }
	
})


function funcaoDoMovimento(){
	movimento()
	
	function movimento(){
		 jogador.adicionaMovimento.x = 0
		 jogador.adicionaMovimento.y = 0
		 
		 if(controles.w.pressed){
			 
			jogador.adicionaMovimento.y = -6
			
		 }
		  if(controles.a.pressed){
			 jogador.adicionaMovimento.x = -6
		 }
		  if(controles.s.pressed){
			 jogador.adicionaMovimento.y = 6
		 }
		  if(controles.d.pressed){
			 jogador.adicionaMovimento.x = 6
			 
		 }
		
	}
	
	
	
}