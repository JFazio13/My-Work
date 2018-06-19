function hideMillionaireLogo(){
	window.GameVariables.IsLogoShowing = false;
	$('.logoContainerDiv').transition({perspective:0,opacity:0}, 1000, 'linear');
}

function showMillionaireLogo(){
	window.GameVariables.IsLogoShowing = true;
	$('.logoContainerDiv').transition({perspective:0,opacity:1}, 1000, 'linear');
	animateLogoBeams();
}

function animateLogoBeams(){
	$('#logoBeam1Img').transition({perspective:0, opacity:0.5}, 2000, 'linear', function(){
		$('#logoBeam1Img').transition({perspective:0, opacity:1}, 2000, 'linear');
	});
	
	$('#logoBeam2Img').transition({perspective:0, opacity:1}, 2000, 'linear', function(){
		$('#logoBeam2Img').transition({perspective:0, opacity:0.5}, 2000, 'linear', function(){
			if(window.GameVariables.IsLogoShowing == true){
				animateLogoBeams();
			}
		});
	});
}

function animateDots(dot) {

	if(dot == 0) {
		setTimeout(function(){
			animateDots(1);
		}, 250);
	} else {
		if(dot == 1) {
			$("#dot1").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right1").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 2) {
			$("#dot2").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right2").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 3) {
			$("#dot3").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right3").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 4) {
			$("#dot4").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right4").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 5) {
			$("#dot5").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right5").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 6) {
			$("#dot6").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right6").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 7) {
			$("#dot7").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right7").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 8) {
			$("#dot8").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right8").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 9) {
			$("#dot9").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right9").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 10) {
			$("#dot10").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right10").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 11) {
			$("#dot11").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right11").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 12) {
			$("#dot12").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right12").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 13) {
			$("#dot13").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right13").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 14) {
			$("#dot14").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right14").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 15) {
			$("#dot15").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right15").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 16) {
			$("#dot1").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right1").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark1").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right1").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 17) {
			$("#dot2").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right2").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark2").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right2").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 18) {
			$("#dot3").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right3").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark3").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right3").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 19) {
			$("#dot4").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right4").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark4").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right4").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 20) {
			$("#dot5").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right5").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark5").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right5").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 21) {
			$("#dot6").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right6").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark6").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right6").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 22) {
			$("#dot7").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right7").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark7").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right7").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 23) {
			$("#dot8").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right8").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark8").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right8").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 24) {
			$("#dot9").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right9").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark9").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right9").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 25) {
			$("#dot10").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right10").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark10").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right10").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 26) {
			$("#dot11").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right11").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark11").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right11").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 27) {
			$("#dot12").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right12").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark12").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right12").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 28) {
			$("#dot13").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right13").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark13").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right13").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 29) {
			$("#dot14").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right14").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark14").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right14").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 30) {
			$("#dot15").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right15").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_dark15").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_dark_right15").fadeIn("fast", function() {
				    // Animation complete
			});
		}

		dot++;

		if(dot <= 30) {
			setTimeout(function(){
				animateDots(dot);
			}, 33);
		}
	}
}

function animateGoldDots(dot) {

	if(dot == 0) {
		setTimeout(function(){
			animateGoldDots(1);
		}, 250);
	} else {
		if(dot == 1) {
			$("#dot1").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right1").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 2) {
			$("#dot2").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right2").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 3) {
			$("#dot3").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right3").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 4) {
			$("#dot4").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right4").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 5) {
			$("#dot5").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right5").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 6) {
			$("#dot6").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right6").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 7) {
			$("#dot7").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right7").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 8) {
			$("#dot8").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right8").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 9) {
			$("#dot9").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right9").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 10) {
			$("#dot10").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right10").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 11) {
			$("#dot11").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right11").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 12) {
			$("#dot12").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right12").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 13) {
			$("#dot13").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right13").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 14) {
			$("#dot14").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right14").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 15) {
			$("#dot15").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#dot_right15").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 16) {
			$("#dot1").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right1").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark1").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right1").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 17) {
			$("#dot2").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right2").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark2").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right2").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 18) {
			$("#dot3").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right3").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark3").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right3").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 19) {
			$("#dot4").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right4").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark4").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right4").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 20) {
			$("#dot5").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right5").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark5").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right5").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 21) {
			$("#dot6").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right6").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark6").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right6").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 22) {
			$("#dot7").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right7").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark7").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right7").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 23) {
			$("#dot8").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right8").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark8").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right8").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 24) {
			$("#dot9").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right9").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark9").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right9").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 25) {
			$("#dot10").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right10").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark10").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right10").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 26) {
			$("#dot11").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right11").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark11").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right11").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 27) {
			$("#dot12").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right12").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark12").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right12").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 28) {
			$("#dot13").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right13").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark13").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right13").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 29) {
			$("#dot14").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right14").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark14").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right14").fadeIn("fast", function() {
				    // Animation complete
			});
		} else if(dot == 30) {
			$("#dot15").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#dot_right15").fadeOut("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark15").fadeIn("fast", function() {
				    // Animation complete
			});
			$("#gold_dot_dark_right15").fadeIn("fast", function() {
				    // Animation complete
			});
		}

		dot++;

		if(dot <= 30) {
			setTimeout(function(){
				animateGoldDots(dot);
			}, 33);
		}
	}
}

