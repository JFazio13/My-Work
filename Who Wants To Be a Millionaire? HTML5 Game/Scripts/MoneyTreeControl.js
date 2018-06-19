function renderMoneyTreeTable(){
	var htmlTableString = "";
	
	for (var i = 14; i >= 0; i--){
		htmlTableString += "<tr id='moneyTreeTr" + (i+1) + "'>";
		
		if((i+1)%5 === 0){
			htmlTableString += "<td class='moneyTreeAmountWhiteTd'>"
		}
		else{
			htmlTableString += "<td class='moneyTreeAmountTd'>"
		}
		
		if(i == 14)
		{
			htmlTableString += "$1 MILLION";
		}
		else
		{
			htmlTableString += accounting.formatMoney(window.GameVariables.PrizeAmounts[i], "$", 0);
		}

		
		htmlTableString += "</td>"
		htmlTableString += "</tr>"
	}
	
	$('.moneyTreeTable').html(htmlTableString);

	setTreeColors();
}

function highlightMoneyTree(counter, speed) {
	var targetFlash = "#flash" + counter;
	
	$(targetFlash).transition({perspective:0, opacity: 1}, 250, 'linear', function(){
		$(targetFlash).transition({perspective:0, opacity: 0}, 750, 'linear');
	});
	
	if(counter <= 14){
		counter++;
		setTimeout(function(){
			highlightMoneyTree(counter, speed);
		}, speed);
	}
}

function setTreeColors() {
	var colorChange = '#moneyTreeTr';

	if(window.GameVariables.QuestionLevel >= 2) {
		$(colorChange + '1 td').css('color', '#787878');
	}
	if(window.GameVariables.QuestionLevel >= 3) {
		$(colorChange + '2 td').css('color', '#787878');
	}
	if(window.GameVariables.QuestionLevel >= 4) {
		$(colorChange + '3 td').css('color', '#787878');
	}
	if(window.GameVariables.QuestionLevel >= 5) {
		$(colorChange + '4 td').css('color', '#787878');
	}
	if(window.GameVariables.QuestionLevel >= 6) {
		$(colorChange + '5 td').css('color', '#787878');
	}
	if(window.GameVariables.QuestionLevel >= 7) {
		$(colorChange + '6 td').css('color', '#787878');
	}
	if(window.GameVariables.QuestionLevel >= 8) {
		$(colorChange + '7 td').css('color', '#787878');
	}
	if(window.GameVariables.QuestionLevel >= 9) {
		$(colorChange + '8 td').css('color', '#787878');
	}
	if(window.GameVariables.QuestionLevel >= 10) {
		$(colorChange + '9 td').css('color', '#787878');
	}
	if(window.GameVariables.QuestionLevel >= 11) {
		$(colorChange + '10 td').css('color', '#787878');
	}
	if(window.GameVariables.QuestionLevel >= 12) {
		$(colorChange + '11 td').css('color', '#787878');
	}
	if(window.GameVariables.QuestionLevel >= 13) {
		$(colorChange + '12 td').css('color', '#787878');
	}
	if(window.GameVariables.QuestionLevel >= 14) {
		$(colorChange + '13 td').css('color', '#787878');
	}
	if(window.GameVariables.QuestionLevel == 15) {
		$(colorChange + '14 td').css('color', '#787878');
	}
}

function introduceTree() {
	$('.glow').css('opacity', 1);
	$('.glow').transition({perspective:0, 'top': '175px'}, 1750, 'linear');
	setTimeout(function(){
		$( ".glow" ).fadeOut("slow", function() {
			$('.glow').css('top', '1925px');
		});
		$( ".glow" ).fadeIn("slow", function() {
			$('.glow').css('top', '950px');
			$('.glow').css('opacity', 0);
		});
	}, 0);
}

function scaleTreeLevels(level) {
	var targetId = '#moneyTreeTr' + level;
	var colorChange = '#moneyTreeTr';

	$(targetId + ' td').css('font-size', '26pt');
	$(targetId + ' td').css('line-height', '1');

	if(window.GameVariables.QuestionLevel <= 5) {
		if(level == 5) {
			$(colorChange + '5 td').css('color', '#FFFFFF');
		}
		if(level == 10) {
			$(colorChange + '10 td').css('color', '#FFFFFF');
		}
		if(level == 15) {
			$(colorChange + '15 td').css('color', '#FFFFFF');
		}
	} else if(window.GameVariables.QuestionLevel <= 10) {
		if(level == 10) {
			$(colorChange + '10 td').css('color', '#FFFFFF');
		}
		if(level == 15) {
			$(colorChange + '15 td').css('color', '#FFFFFF');
		}
	} else if(window.GameVariables.QuestionLevel <= 15) {
		if(level == 15) {
			$(colorChange + '15 td').css('color', '#FFFFFF');
		}
	}
	
	$(targetId + ' td').html(window.GameVariables.QuestionsAndAnswers[level - 1].Category);

	if(level < 15){
		window.GameVariables.ScaleTreeTimeout = setTimeout(function(){
			scaleTreeLevels(level + 1);
		}, 52);
	} else {
		if(window.GameVariables.IsExplainingRules == false) {
			setTimeout(function(){
				revealBar(window.GameVariables.QuestionLevel);
			}, 800);
		}
	}
}

