document.addEventListener("DOMContentLoaded", function () {
	var character = document.querySelector(".character");
	var character2 = document.querySelector(".character2");
	var character3 = document.querySelector(".character3");
	var character4 = document.querySelector(".character4");
	var areaGame = document.querySelector(".area-game");
  
	areaGame.setAttribute("tabindex", "0");
  
	areaGame.addEventListener("keydown", function (event) {
	  var keyPressed = event.key.toLowerCase();
	  var newClass = "";
  
	  switch (keyPressed) {
		case "a":
		  newClass = "character-vermelho";
		  character.className = newClass;
		  character2.className = "";
		  character3.className = "";
		  character4.className = "";
		  break;
		case "s":
		  newClass = "character-verde";
		  character2.className = newClass;
		  character.className = "";
		  character3.className = "";
		  character4.className = "";
		  break;
		case "w":
		  newClass = "character-azul";
		  character3.className = newClass;
		  character2.className = "";
		  character.className = "";
		  character4.className = "";
		  break;
		case "d":
		  newClass = "character-amarelo";
		  character4.className = newClass;
		  character2.className = "";
		  character3.className = "";
		  character.className = "";
		  break;
		default:
		  return;
	  }
	});
  });
  