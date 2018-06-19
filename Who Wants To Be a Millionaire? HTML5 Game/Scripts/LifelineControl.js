/****************************************************************************************************************/
/* Phone a Friend Life Line Functions */
/****************************************************************************************************************/

function pafPulseLifeLine(){
	$('.pafLifeLine').transition({perspective:0, transform:"scale(1.25,1.25)"}, 250, 'linear', function(){
		$('.pafLifeLine').transition({perspective:0, transform:"scale(1,1)"}, 500, 'linear', function(){
			
		});
	});
	
	$('.pafLifeLine .lifelineYellowTreeImg').transition({perspective:0, opacity: 1}, 250, 'linear', function(){
		$('.pafLifeLine .lifelineYellowTreeImg').transition({perspective:0, opacity: 0}, 500, 'linear', function(){
			
		});
	});
	
	$('.pafStrapLifeLine').transition({perspective:0, transform:"scale(1.25,1.25)"}, 250, 'linear', function(){
		$('.pafStrapLifeLine').transition({perspective:0, transform:"scale(1,1)"}, 500, 'linear', function(){
			
		});
	});
	
	$('.pafStrapLifeLine .lifelineYellowStrapImg').transition({perspective:0, opacity: 1}, 250, 'linear', function(){
		$('.pafStrapLifeLine .lifelineYellowStrapImg').transition({perspective:0, opacity: 0}, 500, 'linear', function(){
			
		});
	});
}

function pafLifeLineDisable(){
	$('.pafLifeLine .lifelineUsedImg').css('opacity', 1);
	$('.pafLifeLine .lifelineTreeImg').css('opacity', 0.5);
	$('.pafStrapLifeLine .lifelineUsedStrapImg').css('opacity', 1);
	$('.pafStrapLifeLine .lifelineStrapImg').css('opacity', 0.5);
	window.GameVariables.IsPAFLifeLineUsed = true;
}

function pafRevealClock(){
	$('.pafClockDiv').transition({perspective:0, right:'300px'}, 750, 'linear', function(){
		setTimeout(function(){
			pafCountDownClock(0);
			startLifelinePassiveSound("paf_countdown.mp3");
			setTimeout(stopLifelineActiveSound, 200);
		}, 250);
	});
}

function pafCountDownClock(timeConsumed){
	if(timeConsumed == 30){
		$('.pafClockTimeDiv').html("");
	}
	else{
		$('.pafClockTimeDiv').html(30 - timeConsumed);
	}
	
	var currentRotation = 0;
	
	if(timeConsumed < 15){
		currentRotation = (timeConsumed * 12) + 6;
		
		$('#pafRightRingImg').css("transform","rotate(" + currentRotation + "deg)");
				
		window.GameVariables.PAFClockTimeout = setTimeout(function(){
			currentRotation += 6;
			
			$('#pafRightRingImg').css("transform","rotate(" + currentRotation + "deg)");
			
			window.GameVariables.PAFClockTimeout = setTimeout(function(){ pafCountDownClock(timeConsumed + 1); }, 496);
		}, 490);
	}
	else if(timeConsumed < 30){
		currentRotation = ((timeConsumed - 15) * 12) + 6;
		
		$('#pafLeftRingImg').css("transform","rotate(" + currentRotation + "deg)");
				
		window.GameVariables.PAFClockTimeout = setTimeout(function(){
			currentRotation += 6;
			
			$('#pafLeftRingImg').css("transform","rotate(" + currentRotation + "deg)");

			window.GameVariables.PAFClockTimeout = setTimeout(function(){ pafCountDownClock(timeConsumed + 1); }, 496);
		}, 490);
	}
	else{
		window.GameVariables.PAFClockTimeout = setTimeout(pafHideClock, 500);
	}
}

function pafEndClockEarly(){
	clearTimeout(window.GameVariables.PAFClockTimeout);
	pafHideClock();
	startLifelineActiveSound("paf_end_call_early.mp3");
}

function pafHideClock(){
	$('.pafClockDiv').transition({perspective:0, right:'-400px'}, 750, 'linear', function(){
		$('.pafClockTimeDiv').html(30);
		$('#pafRightRingImg').transition({perspective:0, transform:"rotate(0)"}, 1, 'linear');
		$('#pafLeftRingImg').transition({perspective:0, transform:"rotate(0)"}, 1, 'linear');
		window.GameVariables.pafLifeLineSequenceCounter = 0;
		window.GameVariables.CannotLockInFinalAnswer = false;
		pafLifeLineDisable();
	});
	
	startShortPassiveSound("game_resume.mp3");
}

/****************************************************************************************************************/
/* Fifty-Fifty Life Line Functions */
/****************************************************************************************************************/