function beginClock(timeFinish) {
	window.GameVariables.ClockStopped = false;
	window.GameVariables.ClockDone = false;
	console.log("Category: " + window.GameVariables.QuestionsAndAnswers[window.GameVariables.QuestionLevel - 1].Category);
	setTimeout(function(){
		startMainClock(0, timeFinish);
	}, 50);
}

function setClock(time) {
	$(".dot").hide();
	$(".dot_dark").hide();

	if(window.GameVariables.QuestionLevel < 15) {
		$(".dot_dark_gold").hide();
		$(".clock_gold").hide();
	} else {
		$(".dot_dark").hide();
		$(".dot_dark_gold").hide();
		$(".clock_gold").show();
	}

	if(time <= 45) {
		$('.clockTime').html(time);
	} else if(time > 45 && time < 60) {
		$('.clockTime').html("0:" + time);
	} else if(time >= 60 && time < 120) {
		if(time - 60 < 10) {
			$('.clockTime').html("1:0" + (time - 60));
		} else {
			$('.clockTime').html("1:" + (time - 60));
		}
	} else if(time >= 120 && time < 180) {
		if(time - 120 < 10) {
			$('.clockTime').html("2:0" + (time - 120));
		} else {
			$('.clockTime').html("2:" + (time - 120));
		}
	} else if(time >= 180 && time < 240) {
		if(time - 180 < 10) {
			$('.clockTime').html("3:0" + (time - 180));
		} else {
			$('.clockTime').html("3:" + (time - 180));
		}
	} else if(time >= 240 && time < 300) {
		if(time - 240 < 10) {
			$('.clockTime').html("4:0" + (time - 240));
		} else {
			$('.clockTime').html("4:" + (time - 240));
		}
	} else if(time >= 300 && time < 360) {
		if(time - 300 < 10) {
			$('.clockTime').html("5:0" + (time - 300));
		} else {
			$('.clockTime').html("5:" + (time - 300));
		}
	}
	$('.clock_red').css("transform","rotate(" + 0.2 + "deg)");
	$('.clock_white').css("transform","rotate(" + 0.2 + "deg)");
	window.GameVariables.FirstTick = true;
}

function resetClock(time) {

	if(window.GameVariables.QuestionLevel < 15) {
		$(".dot_dark_gold").hide();
		$(".clock_gold").hide();
	} else {
		$(".dot_dark").hide();
		$(".dot_dark_gold").hide();
		$(".clock_gold").show();
	}

	if(time <= 45) {
		$('.clockTime').html(time);
	} else if(time > 45 && time < 60) {
		$('.clockTime').html("0:" + time);
	} else if(time >= 60 && time < 120) {
		if(time - 60 < 10) {
			$('.clockTime').html("1:0" + (time - 60));
		} else {
			$('.clockTime').html("1:" + (time - 60));
		}
	} else if(time >= 120 && time < 180) {
		if(time - 120 < 10) {
			$('.clockTime').html("2:0" + (time - 120));
		} else {
			$('.clockTime').html("2:" + (time - 120));
		}
	} else if(time >= 180 && time < 240) {
		if(time - 180 < 10) {
			$('.clockTime').html("3:0" + (time - 180));
		} else {
			$('.clockTime').html("3:" + (time - 180));
		}
	} else if(time >= 240 && time < 300) {
		if(time - 240 < 10) {
			$('.clockTime').html("4:0" + (time - 240));
		} else {
			$('.clockTime').html("4:" + (time - 240));
		}
	} else if(time >= 300 && time < 360) {
		if(time - 300 < 10) {
			$('.clockTime').html("5:0" + (time - 300));
		} else {
			$('.clockTime').html("5:" + (time - 300));
		}
	}
	$('.clock_red').css("transform","rotate(" + 0.2 + "deg)");
	$('.clock_white').css("transform","rotate(" + 0.2 + "deg)");
	window.GameVariables.FirstTick = true;
}

