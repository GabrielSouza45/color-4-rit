document.addEventListener("DOMContentLoaded", function () {
	var character = document.querySelector(".character");
	var character2 = document.querySelector(".character2");
	var character3 = document.querySelector(".character3");
	var character4 = document.querySelector(".character4");
	var areaGame = document.querySelector(".area-game");
  

  
	document.addEventListener("keydown", function (event) {
	  var keyPressed = event.key.toLowerCase();
	  var newClass = "";
  
	  switch (keyPressed) {
		case "w":
		  newClass = "character-vermelho";
		  character3.className = newClass;
		  character2.className = "";
		  character.className = "";
		  character4.className = "";
		  break;
		case "d":
		  newClass = "character-verde";
		  character4.className = newClass;
		  character.className = "";
		  character3.className = "";
		  character2.className = "";
		  break;
		case "a":
		  newClass = "character-azul";
		  character.className = newClass;
		  character2.className = "";
		  character3.className = "";
		  character4.className = "";
		  break;
		case "s":
		  newClass = "character-amarelo";
		  character2.className = newClass;
		  character4.className = "";
		  character3.className = "";
		  character.className = "";
		  break;
		default:
		  return;
	  }
	});
  });
  