function ffPulseLifeLine(){
	$('.ffLifeLine').transition({perspective:0, transform:"scale(1.25,1.25)"}, 250, 'linear', function(){
		$('.ffLifeLine').transition({perspective:0, transform:"scale(1,1)"}, 500, 'linear', function(){
			
		});
	});
	
	$('.ffLifeLine .lifelineYellowTreeImg').transition({perspective:0, opacity: 1}, 250, 'linear', function(){
		$('.ffLifeLine .lifelineYellowTreeImg').transition({perspective:0, opacity: 0}, 500, 'linear', function(){
			
		});
	});
	
	$('.ffStrapLifeLine').transition({perspective:0, transform:"scale(1.25,1.25)"}, 250, 'linear', function(){
		$('.ffStrapLifeLine').transition({perspective:0, transform:"scale(1,1)"}, 500, 'linear', function(){
			
		});
	});
	
	$('.ffStrapLifeLine .lifelineYellowStrapImg').transition({perspective:0, opacity: 1}, 250, 'linear', function(){
		$('.ffStrapLifeLine .lifelineYellowStrapImg').transition({perspective:0, opacity: 0}, 500, 'linear', function(){
			
		});
	});
}

function ffLifeLineDisable(){
	$('.ffLifeLine .lifelineUsedImg').css('opacity', 1);
	$('.ffLifeLine .lifelineTreeImg').css('opacity', 0.5);
	$('.ffStrapLifeLine .lifelineUsedStrapImg').css('opacity', 1);
	$('.ffStrapLifeLine .lifelineStrapImg').css('opacity', 0.5);
	window.GameVariables.IsFFLifeLineUsed = true;
}

function ffLifeLineRestore(){
	$('.ffLifeLine .lifelineUsedImg').css('opacity', 0);
	$('.ffLifeLine .lifelineTreeImg').css('opacity', 0);
	$('.ffStrapLifeLine .lifelineUsedStrapImg').css('opacity', 0);
	$('.ffStrapLifeLine .lifelineStrapImg').css('opacity', 0);
}

function ffRemoveTwoWrongAnswers(){
	var canRemoveTwo = true;
	var counter = 0;
		
	if(window.GameVariables.AnswerAIsOut == true || window.GameVariables.AnswerBIsOut == true || window.GameVariables.AnswerCIsOut == true || window.GameVariables.AnswerDIsOut == true){
		canRemoveTwo = false;
	}
	
	if(canRemoveTwo == true){
		ffLifeLineDisable();
	
		while(counter < 2){
			var randomAnswerValue = Math.ceil(Math.random() * 4);
			
			if(randomAnswerValue == 1 && window.GameVariables.AnswerAIsOut == false && window.GameVariables.CurrentCorrectAnswer != "a"){
				$('#answerA .letterP').css('opacity', 0);
				$('#answerA .answerP').css('opacity', 0);
				$('#answerA .diagonalImg').css('opacity', 0);
				window.GameVariables.AnswerAIsOut = true;
				counter++;
			}
			
			if(randomAnswerValue == 2 && window.GameVariables.AnswerBIsOut == false && window.GameVariables.CurrentCorrectAnswer != "b"){
				$('#answerB .letterP').css('opacity', 0);
				$('#answerB .answerP').css('opacity', 0);
				$('#answerB .diagonalImg').css('opacity', 0);
				window.GameVariables.AnswerBIsOut = true;
				counter++;
			}
			
			if(randomAnswerValue == 3 && window.GameVariables.AnswerCIsOut == false && window.GameVariables.CurrentCorrectAnswer != "c"){
				$('#answerC .letterP').css('opacity', 0);
				$('#answerC .answerP').css('opacity', 0);
				$('#answerC .diagonalImg').css('opacity', 0);
				window.GameVariables.AnswerCIsOut = true;
				counter++;
			}
			
			if(randomAnswerValue == 4 && window.GameVariables.AnswerDIsOut == false && window.GameVariables.CurrentCorrectAnswer != "d"){
				$('#answerD .letterP').css('opacity', 0);
				$('#answerD .answerP').css('opacity', 0);
				$('#answerD .diagonalImg').css('opacity', 0);
				window.GameVariables.AnswerDIsOut = true;
				counter++;
			}
		}
	}
}

/****************************************************************************************************************/
/* Ask the Audience Life Line Functions */
/****************************************************************************************************************/

function ataPulseLifeLine(){
	$('.ataLifeLine').transition({perspective:0, transform:"scale(1.25,1.25)"}, 250, 'linear', function(){
		$('.ataLifeLine').transition({perspective:0, transform:"scale(1,1)"}, 500, 'linear', function(){
			
		});
	});	
	
	$('.ataLifeLine .lifelineYellowTreeImg').transition({perspective:0, opacity: 1}, 250, 'linear', function(){
		$('.ataLifeLine .lifelineYellowTreeImg').transition({perspective:0, opacity: 0}, 500, 'linear', function(){
			
		});
	});
	
	$('.ataStrapLifeLine').transition({perspective:0, transform:"scale(1.25,1.25)"}, 250, 'linear', function(){
		$('.ataStrapLifeLine').transition({perspective:0, transform:"scale(1,1)"}, 500, 'linear', function(){
			
		});
	});
	
	$('.ataStrapLifeLine .lifelineYellowStrapImg').transition({perspective:0, opacity: 1}, 250, 'linear', function(){
		$('.ataStrapLifeLine .lifelineYellowStrapImg').transition({perspective:0, opacity: 0}, 500, 'linear', function(){
			
		});
	});
}

