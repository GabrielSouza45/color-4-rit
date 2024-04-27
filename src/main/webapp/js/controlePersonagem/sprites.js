class Sprite{
	
  constructor({position,adicionaMovimento, dimensions}){
	  this.position = position
	  this.adicionaMovimento = adicionaMovimento 
	  this.width = dimensions.width
	  this.height = dimensions.height
	  
	  
  }	//desenha o personagem
  draw(){
	  ctx.fillStyle ="white"

	  ctx.fillRect(this.position.x, this.position.y, this.width,this.height)
  }
  
  update(){
	  //atualiza a localizacao do personagem somando um valor para direcao
	   this.position.x += this.adicionaMovimento.x
	   this.position.y += this.adicionaMovimento.y
	   
	   this.draw()
	  
  }
  
}
//instancia do personagem
const jogador = new Sprite({
	position: {
		//largura
		x: 100,
		//altura
		y: 100
	},
	
	adicionaMovimento:{
		//largura
		x:0,
		//altura
		y:0
		
	},
	
	dimensions: {
		//tamanho do personagem
		width:50,
		height:50
		
	}
})


	
	
	
	
	