function stopMainClock() {
	window.GameVariables.ClockStopped = true;
}

function resumeMainClock() {
	window.GameVariables.ClockStopped = false;
}

function checkTick() {
	if(window.GameVariables.FirstTick == true) {
		window.GameVariables.FirstTick = false;
		return true;
	} else {
		return false;
	}
}

function showGoldClock() {
	$('.clock_gold').css('z-index', 1004);
	$( ".q_clock" ).fadeOut( "fast", function() {
    	// Animation complete.
  	});
}

function clockTimeOut() {
	console.log("RUNNING");
	if(window.GameVariables.LockedIn == false) {
		window.GameVariables.TimeUp = true;
		window.GameVariables.CannotLockInFinalAnswer = true;
	} else {
		// Do nothing
	}
}

function startRewind(currentTime, timeFinish) {
	window.GameVariables.ClockStopped = true;
	var rotate = window.GameVariables.Rotation;
	var time = timeFinish - currentTime;
	var counter = rotate/time;
	var degreesPerSecond = (rotate * 1)/1850;
	var rate = 1/degreesPerSecond; 

	rewindClock(currentTime, timeFinish, rate, counter);
}

function rewindClock(currentTime, timeFinish, rate) {

	var rotate = window.GameVariables.Rotation;
	var time = timeFinish - currentTime;
	var counter = rotate/time;

	if(window.GameVariables.RewindCounter >= counter) {
		$('.clockTime').html(currentTime);
		window.GameVariables.RewindCounter = 0;
		currentTime++;
	}

	if(window.GameVariables.Rotation >= 0) {
		window.GameVariables.Rotation--;
		window.GameVariables.RewindCounter++;
		if(window.GameVariables.Rotation < 0) {
			if(window.GameVariables.QuestionLevel <= 10) {
				resetClock(30);
			} else if(window.GameVariables.QuestionLevel <= 14) {
				resetClock(45);
			} else if (window.GameVariables.QuestionLevel == 15) {
				resetClock(window.GameVariables.MillionDollarClockTime);
			}
		} else {
			$('.clock_red').css("transform","rotate(" + rotate + "deg)");
			$('.clock_white').css("transform","rotate(" + rotate + "deg)");
		}
		
		console.log("Rate: " + rate);
		if(window.GameVariables.Rotation == 3) {
			startShortPassiveSound("game_resume.mp3");
		}
		if(window.GameVariables.Rotation == 0) {
			$(".dot").fadeOut("slow", function() {
			    // Animation complete
			});
		}
		window.GameVariables.ClockTimeout = setTimeout(function(){ rewindClock(currentTime, timeFinish, rate); }, rate);
	}
}

function animateGlint() {
	$('.question_glint').transition({perspective:0, 'left': '0px'}, 5000, 'linear');
}