function ataLifeLineDisable(){
	$('.ataLifeLine .lifelineUsedImg').css('opacity', 1);
	$('.ataLifeLine .lifelineTreeImg').css('opacity', 0.5);
	$('.ataStrapLifeLine .lifelineUsedStrapImg').css('opacity', 1);
	$('.ataStrapLifeLine .lifelineStrapImg').css('opacity', 0.5);
	window.GameVariables.IsATALifeLineUsed = true;
}

/*function slideInATAGraph(){
	$('.ataGraphDiv').transition({perspective:0, right:"300px"}, 750, 'linear');
}*/

function slideInATAGraph(){
	$('.ataGraphDiv').transition({perspective:0, right:"300px"}, 10, 'linear');

	$(".ataGraph").fadeIn("slow", function() {
	    // Animation complete
	});
}

function calculateGraphPercentages(){
	var percentageOfDifficulty = (window.GameVariables.QuestionLevel - 1) * 3.5;
	var skewResults = (Math.random() * 100) > 98 ? true : false;
	
	if(skewResults != true){

		var random = Math.random();
    	while(random < 0.4) {
    		random = Math.random();
    	}

    	console.log(random);

		if(window.GameVariables.CurrentCorrectAnswer == "a" && window.GameVariables.AnswerAIsOut == false){
			window.GameVariables.AskTheAudienceVotingPercents[0] = Math.ceil(random * (100 - percentageOfDifficulty));
			window.GameVariables.AskTheAudienceVotingPercents[1] = window.GameVariables.AnswerBIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[0]));
			window.GameVariables.AskTheAudienceVotingPercents[2] = window.GameVariables.AnswerCIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[0] - window.GameVariables.AskTheAudienceVotingPercents[1]));
			window.GameVariables.AskTheAudienceVotingPercents[3] = window.GameVariables.AnswerDIsOut == true ? 0 : 100 - window.GameVariables.AskTheAudienceVotingPercents[0] - window.GameVariables.AskTheAudienceVotingPercents[1]  - window.GameVariables.AskTheAudienceVotingPercents[2];
		}
		
		if(window.GameVariables.CurrentCorrectAnswer == "b" && window.GameVariables.AnswerBIsOut == false){
			window.GameVariables.AskTheAudienceVotingPercents[1] = Math.ceil(random * (100 - percentageOfDifficulty));
			window.GameVariables.AskTheAudienceVotingPercents[2] = window.GameVariables.AnswerCIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[1]));
			window.GameVariables.AskTheAudienceVotingPercents[3] = window.GameVariables.AnswerDIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[1] - window.GameVariables.AskTheAudienceVotingPercents[2]));
			window.GameVariables.AskTheAudienceVotingPercents[0] = window.GameVariables.AnswerAIsOut == true ? 0 : 100 - window.GameVariables.AskTheAudienceVotingPercents[1] - window.GameVariables.AskTheAudienceVotingPercents[2]  - window.GameVariables.AskTheAudienceVotingPercents[3];
		}
		
		if(window.GameVariables.CurrentCorrectAnswer == "c" && window.GameVariables.AnswerCIsOut == false){
			window.GameVariables.AskTheAudienceVotingPercents[2] = Math.ceil(random * (100 - percentageOfDifficulty));
			window.GameVariables.AskTheAudienceVotingPercents[3] = window.GameVariables.AnswerDIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[2]));
			window.GameVariables.AskTheAudienceVotingPercents[0] = window.GameVariables.AnswerAIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[2] - window.GameVariables.AskTheAudienceVotingPercents[3]));
			window.GameVariables.AskTheAudienceVotingPercents[1] = window.GameVariables.AnswerBIsOut == true ? 0 : 100 - window.GameVariables.AskTheAudienceVotingPercents[2] - window.GameVariables.AskTheAudienceVotingPercents[3]  - window.GameVariables.AskTheAudienceVotingPercents[0];
		}
		
		if(window.GameVariables.CurrentCorrectAnswer == "d" && window.GameVariables.AnswerDIsOut == false){
			window.GameVariables.AskTheAudienceVotingPercents[3] = Math.ceil(random * (100 - percentageOfDifficulty));
			window.GameVariables.AskTheAudienceVotingPercents[0] = window.GameVariables.AnswerAIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[3]));
			window.GameVariables.AskTheAudienceVotingPercents[1] = window.GameVariables.AnswerBIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[3] - window.GameVariables.AskTheAudienceVotingPercents[0]));
			window.GameVariables.AskTheAudienceVotingPercents[2] = window.GameVariables.AnswerCIsOut == true ? 0 : 100 - window.GameVariables.AskTheAudienceVotingPercents[3] - window.GameVariables.AskTheAudienceVotingPercents[0]  - window.GameVariables.AskTheAudienceVotingPercents[1];
		}
	}
	else{

		console.log("DEVIOUS");

		if(window.GameVariables.AnswerAIsOut == false){
			window.GameVariables.AskTheAudienceVotingPercents[0] = Math.ceil(Math.random() * (100));
			window.GameVariables.AskTheAudienceVotingPercents[1] = window.GameVariables.AnswerBIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[0]));
			window.GameVariables.AskTheAudienceVotingPercents[2] = window.GameVariables.AnswerCIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[0] - window.GameVariables.AskTheAudienceVotingPercents[1]));
			window.GameVariables.AskTheAudienceVotingPercents[3] = window.GameVariables.AnswerDIsOut == true ? 0 : 100 - window.GameVariables.AskTheAudienceVotingPercents[0] - window.GameVariables.AskTheAudienceVotingPercents[1]  - window.GameVariables.AskTheAudienceVotingPercents[2];
		}
		
		if(window.GameVariables.AnswerBIsOut == false){
			window.GameVariables.AskTheAudienceVotingPercents[1] = Math.ceil(Math.random() * (100));
			window.GameVariables.AskTheAudienceVotingPercents[2] = window.GameVariables.AnswerCIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[1]));
			window.GameVariables.AskTheAudienceVotingPercents[3] = window.GameVariables.AnswerDIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[1] - window.GameVariables.AskTheAudienceVotingPercents[2]));
			window.GameVariables.AskTheAudienceVotingPercents[0] = window.GameVariables.AnswerAIsOut == true ? 0 : 100 - window.GameVariables.AskTheAudienceVotingPercents[1] - window.GameVariables.AskTheAudienceVotingPercents[2]  - window.GameVariables.AskTheAudienceVotingPercents[3];
		}
		
		if(window.GameVariables.AnswerCIsOut == false){
			window.GameVariables.AskTheAudienceVotingPercents[2] = Math.ceil(Math.random() * (100));
			window.GameVariables.AskTheAudienceVotingPercents[3] = window.GameVariables.AnswerDIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[2]));
			window.GameVariables.AskTheAudienceVotingPercents[0] = window.GameVariables.AnswerAIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[2] - window.GameVariables.AskTheAudienceVotingPercents[3]));
			window.GameVariables.AskTheAudienceVotingPercents[1] = window.GameVariables.AnswerBIsOut == true ? 0 : 100 - window.GameVariables.AskTheAudienceVotingPercents[2] - window.GameVariables.AskTheAudienceVotingPercents[3]  - window.GameVariables.AskTheAudienceVotingPercents[0];
		}
		
		if(window.GameVariables.AnswerDIsOut == false){
			window.GameVariables.AskTheAudienceVotingPercents[3] = Math.ceil(Math.random() * (100));
			window.GameVariables.AskTheAudienceVotingPercents[0] = window.GameVariables.AnswerAIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[3]));
			window.GameVariables.AskTheAudienceVotingPercents[1] = window.GameVariables.AnswerBIsOut == true ? 0 : Math.ceil(Math.random() * (100 - window.GameVariables.AskTheAudienceVotingPercents[3] - window.GameVariables.AskTheAudienceVotingPercents[0]));
			window.GameVariables.AskTheAudienceVotingPercents[2] = window.GameVariables.AnswerCIsOut == true ? 0 : 100 - window.GameVariables.AskTheAudienceVotingPercents[3] - window.GameVariables.AskTheAudienceVotingPercents[0]  - window.GameVariables.AskTheAudienceVotingPercents[1];
		}
	}
	
	var sumOfAllPercents = window.GameVariables.AskTheAudienceVotingPercents[0] + window.GameVariables.AskTheAudienceVotingPercents[1] + window.GameVariables.AskTheAudienceVotingPercents[2] + window.GameVariables.AskTheAudienceVotingPercents[3];
		
	if(sumOfAllPercents < 100){
		if(window.GameVariables.CurrentCorrectAnswer == "a" && window.GameVariables.AnswerAIsOut == false){
			window.GameVariables.AskTheAudienceVotingPercents[0] += 100 - sumOfAllPercents;
		}
		
		if(window.GameVariables.CurrentCorrectAnswer == "b" && window.GameVariables.AnswerBIsOut == false){
			window.GameVariables.AskTheAudienceVotingPercents[1] += 100 - sumOfAllPercents;
		}
		
		if(window.GameVariables.CurrentCorrectAnswer == "c" && window.GameVariables.AnswerCIsOut == false){
			window.GameVariables.AskTheAudienceVotingPercents[2] += 100 - sumOfAllPercents;
		}
		
		if(window.GameVariables.CurrentCorrectAnswer == "d" && window.GameVariables.AnswerDIsOut == false){
			window.GameVariables.AskTheAudienceVotingPercents[3] += 100 - sumOfAllPercents;
		}
	}
	
	window.GameVariables.AskTheAudienceVotingPercents[0] = window.GameVariables.AskTheAudienceVotingPercents[0]/100;
	window.GameVariables.AskTheAudienceVotingPercents[1] = window.GameVariables.AskTheAudienceVotingPercents[1]/100;
	window.GameVariables.AskTheAudienceVotingPercents[2] = window.GameVariables.AskTheAudienceVotingPercents[2]/100;
	window.GameVariables.AskTheAudienceVotingPercents[3] = window.GameVariables.AskTheAudienceVotingPercents[3]/100;
}

