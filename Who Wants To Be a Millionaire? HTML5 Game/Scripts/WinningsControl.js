function showAmountWon(){
	$('.winningsP').html(accounting.formatMoney(window.GameVariables.PrizeAmounts[window.GameVariables.QuestionLevel - 1], "$", 0));
	$('.totalPrizeMoneyWonDiv').html(accounting.formatMoney(window.GameVariables.PrizeAmounts[window.GameVariables.QuestionLevel - 1], "$", 0));
	$('.winStrapDiv').css('opacity', 1);
}

function hideAmountWon(){
	$('.winStrapDiv').css('opacity', 0);
}

function reduceAmountWon(){
	if(window.GameVariables.QuestionLevel < 6){
		$('.totalPrizeMoneyWonDiv').html("$0");
	}
	else if(window.GameVariables.QuestionLevel < 11){
		$('.totalPrizeMoneyWonDiv').html(accounting.formatMoney(window.GameVariables.PrizeAmounts[4], "$", 0));
	}
	else {
		$('.totalPrizeMoneyWonDiv').html(accounting.formatMoney(window.GameVariables.PrizeAmounts[9], "$", 0));
	}
}

function showMillionaireTitleStrap(){
	$('.millionaireWinnerTitleDiv').css('opacity', 1);
}

function hideMillionaireTitleStrap(){
	$('.millionaireWinnerTitleDiv').css('opacity', 0);
}

function showTotalPrizeMoneyStrap(){
	$('.totalPrizeMoneyDiv').css('opacity', 1);
}

function hideTotalPrizeMoneyStrap(){
	$('.totalPrizeMoneyDiv').css('opacity', 0);
}