function startMainClock(timeConsumed, timeFinish) {
	var mult = 1/(timeFinish/15);
	var timeLeft = timeFinish - timeConsumed;
	window.GameVariables.CurrentTime = timeLeft;
	animateGlint();

	if(window.GameVariables.STQ == true) {
		return;
	}

	if(timeConsumed == 0) {
		window.GameVariables.GlintInterval = 0;
	}

	if(window.GameVariables.ClockStopped == false) {
		if(window.GameVariables.GlintInterval == 7) {
			window.GameVariables.GlintSound.play();
			window.GameVariables.GlintInterval = 0;
		} else {
			window.GameVariables.GlintInterval++;
		}
	}
	
	if(window.GameVariables.LockedIn == false) {
		if(timeConsumed >= (15/15) * timeFinish - 1) {
			$("#dot1").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right1").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (14/15) * timeFinish - 1) {
			$("#dot2").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right2").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (13/15) * timeFinish - 1) {
			$("#dot3").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right3").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (12/15) * timeFinish - 1) {
			$("#dot4").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right4").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (11/15) * timeFinish - 1) {
			$("#dot5").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right5").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (10/15) * timeFinish - 1) {
			$("#dot6").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right6").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (9/15) * timeFinish - 1) {
			$("#dot7").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right7").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (8/15) * timeFinish - 1) {
			$("#dot8").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right8").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (7/15) * timeFinish - 1) {
			$("#dot9").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right9").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (6/15) * timeFinish - 1) {
			$("#dot10").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right10").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (5/15) * timeFinish - 1) {
			$("#dot11").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right11").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (4/15) * timeFinish - 1) {
			$("#dot12").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right12").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (3/15) * timeFinish - 1) {
			$("#dot13").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right13").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (2/15) * timeFinish - 1) {
			$("#dot14").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right14").fadeIn("slow", function() {
			    // Animation complete
			});
		} else if(timeConsumed >= (1/15) * timeFinish - 1) {
			$("#dot15").fadeIn("slow", function() {
			    // Animation complete
			});
			$("#dot_right15").fadeIn("slow", function() {
			    // Animation complete
			});
		}
	}

	if(window.GameVariables.ClockStopped == true) {
		if(window.GameVariables.ClockDone == true) {
			window.GameVariables.MillionDollarClockTime += timeFinish - timeConsumed + 1;
			return;
		}
		window.GameVariables.ClockTimeout = setTimeout(function(){
			window.GameVariables.ClockTimeout = setTimeout(function(){ startMainClock(timeConsumed, timeFinish); }, 496);
		}, 496);
	} else {
		if(timeConsumed == timeFinish) {
			setTimeout(function() {
				clockTimeOut();
			}, 496);
			if(timeFinish > 45) {
				$('.clockTime').html("0:00");
			} else {
				$('.clockTime').html("0");
			}
		}
		else {
			if(timeLeft < 60 && timeFinish <= 45) {
				$('.clockTime').html(timeFinish - timeConsumed);
			} else if(timeLeft < 60) {
				if(timeLeft < 10) {
					$('.clockTime').html("0:0" + timeLeft);
				} else {
					$('.clockTime').html("0:" + timeLeft);
				}
			} else if(timeLeft < 120 && timeLeft >= 60) {
				if(timeLeft - 60 < 10) {
					$('.clockTime').html("1:0" + (timeLeft - 60));
				} else {
					$('.clockTime').html("1:" + (timeLeft - 60));
				}
			} else if(timeLeft < 180 && timeLeft >= 120) {
				if(timeLeft - 120 < 10) {
					$('.clockTime').html("2:0" + (timeLeft - 120));
				} else {
					$('.clockTime').html("2:" + (timeLeft - 120));
				}
			} else if(timeLeft < 240 && timeLeft >= 180) {
				if(timeLeft - 180 < 10) {
					$('.clockTime').html("3:0" + (timeLeft - 180));
				} else {
					$('.clockTime').html("3:" + (timeLeft - 180));
				}
			} else if(timeLeft < 300 && timeLeft >= 240) {
				if(timeLeft - 240 < 10) {
					$('.clockTime').html("4:0" + (timeLeft - 240));
				} else {
					$('.clockTime').html("4:" + (timeLeft - 240));
				}
			} else if(timeLeft < 360 && timeLeft >= 300) {
				if(timeLeft - 300 < 10) {
					$('.clockTime').html("5:0" + (timeLeft - 300));
				} else {
					$('.clockTime').html("5:" + (timeLeft - 300));
				}
			}
		}
		
		var currentRotation = 0;
		
		if(timeConsumed <= timeFinish){
			currentRotation = (timeConsumed * mult * 12);
			window.GameVariables.Rotation = currentRotation;
			if(checkTick() == false && window.GameVariables.LockedIn == false && window.GameVariables.ClockStopped == false) {
				$('.clock_red').css("transform","rotate(" + currentRotation + "deg)");
				$('.clock_white').css("transform","rotate(" + currentRotation + "deg)");	
			} else {
				currentRotation = 0;
			}
			if(timeConsumed < timeFinish) {
				window.GameVariables.ClockTimeout = setTimeout(function(){
					currentRotation += (6 * mult);
					window.GameVariables.Rotation = currentRotation;
					if(checkTick() == false && window.GameVariables.LockedIn == false && window.GameVariables.ClockStopped == false) {
						$('.clock_red').css("transform","rotate(" + currentRotation + "deg)");
						$('.clock_white').css("transform","rotate(" + currentRotation + "deg)");	
					} else {
						currentRotation = 0;
					}
					window.GameVariables.ClockTimeout = setTimeout(function(){ startMainClock(timeConsumed + 1, timeFinish); }, 496);
				}, 496);
			}
		}
		else{
		
		}
	}
}