function revealGraphPercentages(){
	console.log("STEP 1");
	calculateGraphPercentages();
	
	var topHeightForAnswerBarA = Math.ceil(Math.random() * 121) + 121;
	var topHeightForAnswerBarB = Math.ceil(Math.random() * 121) + 121;
	var topHeightForAnswerBarC = Math.ceil(Math.random() * 121) + 121;
	var topHeightForAnswerBarD = Math.ceil(Math.random() * 121) + 121;
	
	var finalHeightForAnswerBarA = Math.round(window.GameVariables.AskTheAudienceVotingPercents[0] * 242);
	var finalHeightForAnswerBarB = Math.round(window.GameVariables.AskTheAudienceVotingPercents[1] * 242);
	var finalHeightForAnswerBarC = Math.round(window.GameVariables.AskTheAudienceVotingPercents[2] * 242);
	var finalHeightForAnswerBarD = Math.round(window.GameVariables.AskTheAudienceVotingPercents[3] * 242);

	$('#ataPercentStrapA').html(Math.round(window.GameVariables.AskTheAudienceVotingPercents[0] * 100) + "%");
	$('#ataPercentStrapB').html(Math.round(window.GameVariables.AskTheAudienceVotingPercents[1] * 100) + "%");
	$('#ataPercentStrapC').html(Math.round(window.GameVariables.AskTheAudienceVotingPercents[2] * 100) + "%");
	$('#ataPercentStrapD').html(Math.round(window.GameVariables.AskTheAudienceVotingPercents[3] * 100) + "%");
	

	if(window.GameVariables.AnswerAIsOut === false){
		$('#graphBarA').css('height','0px');
		$('#graphBarA').transition({height:topHeightForAnswerBarA+"px"}, 1100, function(){
			$('#graphBarA').transition({height:finalHeightForAnswerBarA+"px"}, 600);
		});
	
		jQuery({ PrimaryCounter: 0 }).animate({ PrimaryCounter: ((topHeightForAnswerBarA / 242) * 100) }, {
			duration: 600,
			easing: 'linear',
			step: function () {
				$('#graphPercentA').html(Math.round(this.PrimaryCounter) + "%");
			},
			complete: function(){ 
				jQuery({ SecondaryCounter: ((topHeightForAnswerBarA / 220) * 100) }).animate({ SecondaryCounter: (window.GameVariables.AskTheAudienceVotingPercents[0] * 100) }, {
					duration: 600,
					easing: 'linear',
					step: function () {
						$('#graphPercentA').html(Math.round(this.SecondaryCounter) + "%");
					},
					complete: function(){
						var percentA = Math.round(window.GameVariables.AskTheAudienceVotingPercents[0] * 100) + "%";
						
						$('#graphPercentA').html(percentA);
					}
				});
			}
		});
	}
	
	if(window.GameVariables.AnswerBIsOut === false){
		$('#graphBarB').css('height','0px');
		$('#graphBarB').transition({height:topHeightForAnswerBarB+"px"}, 1100, function(){
			$('#graphBarB').transition({height:finalHeightForAnswerBarB+"px"}, 600);
		});
	
		jQuery({ PrimaryCounter: 0 }).animate({ PrimaryCounter: ((topHeightForAnswerBarB / 242) * 100) }, {
			duration: 600,
			easing: 'linear',
			step: function () {
				$('#graphPercentB').html(Math.round(this.PrimaryCounter) + "%");
			},
			complete: function(){ 
				jQuery({ SecondaryCounter: ((topHeightForAnswerBarB / 242) * 100) }).animate({ SecondaryCounter: (window.GameVariables.AskTheAudienceVotingPercents[1] * 100) }, {
					duration: 600,
					easing: 'linear',
					step: function () {
						$('#graphPercentB').html(Math.round(this.SecondaryCounter) + "%");
					},
					complete: function(){
						var percentB = Math.round(window.GameVariables.AskTheAudienceVotingPercents[1] * 100) + "%";
						
						$('#graphPercentB').html(percentB);
					}
				});
			}
		});
	}
	
	if(window.GameVariables.AnswerCIsOut === false){
		$('#graphBarC').css('height','0px');
		$('#graphBarC').transition({height:topHeightForAnswerBarC+"px"}, 1100, function(){
			$('#graphBarC').transition({height:finalHeightForAnswerBarC+"px"}, 600);
		});
	
		jQuery({ PrimaryCounter: 0 }).animate({ PrimaryCounter: ((topHeightForAnswerBarC / 242) * 100) }, {
			duration: 600,
			easing: 'linear',
			step: function () {
				$('#graphPercentC').html(Math.round(this.PrimaryCounter) + "%");
			},
			complete: function(){ 
				jQuery({ SecondaryCounter: ((topHeightForAnswerBarB / 242) * 100) }).animate({ SecondaryCounter: (window.GameVariables.AskTheAudienceVotingPercents[2] * 100) }, {
					duration: 600,
					easing: 'linear',
					step: function () {
						$('#graphPercentC').html(Math.round(this.SecondaryCounter) + "%");
					},
					complete: function(){
						var percentC = Math.round(window.GameVariables.AskTheAudienceVotingPercents[2] * 100) + "%";
						
						$('#graphPercentC').html(percentC);
					}
				});
			}
		});
	}
	
	if(window.GameVariables.AnswerDIsOut === false){
		$('#graphBarD').css('height','0px');
		$('#graphBarD').transition({height:topHeightForAnswerBarD+"px"}, 1100, function(){
			$('#graphBarD').transition({height:finalHeightForAnswerBarD+"px"}, 600);
		});
		
		jQuery({ PrimaryCounter: 0 }).animate({ PrimaryCounter: ((topHeightForAnswerBarD / 242) * 100) }, {
			duration: 600,
			easing: 'linear',
			step: function () {
				$('#graphPercentD').html(Math.round(this.PrimaryCounter) + "%");
			},
			complete: function(){ 
				jQuery({ SecondaryCounter: ((topHeightForAnswerBarB / 242) * 100) }).animate({ SecondaryCounter: (window.GameVariables.AskTheAudienceVotingPercents[3] * 100) }, {
					duration: 600,
					easing: 'linear',
					step: function () {
						$('#graphPercentD').html(Math.round(this.SecondaryCounter) + "%");
					},
					complete: function(){
						var percentD = Math.round(window.GameVariables.AskTheAudienceVotingPercents[3] * 100) + "%";
						
						$('#graphPercentD').html(percentD);
						
						determineHighestPercentagesToFlash();
					}
				});
			}
		});
	}
	else{
		jQuery({ PrimaryCounter: 0 }).animate({ PrimaryCounter: 0 }, {
			duration: 600,
			easing: 'linear',
			step: function () {},
			complete: function(){ 
				jQuery({ SecondaryCounter: 0 }).animate({ SecondaryCounter: 0 }, {
					duration: 600,
					easing: 'linear',
					step: function () {},
					complete: function(){
						determineHighestPercentagesToFlash();
					}
				});
			}
		});
	}
}