function slideInMoneyTree() {
	$('.bar').hide();
	$('.moneyTreeDiv').transition({perspective:0, 'right': '0px'}, 750, 'linear');
	startShortActiveSound("money_tree_on.mp3");
	window.GameVariables.TreeScaled = false;
	setTreeColors();
}

function slideOutMoneyTree(){
	$('.moneyTreeDiv').transition({perspective:0, 'right': '-760px'}, 780, 'linear');
	startShortPassiveSound("money_tree_slide.mp3");
	setTimeout(function() {
		unscaleTreeLevels();
	}, 800);
}

function revealBar(level) {
	var top = 952;
	if(level == 1) {
		$('.bar').css('top', top);
	} else if(level == 2) {
		$('.bar').css('top', top - 53);
	} else if(level == 3) {
		$('.bar').css('top', top - 107);
	} else if(level == 4) {
		$('.bar').css('top', top - 160);
	} else if(level == 5) {
		$('.bar').css('top', top - 214);
	} else if(level == 6) {
		$('.bar').css('top', top - 267);
	} else if(level == 7) {
		$('.bar').css('top', top - 320);
	} else if(level == 8) {
		$('.bar').css('top', top - 373);
	} else if(level == 9) {
		$('.bar').css('top', top - 427);
	} else if(level == 10) {
		$('.bar').css('top', top - 480);
	} else if(level == 11) {
		$('.bar').css('top', top - 533);
	} else if(level == 12) {
		$('.bar').css('top', top - 587);
	} else if(level == 13) {
		$('.bar').css('top', top - 639);
	} else if(level == 14) {
		$('.bar').css('top', top - 693);
	} else if(level == 15) {
		$('.bar').css('top', top - 747);
	}
	
	$( ".bar" ).fadeIn("slow", function() {
    	// Animation complete.
  	});
	startBarSound("reveal_bar.mp3");
}

function setLevelOnMoneyTree(level){
	var top = 952;
	if(level == 1) {
		$('.bar').css('top', top);
	} else if(level == 2) {
		$('.bar').css('top', top - 53);
	} else if(level == 3) {
		$('.bar').css('top', top - 107);
	} else if(level == 4) {
		$('.bar').css('top', top - 160);
	} else if(level == 5) {
		$('.bar').css('top', top - 214);
	} else if(level == 6) {
		$('.bar').css('top', top - 267);
	} else if(level == 7) {
		$('.bar').css('top', top - 320);
	} else if(level == 8) {
		$('.bar').css('top', top - 373);
	} else if(level == 9) {
		$('.bar').css('top', top - 427);
	} else if(level == 10) {
		$('.bar').css('top', top - 480);
	} else if(level == 11) {
		$('.bar').css('top', top - 533);
	} else if(level == 12) {
		$('.bar').css('top', top - 587);
	} else if(level == 13) {
		$('.bar').css('top', top - 639);
	} else if(level == 14) {
		$('.bar').css('top', top - 693);
	} else if(level == 15) {
		$('.bar').css('top', top - 747);
	}
	
	$( ".bar" ).fadeIn("slow", function() {
    	// Animation complete.
  	});
}

function setFlashOnMoneyTree(level){
	var flash = '#flash' + level;

	$(flash).fadeIn("slow", function() {
    	$(flash).css('opacity', 1);
  	});
}

function unscaleTreeLevels(){
	clearTimeout(window.GameVariables.ScaleTreeTimeout);
	renderMoneyTreeTable();
	setLevelOnMoneyTree(window.GameVariables.QuestionLevel);
}

function unscaleTreeFade(){
	$( ".moneyTreeTable" ).fadeOut("slow", function() {
    	// Animation complete.
  	});

	setTimeout(function(){
		renderMoneyTreeTable();
	}, 600);

	$( ".moneyTreeTable" ).fadeIn("slow", function() {
    	// Animation complete.
  	});
}

function showMoneyTree(){
	$('.moneyTreeDiv').css('opacity', 1);
}

function hideMoneyTree(){
	$('.moneyTreeDiv').css('opacity', 0);
}

function hideLevelStrap(){
	$('.currentLevelStrapDiv').transition({perspective:0, 'right':'-648px'}, 750, 'linear');
}

function showLevelStrap(){
	if(window.GameVariables.QuestionLevel < 15){
		$('.currentLevelStrapAmountDiv').html(accounting.formatMoney(window.GameVariables.PrizeAmounts[window.GameVariables.QuestionLevel - 1], "$", 0));
	}
	else{
		$('.currentLevelStrapAmountDiv').html("$1 MILLION");
		$('.sideStrapGoldImg').css('opacity', 1);
	}
	$('.currentLevelStrapDiv').transition({perspective:0, 'right':'0px'}, 750, 'linear');
}

function animateLevelStrapGlow(){
	
}