function playLightsDownSound(){
	if(window.GameVariables.IsFirstQuestionOfGame == true || window.GameVariables.QuestionLevel < 6){
		window.GameVariables.IsFirstQuestionOfGame = false;
		startGeneralSound(window.GameVariables.LightsDownSounds[0]);
		setTimeout(stopLongPassiveSound, 1000);
	}
	else if(window.GameVariables.QuestionLevel == 6 || window.GameVariables.QuestionLevel == 11){
		startLongActiveSound(window.GameVariables.LightsDownSounds[window.GameVariables.QuestionLevel - 1]);
		setTimeout(stopLongPassiveSound, 1000);
	}
	else{
		startLongActiveSound(window.GameVariables.LightsDownSounds[window.GameVariables.QuestionLevel - 1]);
		setTimeout(stopLongPassiveSound, 200);
	}
}

function playBackgroundSound(){
	if(window.GameVariables.QuestionLevel < 6 && window.GameVariables.FirstTierBackgroundSoundPlaying == false){
		window.GameVariables.FirstTierBackgroundSoundPlaying = true;
		startLongPassiveSound(window.GameVariables.RoundBackgroundSounds[window.GameVariables.QuestionLevel - 1]);
	}
	else if(window.GameVariables.QuestionLevel > 5){
		startLongPassiveSound(window.GameVariables.RoundBackgroundSounds[window.GameVariables.QuestionLevel - 1]);
		setTimeout(stopLongActiveSound, 200);
	}
}

function playFinalAnswerSound(){
	if(window.GameVariables.QuestionLevel > 5){
		startLongActiveSound(window.GameVariables.FinalAnswerSounds[window.GameVariables.QuestionLevel - 1]);
		setTimeout(stopLongPassiveSound, 200);
	}
}

function playCorrectAnswerSound(){
	if(window.GameVariables.QuestionLevel < 6){
		startLongActiveSound(window.GameVariables.CorrectAnswerSounds[window.GameVariables.QuestionLevel - 1]);
		
		if(window.GameVariables.QuestionLevel == 5){
			setTimeout(stopLongPassiveSound, 1000);
		}
	}
	else if(window.GameVariables.QuestionLevel == 10 || window.GameVariables.QuestionLevel == 15){
		startLongPassiveSound(window.GameVariables.CorrectAnswerSounds[window.GameVariables.QuestionLevel - 1]);
		setTimeout(stopLongActiveSound, 1000);
	}
	else{
		startLongPassiveSound(window.GameVariables.CorrectAnswerSounds[window.GameVariables.QuestionLevel - 1]);
		setTimeout(stopLongActiveSound, 200);
	}
}

function playWrongAnswerSound(){
	if(window.GameVariables.QuestionLevel < 6){
		startGeneralSound(window.GameVariables.WrongAnswerSounds[window.GameVariables.QuestionLevel - 1]);
		setTimeout(stopLongPassiveSound, 200);
	}
	else if(window.GameVariables.QuestionLevel == 6 || window.GameVariables.QuestionLevel == 11){
		startLongPassiveSound(window.GameVariables.WrongAnswerSounds[window.GameVariables.QuestionLevel - 1]);
		setTimeout(stopLongActiveSound, 1000);
	}
	else{
		startLongPassiveSound(window.GameVariables.WrongAnswerSounds[window.GameVariables.QuestionLevel - 1]);
		setTimeout(stopLongActiveSound, 200);
	}
}