function determineHighestPercentagesToFlash(){
	/* Must manually set the percents on the final call because rounding does not working correctly when doing calculations based on dimensions */
	var flashes = [true, true, true, true];
	
	var highestPercentSoFar = window.GameVariables.AskTheAudienceVotingPercents[0];
	var percentsProcessed = 0;
	
	while (percentsProcessed < 4){
		for(var i = 0; i < 4; i++){
			if(window.GameVariables.AskTheAudienceVotingPercents[i] < highestPercentSoFar){
				flashes[i] = false;
			}
			else if(window.GameVariables.AskTheAudienceVotingPercents[i] > highestPercentSoFar){
				highestPercentSoFar = window.GameVariables.AskTheAudienceVotingPercents[i];
			}
		}
		percentsProcessed++;
	}
	
	for (var j = 0; j < 4; j++){
		if(flashes[j] == true){
			var targetElement;
			
			if(j === 0){
				targetElement = "#graphBarA .ask-the-audience-graph-answer-bar-highest-flash-div";
			}
			else if(j === 1){
				targetElement = "#graphBarB .ask-the-audience-graph-answer-bar-highest-flash-div";
			}
			else if(j === 2){
				targetElement = "#graphBarC .ask-the-audience-graph-answer-bar-highest-flash-div";
			}
			else if(j === 3){
				targetElement = "#graphBarD .ask-the-audience-graph-answer-bar-highest-flash-div";
			}
			
			flashHighestPercentage(targetElement);
		}
	}
}

function flashHighestPercentage(targetElement){
	$(targetElement).transition({opacity:1}, 250, 'linear', function(){
		$(targetElement).transition({opacity:0}, 750, 'linear');
	});
}

function slideOutATAGraph(){
	$(".ataGraph").fadeOut("slow", function() {
	    // Animation complete
	});
	$(".ataGraphDiv").fadeOut("slow", function() {
	    // Animation complete
	});

	setTimeout(function(){
		$('.ataGraphDiv').transition({perspective:0, right:"-330px"}, 10, 'linear', function(){
			$('#graphBarA').css('height', "0px");
			$('#graphBarB').css('height', "0px");
			$('#graphBarC').css('height', "0px");
			$('#graphBarD').css('height', "0px");
			$('#graphPercentA').html("");
			$('#graphPercentB').html("");
			$('#graphPercentC').html("");
			$('#graphPercentD').html("");
			ataLifeLineDisable();
			window.GameVariables.IsATALifeLineUsed = true;
		});
	}, 350);
}

/****************************************************************************************************************/
/* Switch the Question Life Line Functions */
/****************************************************************************************************************/

function stqLifeLineSlideIn(){
	$('.ffLifeLine').transition({perspective:0, 'left':'220px'}, 500, 'linear');
	$('.ataLifeLine').transition({perspective:0, 'left':'370px'}, 500, 'linear');
	$('.stqLifeLine').transition({perspective:0, 'left':'520px'}, 500, 'linear');
	
	$('.ffStrapLifeLine').transition({perspective:0, 'left':'625px'}, 500, 'linear');
	$('.ataStrapLifeLine').transition({perspective:0, 'left':'850px'}, 500, 'linear');
	$('.stqStrapLifeLine').transition({perspective:0, 'left':'1075px'}, 500, 'linear');

	startBarSound('extra_lifeline_1.mp3');
}

function stqLifeLineSlideOut(){
	window.GameVariables.LifeLineAnimationCounter = 0;
	$('.ffLifeLine').transition({perspective:0, 'left':'270px'}, 500, 'linear');
	$('.ataLifeLine').transition({perspective:0, 'left':'470px'}, 500, 'linear');
	$('.stqLifeLine').transition({perspective:0, 'left':'870px'}, 500, 'linear');
	
	$('.ffStrapLifeLine').transition({perspective:0, 'left':'745px'}, 500, 'linear');
	$('.ataStrapLifeLine').transition({perspective:0, 'left':'1090px'}, 500, 'linear');
	$('.stqStrapLifeLine').transition({perspective:0, 'left':'1870px'}, 500, 'linear');
}

function stqPulseLifeLine(){
	$('.stqLifeLine').transition({perspective:0, transform:"scale(1.25,1.25)"}, 250, 'linear', function(){
		$('.stqLifeLine').transition({perspective:0, transform:"scale(1,1)"}, 500, 'linear', function(){
			
		});
	});
	
	$('.stqLifeLine .lifelineYellowTreeImg').transition({perspective:0, opacity: 1}, 250, 'linear', function(){
		$('.stqLifeLine .lifelineYellowTreeImg').transition({perspective:0, opacity: 0}, 500, 'linear', function(){
			
		});
	});
	
	$('.stqStrapLifeLine').transition({perspective:0, transform:"scale(1.25,1.25)"}, 250, 'linear', function(){
		$('.stqStrapLifeLine').transition({perspective:0, transform:"scale(1,1)"}, 500, 'linear', function(){
			
		});
	});
	
	$('.stqStrapLifeLine .lifelineYellowStrapImg').transition({perspective:0, opacity: 1}, 250, 'linear', function(){
		$('.stqStrapLifeLine .lifelineYellowStrapImg').transition({perspective:0, opacity: 0}, 500, 'linear', function(){
			
		});
	});
}

function stqLifeLineDisable(){
	$('.stqLifeLine .lifelineUsedImg').css('opacity', 1);
	$('.stqLifeLine .lifelineTreeImg').css('opacity', 0.5);
	$('.stqStrapLifeLine .lifelineUsedStrapImg').css('opacity', 1);
	$('.stqStrapLifeLine .lifelineStrapImg').css('opacity', 0.5);
}

function switchOutToNewQuestion(){
	clearTimeout(window.GameVariables.ShowAnswerTimeout);
	resetAnswerStraps();
	stqLifeLineDisable();
	
	$('.questionStrapDiv').transition({perspective:0, transform:"rotateX(-90deg)"}, 400, function(){
		setQuestion(true);		
		$('.questionStrapDiv').transition({perspective:0, transform:"rotateX(0deg)"}, 400);
	});
}

/****************************************************************************************************************/
/* Double Dip Life Line Functions */
/****************************************************************************************************************/

function ddPulseLifeLine(){
	$('.ffLifeLine').transition({perspective:0, transform:"scale(1.25,1.25)"}, 250, 'linear', function(){
		$('.ffLifeLine').transition({perspective:0, transform:"scale(1,1)"}, 500, 'linear', function(){
			
		});
	});
	
	$('.ffLifeLine .lifelineYellowTreeImg').transition({perspective:0, opacity: 1}, 250, 'linear', function(){
		$('.ffLifeLine .lifelineYellowTreeImg').transition({perspective:0, opacity: 0}, 500, 'linear', function(){
			
		});
	});
	
	$('.ffStrapLifeLine').transition({perspective:0, transform:"scale(1.25,1.25)"}, 250, 'linear', function(){
		$('.ffStrapLifeLine').transition({perspective:0, transform:"scale(1,1)"}, 500, 'linear', function(){
			
		});
	});
	
	$('.ffStrapLifeLine .lifelineYellowStrapImg').transition({perspective:0, opacity: 1}, 250, 'linear', function(){
		$('.ffStrapLifeLine .lifelineYellowStrapImg').transition({perspective:0, opacity: 0}, 500, 'linear', function(){
			
		});
	});
}

function ddLifeLineDisable(){
	$('.ffLifeLine .lifelineUsedImg').css('opacity', 1);
	$('.ffLifeLine .lifelineTreeImg').css('opacity', 0.5);
	$('.ffStrapLifeLine .lifelineUsedStrapImg').css('opacity', 1);
	$('.ffStrapLifeLine .lifelineStrapImg').css('opacity', 0.5);
	
	$('.superDdLifeLine .superLifelineUsedImg').css('opacity', 1);
	$('.superDdLifeLine .superLifelineTreeImg').css('opacity', 0.5);
	$('.superDdStrapLifeLine .superLifelineUsedStrapImg').css('opacity', 1);
	$('.superDdStrapLifeLine .superLifelineStrapImg').css('opacity', 0.5);
	window.GameVariables.IsDDLifeLineUsed = true;
}

function ddShadowFirstWrongAnswer(answer){
	$('#answer' + answer + ' .letterP').css({'opacity':0.25, 'color':'#FFAD16'});
	$('#answer' + answer + ' .answerP').css({'opacity':0.25, 'color':'white'});
	$('#answer' + answer + ' .diagonalImg').attr('src', 'Images/orange_diagonal.png');
	$('#answer' + answer + ' .diagonalImg').css('opacity', 0.25);
	$('#answer' + answer + ' .finalImg').css('opacity', 0);
	
	if(answer == "A"){
		window.GameVariables.AnswerAIsOut = true;
	}
	
	if(answer == "B"){
		window.GameVariables.AnswerBIsOut = true;
	}
	
	if(answer == "C"){
		window.GameVariables.AnswerCIsOut = true;
	}
	
	if(answer == "D"){
		window.GameVariables.AnswerDIsOut = true;
	}
}


/****************************************************************************************************************/
/* Other Life Line Functions */
/****************************************************************************************************************/

function showLifeLineCentered(target){
	window.GameVariables.ContinuePulsingLifeLineCenter = true;
	window.GameVariables.ShowLifeLineCenteredAnimation = true;
	
	$(target).css('opacity', 1);
		
	$('.answerStrapLifeLineCenterContainerDiv').transition({perspective:0, transform:"scale(1,1)", opacity:1}, 200, 'linear', function(){
		pulseLifeLineCenteredGlow();
	});
}

function pulseLifeLineCenteredGlow(){
	$('.lifelineCenterGlowImg').transition({perspective:0, transform:"scale(1.2,1.2)"}, 1000, function(){
		$('.lifelineCenterGlowImg').transition({perspective:0, transform:"scale(1,1)"}, 1000, function(){
			if(window.GameVariables.ContinuePulsingLifeLineCenter == true){
				setTimeout(pulseLifeLineCenteredGlow, 1000);
			}
		});
	});
}

function hideLifeLineCentered(){
	window.GameVariables.ContinuePulsingLifeLineCenter = false;
	window.GameVariables.ShowLifeLineCenteredAnimation = false;
	$('.answerStrapLifeLineCenterContainerDiv').transition({perspective:0, transform:"scale(0.2,0.2)", opacity:0}, 200, 'linear', function(){
		$('.lifelineCenterImg').css('opacity', 0);
	});
}

function hideJustLifeLineCenteredContainer(){
	$('.answerStrapLifeLineCenterDiv').css('opacity', 0);
}

function showJustLifeLineCenteredContainer(){
	$('.answerStrapLifeLineCenterDiv').css('opacity', 1);
}

function slideLifeLineStrapIn(){
	$('.lifeLinesLeftStrapDiv').transition({perspective:0, 'left':'112px'}, 500);
}

function slideLifeLineStrapOut(){
	$('.lifeLinesLeftStrapDiv').transition({perspective:0, 'left':'-1696px'}, 500, function(){
		$('.lifeLinesLeftStrapDiv').transition({perspective:0, 'left':'1920px'}, 1, 'linear');
	});
}