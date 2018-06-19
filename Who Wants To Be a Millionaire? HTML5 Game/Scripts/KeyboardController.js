$(document).ready(function(){

	hideDisclaimerAndBeginController();
	
	$(document).on('keydown',function(e)
	{
		if(e.keyCode == 82) { // 'r'
			if(window.GameVariables.Resuming == 1) {
				window.GameVariables.ResumeNow = 1;
				$(".stageContainerDiv").prepend('<div id="resume">Resume game...</div>');
			}
		}
		else if(e.keyCode == 32) // key "space bar" to hide disclaimer and begin application
		{
			if(window.GameVariables.IsDisclaimerShowing === true){
				hideDisclaimerAndBeginController();
				window.GameVariables.IsDisclaimerShowing = false;
			}
		}
		else if(e.keyCode == 37) // key 'left arrow' for fastest-finger / money tree hide and show
		{
			if(window.GameVariables.GameOver == true) {
				return;
			}

			if(window.GameVariables.QuestionLevel >= 6 && window.GameVariables.STQEnabled == 0 && window.GameVariables.STQDisabled == 0) {
				window.GameVariables.IsSTQLifeLineUsed = false;
				window.GameVariables.STQEnabled = 1;
			}

			if(window.GameVariables.IsLogoShowing == false && window.GameVariables.IsInCommercial == false)
			{
				if(window.GameVariables.IsPlayingFasestFinger == true)
				{
					if(window.GameVariables.FastestFingerSequenceCounter <= 9)
					{
						if(window.GameVariables.FastestFingerSequenceCounter == 0)
						{
							startShortActiveSound("fastest_finger_contestants.mp3");
						}
						// reveal contestants one at a time
						revealFastestFingerContestants();
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 10)
					{
						hideContestantNameAndLocation();
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 11)
					{
						startGeneralSound("fastest_finger_lights_down.mp3");
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 12)
					{
						revealFastestFingerQuestion();
						startShortPassiveSound("fastest_finger_read_question.mp3");
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 13)
					{
						startShortActiveSound("fastest_finger_3_stabs.mp3");
						setTimeout(stopShortPassiveSound, 200);
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 14)
					{
						revealAllAnswersAtOnce();
						startShortPassiveSound("fastest_finger_think.mp3");
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 15)
					{
						hideFastestFingerQuestionAndAnswers();
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 16)
					{
						startShortPassiveSound("fastest_finger_read_answer_order.mp3");
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 17)
					{
						showFastestFingerAnswerTree();
					}
					else if(window.GameVariables.FastestFingerSequenceCounter <= 21)
					{
						var soundToPlay = "";
					
						revealFastestFingerAnswersinCorrectOrder();
						
						if(window.GameVariables.FastestFingerSequenceCounter == 18){
							soundToPlay = "fastest_finger_answer_correct_1.mp3";
						}
						else if(window.GameVariables.FastestFingerSequenceCounter == 19){
							soundToPlay = "fastest_finger_answer_correct_2.mp3";
						}
						else if(window.GameVariables.FastestFingerSequenceCounter == 20){
							soundToPlay = "fastest_finger_answer_correct_3.mp3";
						}
						else if(window.GameVariables.FastestFingerSequenceCounter == 21){
							soundToPlay = "fastest_finger_answer_correct_4.mp3";
						}
						
						setTimeout(function(){
							startGeneralSound(soundToPlay);
						}, 250);
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 22)
					{
						hideFastestFingerAnswerTree();
						showFastestFingerContestants();
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 23)
					{
						revealFastestFingerWinnersAndTimes();
						startGeneralSound("fastest_finger_reveal_times.mp3");
						setTimeout(stopShortPassiveSound, 200);
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 24)
					{
						flashFastestTimeStep1();
						startGeneralSound("fastest_finger_winner.mp3");
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 25)
					{
						showTheFastestFingerWinner();
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 26)
					{
						hideTheFastestFingerWinner();
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 27)
					{
						startShortActiveSound("hello_long.mp3");
					}
					else if(window.GameVariables.FastestFingerSequenceCounter == 28)
					{
						startGeneralSound("lights_down_classic.mp3");
						setTimeout(stopShortActiveSound, 500);
						window.GameVariables.IsPlayingFasestFinger = false;
					}
					
					window.GameVariables.FastestFingerSequenceCounter++;
				}
				else if(window.GameVariables.QuestionInProgress == false && window.GameVariables.IsInCommercial == false){
					// money tree animations
					if(window.GameVariables.MoneyTreeSequenceCounter == 0)
					{
						if(window.GameVariables.QuestionLevel < 8){
							startLongPassiveSound("explain_rules.mp3");
						}
						else{
							startLongPassiveSound("explain_rules_v2.mp3");
						}
					}
					else if(window.GameVariables.MoneyTreeSequenceCounter == 1)
					{
						slideInMoneyTree();
						window.GameVariables.IsMoneyTreeShowing = true;
					} else if(window.GameVariables.MoneyTreeSequenceCounter == 2) {
						startBarSound('highlight_v1.mp3');
						highlightMoneyTree(1, 1600/26);
						//introduceTree();
					}
					else if(window.GameVariables.MoneyTreeSequenceCounter >= 3)
					{
						
						window.GameVariables.IsMoneyTreeShowing == true ? slideOutMoneyTree() : slideInMoneyTree();
						window.GameVariables.STQUnlockedLevel > window.GameVariables.QuestionLevel ? stqLifeLineSlideOut() : null;
						window.GameVariables.IsMoneyTreeShowing = !window.GameVariables.IsMoneyTreeShowing;
						window.GameVariables.IsExplainingRules = false;
					}
					
					window.GameVariables.MoneyTreeSequenceCounter++;
				}
			}
		}
		else if(e.keyCode == 38) // key 'up arrow'
		{
			if(window.GameVariables.IsMoneyTreeShowing == true && window.GameVariables.TreeScaled == false) {
				$('.glow').css('opacity', 1);
				$('.glow').transition({perspective:0, 'top': '175px'}, 790, 'linear');
				setTimeout(function(){
					$( ".glow" ).fadeOut("slow", function() {
		    			$('.glow').css('top', '1925px');
		  			});
		  			$( ".glow" ).fadeIn("slow", function() {
		  				$('.glow').css('top', '950px');
		    			$('.glow').css('opacity', 0);
		  			});
				}, 0);
				scaleTreeLevels(1);
				startScalingSound("categories.mp3");
				window.GameVariables.TreeScaled = true;
			} else if(window.GameVariables.IsMoneyTreeShowing == true && window.GameVariables.TreeScaled == true) {
				unscaleTreeFade();
				window.GameVariables.TreeScaled = false;
			}
			
		}
		else if(e.keyCode == 39) // key 'right arrow' for question navigation
		{
			if(window.GameVariables.GameOver == true) {
				return;
			}

			if(window.GameVariables.TimeUp == true) {
				if(window.GameVariables.QuestionInProgress == true){
					if(window.GameVariables.walkAwaySequenceCounter == 0) {
						window.GameVariables.IsWalkingAway = true;
						if(window.GameVariables.CurrentTargetAnswer == "" || (window.GameVariables.CurrentTargetAnswer != window.GameVariables.CurrentCorrectAnswer)){
							revealNormalToCorrectAnswerStep1(window.GameVariables.CurrentCorrectAnswer.toUpperCase());
						}
						else if(window.GameVariables.CurrentTargetAnswer == window.GameVariables.CurrentCorrectAnswer){
							showFinalToCorrectAnswerStep1(window.GameVariables.CurrentCorrectAnswer.toUpperCase());
						}
						
						if(window.GameVariables.IsLevelStrapShowing == true){
							hideLevelStrap();
							window.GameVariables.IsLevelStrapShowing = false;
						}
						
						hideLifeLineCentered();
					}
					else if(window.GameVariables.walkAwaySequenceCounter == 1){
						hideQuestionAndAnswerStraps();
						window.GameVariables.QuestionInProgress = false;
						window.GameVariables.walkAwaySequenceCounter = -1;
					}
					
					window.GameVariables.walkAwaySequenceCounter++;
				}
			}
			if(window.GameVariables.QuestionLevel == 15) {
				showGoldClock();
			}

			if(window.GameVariables.LLComplete == true) {
				startShortPassiveSound("resume_clock.mp3");
				setTimeout(function(){
					resumeMainClock();
				}, 100);
				if(window.GameVariables.QuestionLevel <=5) {
					window.GameVariables.LongPassiveSound.play();
					window.GameVariables.ShortActiveSound.play();
				} else {
					window.GameVariables.LongPassiveSound.play();
				}
				window.GameVariables.LLComplete = false;

				if(window.GameVariables.IsDoubleDipActive == true) {
					setTimeout(stopLifelineActiveSound, 5);
					window.GameVariables.DDUsed = true;
				}
				window.GameVariables.LLStarted = false;
			}

			if(window.GameVariables.QuestionLevel < 6 && window.GameVariables.STQDisabledStart == 0) {
				window.GameVariables.IsSTQLifeLineUsed = true;
				window.GameVariables.STQDisabledStart = 1;
			}

			if(window.GameVariables.QuestionLevel >= 6 && window.GameVariables.STQEnabled == 0 && window.GameVariables.STQDisabled == 0) {
				window.GameVariables.IsSTQLifeLineUsed = false;
				window.GameVariables.STQEnabled = 1;
				window.GameVariables.stqLifeLineSequenceCounter = 0;
			}

			if(window.GameVariables.IsPlayingFasestFinger == false && window.GameVariables.IsMoneyTreeShowing == false && window.GameVariables.IsExplainingRules == false && window.GameVariables.IsInCommercial == false && window.GameVariables.IsNameStrapShowing == false){
				if(window.GameVariables.QuestionSequenceCounter == -1){
					return;
				}			
				else if(window.GameVariables.QuestionSequenceCounter == 0) {
					if(window.GameVariables.FirstQ == true && window.GameVariables.QuestionLevel == 1) {
						startLongPassiveSound("lights_down_classic.mp3");
						window.GameVariables.FirstQ = false;
					} else {
						playLightsDownSound();
					}	
				}
				else if(window.GameVariables.QuestionSequenceCounter == 1) {
					window.GameVariables.LockedIn = false;
					if(window.GameVariables.QuestionLevel <= 5) {
						setClock(15);
					} else if(window.GameVariables.QuestionLevel <= 10) {
						setClock(30);
					} else if(window.GameVariables.QuestionLevel <= 14){
						setClock(45);
					} else {
						setClock(window.GameVariables.MillionDollarClockTime);
					}
					setQuestion(false);
					window.GameVariables.QuestionInProgress = true;
					// setup question and answers

					$(".questionTd").hide();

					revealQuestionAndAnswerStraps();
					setTimeout(function() {
						if(window.GameVariables.QuestionLevel == 1 || window.GameVariables.QuestionLevel == 6 || window.GameVariables.QuestionLevel == 11 || window.GameVariables.QuestionLevel == 15) {
							if(window.GameVariables.QuestionLevel == 15) {
								animateGoldDots(0);
							} else {
								animateDots(0);
							}
						} else {
							animateDots(1);
						}
					}, 50);
				}
				else if(window.GameVariables.QuestionSequenceCounter == 2) {
					revealAllAnswersAtOnce();
					if(window.GameVariables.QuestionLevel <= 5) {
						beginClock(15);
						startShortActiveSound("q1_to_q5_clock.mp3");
					} else if(window.GameVariables.QuestionLevel <= 10) {
						beginClock(30);
						stopGeneralSound();
						playBackgroundSound();
					} else if(window.GameVariables.QuestionLevel <= 14){
						beginClock(45);
						stopGeneralSound();
						playBackgroundSound();
					} else {
						beginClock(window.GameVariables.MillionDollarClockTime);
						stopGeneralSound();
						playBackgroundSound();
					}
					window.GameVariables.QuestionSequenceCounter = -1;
					return;
				}
				else if(window.GameVariables.QuestionSequenceCounter == 6){
					if(window.GameVariables.CurrentCorrectAnswer == window.GameVariables.CurrentTargetAnswer) {
						if(window.GameVariables.IsSTQLifeLineUsed == true) {
							hideLifeLineCentered();
						}
						if(window.GameVariables.IsDoubleDipActive == true){
							setTimeout(stopLifelinePassiveSound, 250);
							hideLifeLineCentered();
							window.GameVariables.IsDoubleDipActive = false;
							window.GameVariables.ClockDone = true;
						}
						
						window.GameVariables.CannotWalkAway = false;
						showFinalToCorrectAnswerStep1(window.GameVariables.CurrentTargetAnswer.toUpperCase());
						playCorrectAnswerSound();

						if(window.GameVariables.ataLifeLineSequenceCounter < 0) {
							slideOutATAGraph();
						}
					}
					else if(window.GameVariables.CurrentTargetAnswer != window.GameVariables.CurrentCorrectAnswer) {
						if(window.GameVariables.IsDoubleDipActive == true && window.GameVariables.IsFirstDoubleDipAnswerWrong == false) {
							window.GameVariables.IsFirstDoubleDipAnswerWrong = true;
							window.GameVariables.LockedIn = false;
							window.GameVariables.LLStarted = true;
							startLifelineActiveSound("double_dip_first_wrong.mp3");
							setTimeout(stopLifelinePassiveSound, 250);
							setTimeout(function(){
								ddShadowFirstWrongAnswer(window.GameVariables.CurrentTargetAnswer.toUpperCase());
								window.GameVariables.CurrentTargetAnswer = "";
								window.GameVariables.QuestionSequenceCounter = -1;
							}, 50);
							window.GameVariables.LLComplete = true;
						}
						else{
							revealNormalToCorrectAnswerStep1(window.GameVariables.CurrentCorrectAnswer.toUpperCase());
							window.GameVariables.QuestionSequenceCounter = 8;
							playWrongAnswerSound();

							if(window.GameVariables.ataLifeLineSequenceCounter < 0) {
								slideOutATAGraph();
							}
							
							if(window.GameVariables.IsDoubleDipActive == true){
								window.GameVariables.IsDoubleDipActive = false;
								hideLifeLineCentered();
								setTimeout(stopLifelinePassiveSound, 250);
							}
						}
					}
					
					if(window.GameVariables.IsLevelStrapShowing == true){
						hideLevelStrap();
						window.GameVariables.IsLevelStrapShowing = false;
					}
				}
				else if(window.GameVariables.QuestionSequenceCounter == 7){
					hideQuestionAndAnswerStraps();
					resetAnswerStraps();
					
					if(window.GameVariables.QuestionLevel == 15){
						showMillionaireTitleStrap();
					}
					else{
						showAmountWon();
					}
				}
				else if(window.GameVariables.QuestionSequenceCounter == 8){
					if(window.GameVariables.QuestionLevel == 15){
						hideMillionaireTitleStrap();
						window.GameVariables.IsWalkingAway = true;
					}
					else {
						hideAmountWon();
					}
					
					window.GameVariables.QuestionLevel++;
					setLevelOnMoneyTree(window.GameVariables.QuestionLevel);
					
					window.GameVariables.QuestionInProgress = false;
					
					if(window.GameVariables.QuestionLevel < 6){
						window.GameVariables.QuestionSequenceCounter = 0;
					}
					else{
						window.GameVariables.QuestionSequenceCounter = -1;
					}
					/* END OF CORRECT ANSWER GIVEN CONTROL */
				}
				else if(window.GameVariables.QuestionSequenceCounter == 9){
					/* START OF WRONG ANSWER GIVEN CONTROL */
					reduceAmountWon();
					hideQuestionAndAnswerStraps();
					window.GameVariables.QuestionInProgress = false;
					window.GameVariables.IsWalkingAway = true;
					window.GameVariables.GameOver = true;
					window.GameVariables.QuestionSequenceCounter = 0;
				}
				
				window.GameVariables.QuestionSequenceCounter++;
			}
		}
		else if(e.keyCode == 40) // key 'down arrow' for total prize money
		{
			if(window.GameVariables.IsMoneyTreeShowing == true) {
				startBarSound('highlight_v1.mp3');
				highlightMoneyTree(1, 1600/26);
			}
			if(window.GameVariables.QuestionInProgress == false && window.GameVariables.IsMoneyTreeShowing == false  && window.GameVariables.IsNameStrapShowing == false){
				if(window.GameVariables.QuestionLevel - 1 == 15){
					window.GameVariables.IsTotalPrizeMoneyShowing == true ? hideMillionaireTitleStrap() : showMillionaireTitleStrap();
				}
				else{
					window.GameVariables.IsTotalPrizeMoneyShowing == true ? hideTotalPrizeMoneyStrap() : showTotalPrizeMoneyStrap();
				}
				
				window.GameVariables.IsTotalPrizeMoneyShowing = !window.GameVariables.IsTotalPrizeMoneyShowing;
				
				if(window.GameVariables.IsWalkingAway == true){
					window.GameVariables.QuestionLevel > 10 == true ? startGeneralSound("walk_away_large.mp3") : startGeneralSound("walk_away_small.mp3");
					window.GameVariables.IsWalkingAway = false;
				}
				
			}
		}
		else if(e.keyCode == 49) // key '1' to lock in 'A' as final
		{
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $100"));
				window.GameVariables.StartingQuestionLevel = 1;
				window.GameVariables.ResumeNow = 0;
				init();
			} else if(window.GameVariables.LLStarted == true) {
				return;
			}
			else if(window.GameVariables.QuestionInProgress == true && window.GameVariables.CannotLockInFinalAnswer == false && window.GameVariables.AnswerAIsOut == false && window.GameVariables.CurrentTargetAnswer == "" && window.GameVariables.QuestionSequenceCounter == -1){
				lockInFinalAnswer('A');
				window.GameVariables.LockedIn = true;
				window.GameVariables.ClockStopped = true;
				if(window.GameVariables.IsDoubleDipActive == false || window.GameVariables.DDUsed == true) {
					window.GameVariables.ClockDone = true;
				}
				window.GameVariables.CurrentTargetAnswer = "a";

				if(window.GameVariables.IsDoubleDipActive == true && window.GameVariables.DDUsed == false) {
					if(window.GameVariables.IsFirstDoubleDipAnswerWrong === false){
						startLifelinePassiveSound("double_dip_first_final.mp3");
					}
					else{
						startLifelinePassiveSound("double_dip_second_final.mp3");
					}
					
					setTimeout(stopLifelineActiveSound, 250);
				}
				else if(window.GameVariables.QuestionLevel <= 5) {
					if(window.GameVariables.QuestionLevel == 1) {
						startGeneralSound("q1_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 2) {
						startGeneralSound("q2_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 3) {
						startGeneralSound("q3_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 4) {
						startGeneralSound("q4_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 5) {
						startGeneralSound("q5_final.mp3");
					}
					stopShortActiveSound();
				} else if(window.GameVariables.IsWalkingAway == false){
					playFinalAnswerSound();
				}
			}
		}
		else if(e.keyCode == 50) // key '2' to lock in 'B' as final
		{	
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $200"));
				window.GameVariables.StartingQuestionLevel = 2;
				window.GameVariables.ResumeNow = 0;
				init();
			} else if(window.GameVariables.LLStarted == true) {
				return;
			}
			else if(window.GameVariables.QuestionInProgress == true && window.GameVariables.CannotLockInFinalAnswer == false && window.GameVariables.AnswerBIsOut == false && window.GameVariables.CurrentTargetAnswer == "" && window.GameVariables.QuestionSequenceCounter == -1){
				lockInFinalAnswer('B');
				window.GameVariables.LockedIn = true;
				window.GameVariables.ClockStopped = true;
				if(window.GameVariables.IsDoubleDipActive == false || window.GameVariables.DDUsed == true) {
					window.GameVariables.ClockDone = true;
				}
				window.GameVariables.CurrentTargetAnswer = "b";

				if(window.GameVariables.IsDoubleDipActive == true && window.GameVariables.DDUsed == false){
					if(window.GameVariables.IsFirstDoubleDipAnswerWrong === false){
						startLifelinePassiveSound("double_dip_first_final.mp3");
					}
					else{
						startLifelinePassiveSound("double_dip_second_final.mp3");
					}
					
					setTimeout(stopLifelineActiveSound, 250);
				}
				else if(window.GameVariables.QuestionLevel <= 5) {
					if(window.GameVariables.QuestionLevel == 1) {
						startGeneralSound("q1_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 2) {
						startGeneralSound("q2_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 3) {
						startGeneralSound("q3_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 4) {
						startGeneralSound("q4_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 5) {
						startGeneralSound("q5_final.mp3");
					}
					stopShortActiveSound();
				} else if(window.GameVariables.IsWalkingAway == false){
					playFinalAnswerSound();
				}
			}
		}
		else if(e.keyCode == 51) // key '3' to lock in 'C' as final
		{
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $300"));
				window.GameVariables.StartingQuestionLevel = 3;
				window.GameVariables.ResumeNow = 0;
				init();
			} else if(window.GameVariables.LLStarted == true) {
				return;
			}
			else if(window.GameVariables.QuestionInProgress == true && window.GameVariables.CannotLockInFinalAnswer == false && window.GameVariables.AnswerCIsOut == false && window.GameVariables.CurrentTargetAnswer == "" && window.GameVariables.QuestionSequenceCounter == -1){
				lockInFinalAnswer('C');
				window.GameVariables.LockedIn = true;
				window.GameVariables.ClockStopped = true;
				if(window.GameVariables.IsDoubleDipActive == false || window.GameVariables.DDUsed == true) {
					window.GameVariables.ClockDone = true;
				}
				window.GameVariables.CurrentTargetAnswer = "c";

				if(window.GameVariables.IsDoubleDipActive == true && window.GameVariables.DDUsed == false){
					if(window.GameVariables.IsFirstDoubleDipAnswerWrong === false){
						startLifelinePassiveSound("double_dip_first_final.mp3");
					}
					else{
						startLifelinePassiveSound("double_dip_second_final.mp3");
					}
					
					setTimeout(stopLifelineActiveSound, 250);
				}
				else if(window.GameVariables.QuestionLevel <= 5) {
					if(window.GameVariables.QuestionLevel == 1) {
						startGeneralSound("q1_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 2) {
						startGeneralSound("q2_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 3) {
						startGeneralSound("q3_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 4) {
						startGeneralSound("q4_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 5) {
						startGeneralSound("q5_final.mp3");
					}
					stopShortActiveSound();
				} else if(window.GameVariables.IsWalkingAway == false){
					playFinalAnswerSound();
				}
			}
		}
		else if(e.keyCode == 52) // key '4' to lock in 'D' as final
		{
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $500"));
				window.GameVariables.StartingQuestionLevel = 4;
				window.GameVariables.ResumeNow = 0;
				init();
			} else if(window.GameVariables.LLStarted == true) {
				return;
			}
			else if(window.GameVariables.QuestionInProgress == true && window.GameVariables.CannotLockInFinalAnswer == false && window.GameVariables.AnswerDIsOut == false && window.GameVariables.CurrentTargetAnswer == "" && window.GameVariables.QuestionSequenceCounter == -1){
				lockInFinalAnswer('D');
				window.GameVariables.LockedIn = true;
				window.GameVariables.ClockStopped = true;
				if(window.GameVariables.IsDoubleDipActive == false || window.GameVariables.DDUsed == true) {
					window.GameVariables.ClockDone = true;
				}
				window.GameVariables.CurrentTargetAnswer = "d";

				if(window.GameVariables.IsDoubleDipActive == true && window.GameVariables.DDUsed == false){
					if(window.GameVariables.IsFirstDoubleDipAnswerWrong === false){
						startLifelinePassiveSound("double_dip_first_final.mp3");
					}
					else{
						startLifelinePassiveSound("double_dip_second_final.mp3");
					}
					
					setTimeout(stopLifelineActiveSound, 250);
				}
				else if(window.GameVariables.QuestionLevel <= 5) {
					if(window.GameVariables.QuestionLevel == 1) {
						startGeneralSound("q1_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 2) {
						startGeneralSound("q2_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 3) {
						startGeneralSound("q3_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 4) {
						startGeneralSound("q4_final.mp3");
					}
					if(window.GameVariables.QuestionLevel == 5) {
						startGeneralSound("q5_final.mp3");
					}
					stopShortActiveSound();
				} else if(window.GameVariables.IsWalkingAway == false){
					playFinalAnswerSound();
				}
			}
		} else if(e.keyCode == 53) {
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $1,000"));
				window.GameVariables.StartingQuestionLevel = 5;
				window.GameVariables.ResumeNow = 0;
				init();
			}
		} else if(e.keyCode == 54) {
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $2,000"));
				window.GameVariables.StartingQuestionLevel = 6;
				window.GameVariables.ResumeNow = 0;
				init();
			}
		} else if(e.keyCode == 55) {
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $4,000"));
				window.GameVariables.StartingQuestionLevel = 7;
				window.GameVariables.ResumeNow = 0;
				init();
			}
		} else if(e.keyCode == 56) {
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $8,000"));
				window.GameVariables.StartingQuestionLevel = 8;
				window.GameVariables.ResumeNow = 0;
				init();
			}
		} else if(e.keyCode == 57) {
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $16,000"));
				window.GameVariables.StartingQuestionLevel = 9;
				window.GameVariables.ResumeNow = 0;
				init();
			}
		} else if(e.keyCode == 48) {
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $32,000"));
				window.GameVariables.StartingQuestionLevel = 10;
				window.GameVariables.ResumeNow = 0;
				init();
			}
		} else if(e.keyCode == 112) {
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $64,000"));
				window.GameVariables.StartingQuestionLevel = 11;
				window.GameVariables.ResumeNow = 0;
				init();
			}
		} else if(e.keyCode == 113) {
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $125,000"));
				window.GameVariables.StartingQuestionLevel = 12;
				window.GameVariables.ResumeNow = 0;
				init();
			}
		} else if(e.keyCode == 114) {
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $250,000"));
				window.GameVariables.StartingQuestionLevel = 13;
				window.GameVariables.ResumeNow = 0;
				init();
			}
		} else if(e.keyCode == 115) {
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $500,000"));
				window.GameVariables.StartingQuestionLevel = 14;
				window.GameVariables.ResumeNow = 0;
				init();
			}
		}
		else if(e.keyCode == 65) // key 'a' for ask the audience animations
		{
			if(window.GameVariables.Resuming == 1 && window.GameVariables.DisableNow == 1) {
				ataLifeLineDisable();
				$("#disable").append(document.createTextNode(" Ask the Audience "));
			}
			if(window.GameVariables.IsATALifeLineUsed == true) {
				console.log('ATA used');
			}
			else if(window.GameVariables.IsMoneyTreeShowing == true){
				if(window.GameVariables.IsATALifeLineUsed == false){
					ataPulseLifeLine();
					startPulseSound("lifeline_3_on.mp3");
				}
			}
			else if(window.GameVariables.QuestionInProgress == true){
				if(window.GameVariables.ataLifeLineSequenceCounter < 0) {
					return;
				}
				if(window.GameVariables.ataLifeLineSequenceCounter == 0){
					window.GameVariables.LLStarted = true;
					// play ata start sound
					window.GameVariables.CannotLockInFinalAnswer = true;
					startGeneralSound("stop_clock.mp3");
					stopMainClock();
					startLifelineActiveSound("ata_start.mp3");

					$(".ataGraph").fadeOut("fast", function() {
	    				// Animation complete
					});
					
					if(window.GameVariables.QuestionLevel <= 5) {
						window.GameVariables.LongPassiveSound.pause();
						window.GameVariables.ShortActiveSound.pause();
					} else {
						window.GameVariables.LongPassiveSound.pause();
					}
					window.GameVariables.FirstTierBackgroundSoundPlaying = false;
				}
				else if(window.GameVariables.ataLifeLineSequenceCounter == 1){
					slideInATAGraph(0);
				}
				else if(window.GameVariables.ataLifeLineSequenceCounter == 2){
					// play ata vote sound
					startATASound("ata_vote.mp3");
					setTimeout(stopLifelineActiveSound, 200);
				}
				else if(window.GameVariables.ataLifeLineSequenceCounter == 3){
					// play ata stop vote sound
					revealGraphPercentages();
					startLifelineActiveSound("ata_end.mp3");
					setTimeout(stopATASound(1), 1000);
					startShortPassiveSound("game_resume.mp3");
					window.GameVariables.CannotLockInFinalAnswer = false;
					window.GameVariables.ataLifeLineSequenceCounter = -2;
				}
				
				window.GameVariables.ataLifeLineSequenceCounter++;
				window.GameVariables.LLComplete = true;
			}
		}
		else if(e.keyCode == 67) // key 'c' for commerical in/out
		{
			if(window.GameVariables.QuestionInProgress == false && window.GameVariables.IsMoneyTreeShowing == false)
			{
				if(window.GameVariables.IsInCommercial == true){
					hideMillionaireLogo();
					if(window.GameVariables.IsFirstQuestionOfGame == false){
						startShortActiveSound("commercial_out.mp3");
					}
					else{
						window.GameVariables.Resuming = 0;
						window.GameVariables.ResumeNow = 0;
						window.GameVariables.DisableLL = 0;
						window.GameVariables.DisableNow = 0;
						window.GameVariables.IsExplainingRules = true;

						$("#disable").fadeOut("slow", function() {
    						// Animation complete.
  						});
						$("#resume").fadeOut("slow", function() {
    						// Animation complete.
  						});

						startShortActiveSound("hello_long.mp3");
						setTimeout(function(){
							startShortPassiveSound("lights_down_classic2.mp3");
							setTimeout(stopShortActiveSound, 1000);
						}, 9000);
					}
				}
				else{
					showMillionaireLogo();
					window.GameVariables.FirstTierBackgroundSoundPlaying = false;
					window.GameVariables.QuestionSequenceCounter = 0;
					setTimeout(stopLongPassiveSound, 250);
					startShortActiveSound("commercial_in.mp3");
				}
				
				window.GameVariables.IsInCommercial = !window.GameVariables.IsInCommercial;
			}
		}
		else if(e.keyCode == 68) // key 'd' for double dip
		{
			if(window.GameVariables.Resuming == 1 && window.GameVariables.DisableNow == 1) {
				ddLifeLineDisable();
				window.GameVariables.DDDisabled = 1;
				$("#disable").append(document.createTextNode(" Double Dip "));
			}
			if(window.GameVariables.IsDDLifeLineUsed == true) {
				console.log('DD used');
				if(window.GameVariables.IsExplainingRules == true && window.GameVariables.DDDisabled == 0) {
					ddPulseLifeLine();
					startGeneralSound("lifeline_2_on.mp3");
				}
			}
			else if(window.GameVariables.IsMoneyTreeShowing == true){
				if(window.GameVariables.IsDDLifeLineUsed == false && window.GameVariables.DDDisabled == 0){
					ddPulseLifeLine();
					startGeneralSound("lifeline_2_on.mp3");
				}
			}
			else {
				if(window.GameVariables.QuestionInProgress == true){
					startGeneralSound("stop_clock.mp3");
					stopMainClock();
					startLifelineActiveSound("double_dip_start.mp3");
					ddLifeLineDisable();
					if(window.GameVariables.QuestionLevel <= 5) {
						window.GameVariables.LongPassiveSound.pause();
						window.GameVariables.ShortActiveSound.pause();
					} else {
						window.GameVariables.LongPassiveSound.pause();
					}
					showLifeLineCentered("#ddLifeLineCenterImg");
						
					window.GameVariables.IsDoubleDipActive = true;
					window.GameVariables.CannotWalkAway = true;
				}
			}
		}
		else if(e.keyCode == 69) // key 'e' for explain rules sound
		{
			if(window.GameVariables.QuestionInProgress == false && window.GameVariables.IsInCommercial == false)
			{
				if(window.GameVariables.QuestionLevel < 8){
					startLongPassiveSound("explain_rules.mp3");
				}
				else{
					startLongPassiveSound("explain_rules_v2.mp3");
				}
			}
		}
		else if(e.keyCode == 71) // key 'g' for game over
		{
			if(window.GameVariables.QuestionInProgress == false){
				startGeneralSound("game_over.mp3");
				setTimeout(stopLongPassiveSound, 500);
				setTimeout(stopLifelineActiveSound, 500);
				setTimeout(function(){
					startShortPassiveSound("close_theme.mp3");
				}, 2000);
				window.GameVariables.QuestionSequenceCounter = 0;
				window.GameVariables.MoneyTreeSequenceCounter = 0;
				window.GameVariables.IsFirstQuestionOfGame = true;
				
				$('.moneyTreeDiv').transition({perspective:0, opacity:1, right:"-760px"}, 1)
				
				/*window.GameVariables.QuestionInProgress = false;
				hideLifeLineCentered();
				setTimeout(function(){
					hideQuestionAndAnswerStraps();
					resetAnswerStraps();
				}, 1500);
				
				if(window.GameVariables.stqLifeLineSequenceCounter != 0){
					window.GameVariables.IsSTQLifeLineActiveAtStart = true;
					window.GameVariables.CannotLockInFinalAnswer = false;
				}*/
			}
		}
		else if(e.keyCode == 76) // key 'l' for life line strap animations
		{
			if(window.GameVariables.DisableLL == 1) {
				window.GameVariables.DisableNow = 1;
				$(".stageContainerDiv").prepend('<div id="disable">Disable lifelines...</div>');
				$("#disable").append('<BR>');
			}
			else if(window.GameVariables.QuestionInProgress == true){
				window.GameVariables.IsLifeLineStrapShowing == true ? slideLifeLineStrapOut() : slideLifeLineStrapIn();
				window.GameVariables.IsLifeLineStrapShowing = !window.GameVariables.IsLifeLineStrapShowing
			}
		}
		else if(e.keyCode == 77) // key 'm' for money level amount strap animations
		{
			if(window.GameVariables.ResumeNow == 1) {
				$("#resume").append("<BR>");
				$("#resume").append(document.createTextNode(" Question level: $1 MILLION"));
				window.GameVariables.StartingQuestionLevel = 15;
				window.GameVariables.ResumeNow = 0;
				init();
			}
			else if(window.GameVariables.QuestionInProgress == true){
				window.GameVariables.IsLevelStrapShowing == true ? hideLevelStrap() : showLevelStrap();
				window.GameVariables.IsLevelStrapShowing = !window.GameVariables.IsLevelStrapShowing;
			}
		}
		else if(e.keyCode == 78) // key 'n' for name strap animations
		{
			if(window.GameVariables.QuestionInProgress == false && window.GameVariables.IsInCommercial == false && window.GameVariables.IsMoneyTreeShowing == false && window.GameVariables.IsTotalPrizeMoneyShowing == false){
				window.GameVariables.IsNameStrapShowing == true ? hideNameStrap() : showNameStrap();
				window.GameVariables.IsNameStrapShowing = !window.GameVariables.IsNameStrapShowing;
			}
		}
		else if(e.keyCode == 80) // key 'p' for phone a friend animations
		{
			if(window.GameVariables.Resuming == 1 && window.GameVariables.DisableNow == 1) {
				pafLifeLineDisable();
				$("#disable").append(document.createTextNode(" Phone a Friend "));
			}
			if(window.GameVariables.IsPAFLifeLineUsed == true) {
				console.log('PAF used');
			}
			else if(window.GameVariables.IsMoneyTreeShowing == true){
				if(window.GameVariables.IsPAFLifeLineUsed == false){
					pafPulseLifeLine();
					startPulseSound("lifeline_1_on.mp3");
				}
			}
			else if(window.GameVariables.QuestionInProgress == true){
				if(window.GameVariables.pafLifeLineSequenceCounter == 0){
					window.GameVariables.LLStarted = true;
					startGeneralSound("stop_clock.mp3");
					stopMainClock();
					startLifelineActiveSound("paf_start.mp3");
					if(window.GameVariables.QuestionLevel <= 5) {
						window.GameVariables.LongPassiveSound.pause();
						window.GameVariables.ShortActiveSound.pause();
					} else {
						window.GameVariables.LongPassiveSound.pause();
					}
					window.GameVariables.CannotLockInFinalAnswer = true;
					window.GameVariables.FirstTierBackgroundSoundPlaying = false;
				}
				else if(window.GameVariables.pafLifeLineSequenceCounter == 1){
					hideQuestionAndAnswerStraps();
					startGeneralSound("paf_show_3.mp3");

					if(window.GameVariables.ShowLifeLineCenteredAnimation == true){
						hideJustLifeLineCenteredContainer();
					}
				}
				else if(window.GameVariables.pafLifeLineSequenceCounter == 2){
					revealQuestionAndAnswerStraps();
					pafRevealClock();
					
					if(window.GameVariables.ShowLifeLineCenteredAnimation == true){
						showJustLifeLineCenteredContainer();
					}
				}
				else if(window.GameVariables.pafLifeLineSequenceCounter == 3){
					pafEndClockEarly();
					setTimeout(stopLifelinePassiveSound, 200);
				}
				
				window.GameVariables.pafLifeLineSequenceCounter++;
				window.GameVariables.LLComplete = true;
			}
		}
		else if(e.keyCode == 81) // key 'q' for game resume
		{
			if(window.GameVariables.QuestionInProgress == false){
				startLongPassiveSound("game_resume.mp3");
			}
		}
		else if(e.keyCode == 13) {
			window.GameVariables.ClockStopped = true;
		}
		else if(e.keyCode == 83) // key 's' for switch the question animations
		{
			if(window.GameVariables.Resuming == 1 && window.GameVariables.DisableNow == 1) {
				stqLifeLineDisable();
				window.GameVariables.IsSTQLifeLineUsed = true;
				$("#disable").append(document.createTextNode(" Switch the Question "));
			}
			if(window.GameVariables.IsSTQLifeLineUsed == true) {
				console.log('STQ used');
				if(window.GameVariables.IsExplainingRules == true && window.GameVariables.STQDisabled == 0) {
					stqPulseLifeLine();
					startPulseSound("lifeline_4_on.mp3");
				}
			}
			else if(window.GameVariables.IsMoneyTreeShowing == true){
				if(window.GameVariables.QuestionLevel <= window.GameVariables.STQUnlockedLevel) {
					if(window.GameVariables.QuestionLevel == 6) {
						if(window.GameVariables.LifeLineAnimationCounter == 0){
							stqLifeLineSlideIn();
						} else if(window.GameVariables.LifeLineAnimationCounter == 1) {
							stqPulseLifeLine();
							startPulseSound("lifeline_4_on.mp3");
							window.GameVariables.LifeLineAnimationCounter = -1;
						}
					} else if(window.GameVariables.LifeLineAnimationCounter == 0){
						setLevelOnMoneyTree(window.GameVariables.STQUnlockedLevel - 1);
					}
					else if(window.GameVariables.LifeLineAnimationCounter == 1){
						stqLifeLineSlideIn();
					}
					else if(window.GameVariables.LifeLineAnimationCounter == 2){
						stqPulseLifeLine();
						startPulseSound("lifeline_4_on.mp3");
						window.GameVariables.LifeLineAnimationCounter = -1;
						setTimeout(function(){
							$( ".bar" ).fadeOut("slow", function() {
					    		// Animation complete.
					  		});
						}, 2000);
					}
					
					window.GameVariables.LifeLineAnimationCounter++;
				}
				else if(window.GameVariables.IsSTQLifeLineUsed == false) {
					stqPulseLifeLine();
					startPulseSound("lifeline_4_on.mp3");
				}
			}
			else if(window.GameVariables.QuestionInProgress == true){
				if(window.GameVariables.stqLifeLineSequenceCounter == 0){
					window.GameVariables.LLStarted = true;
					showLifeLineCentered('#stqLifeLineCenterImg');
					startLifelineActiveSound("stq_start.mp3");
					setTimeout(stopLongPassiveSound, 50);

					window.GameVariables.STQ = true;
					startGeneralSound("stop_clock.mp3");
					stopMainClock();

					window.GameVariables.CannotLockInFinalAnswer = true;
					window.GameVariables.FirstTierBackgroundSoundPlaying = false;
				}
				else if(window.GameVariables.stqLifeLineSequenceCounter == 1){
					revealNormalToCorrectAnswerStep1(window.GameVariables.CurrentCorrectAnswer.toUpperCase());
					startGeneralSound("stq_reveal_correct_answer.mp3");
				}
				else if(window.GameVariables.stqLifeLineSequenceCounter == 2){
					switchOutToNewQuestion();
					startLifelinePassiveSound("stq_new_question_flip.mp3");

					setTimeout(function() {
						stopLifelineActiveSound();

						if(window.GameVariables.QuestionLevel <= 10) {
							startRewind(window.GameVariables.CurrentTime, 30);
							startShortActiveSound("rewind_clock.mp3");
						}
						else if(window.GameVariables.QuestionLevel <= 14) {
							startRewind(window.GameVariables.CurrentTime, 45);
							startShortActiveSound("rewind_clock.mp3");
						}
						else if(window.GameVariables.QuestionLevel == 15) {
							startRewind(window.GameVariables.CurrentTime, window.GameVariables.MillionDollarClockTime);
							startShortActiveSound("rewind_clock.mp3");
						}
					}, 600);
				}
				else if(window.GameVariables.stqLifeLineSequenceCounter <= 3){
					revealAllAnswersAtOnce();
					fadeOutShortPassiveSound(1);
					playBackgroundSound();
					window.GameVariables.STQ = false;
					window.GameVariables.LLStarted = false;

					if(window.GameVariables.QuestionLevel <= 10) {
						beginClock(30);
					} else if(window.GameVariables.QuestionLevel <= 14) {
						beginClock(45);
					} else if (window.GameVariables.QuestionLevel == 15) {
						beginClock(window.GameVariables.MillionDollarClockTime);
					}
					
					if(window.GameVariables.stqLifeLineSequenceCounter == 3){
						window.GameVariables.CannotLockInFinalAnswer = false;
						window.GameVariables.stqLifeLineSequenceCounter = -1;
						window.GameVariables.IsSTQLifeLineUsed = true;
					}
				}
				
				window.GameVariables.stqLifeLineSequenceCounter++;
			}			
		}
		else if(e.keyCode == 85) // key 'u' for undo final answer / restart question
		{
			if(window.GameVariables.QuestionSequenceCounter == 6 || window.GameVariables.IsWalkingAway == true){
				undoFinaledAnswer();
				setTimeout(stopLongActiveSound, 200);
				window.GameVariables.IsWalkingAway = false;
				window.GameVariables.walkAwaySequenceCounter = 0;
				window.GameVariables.QuestionSequenceCounter = -1;
			}
			
			if(window.GameVariables.QuestionInProgress){
				playBackgroundSound();
			}
		}
		else if(e.keyCode == 87) // key 'w' for walk away
		{
			if(window.GameVariables.CannotWalkAway == true) {
				return;
			}
			if(window.GameVariables.QuestionInProgress == true){
				if(window.GameVariables.walkAwaySequenceCounter == 0){
					startGeneralSound("stop_clock.mp3");
					stopMainClock();
					window.GameVariables.QuestionLevel > 10 == true ? startShortActiveSound("quit_large.mp3") : startShortActiveSound("quit_small.mp3");
					setTimeout(stopLongPassiveSound, 200);
					window.GameVariables.IsWalkingAway = true;
				}
				else if(window.GameVariables.walkAwaySequenceCounter == 1){
					if(window.GameVariables.CurrentTargetAnswer == "" || (window.GameVariables.CurrentTargetAnswer != window.GameVariables.CurrentCorrectAnswer)){
						revealNormalToCorrectAnswerStep1(window.GameVariables.CurrentCorrectAnswer.toUpperCase());
					}
					else if(window.GameVariables.CurrentTargetAnswer == window.GameVariables.CurrentCorrectAnswer){
						showFinalToCorrectAnswerStep1(window.GameVariables.CurrentCorrectAnswer.toUpperCase());
					}
					
					if(window.GameVariables.IsLevelStrapShowing == true){
						hideLevelStrap();
						window.GameVariables.IsLevelStrapShowing = false;
					}
					
					hideLifeLineCentered();
				}
				else if(window.GameVariables.walkAwaySequenceCounter == 2){
					hideQuestionAndAnswerStraps();
					window.GameVariables.QuestionInProgress = false;
					window.GameVariables.walkAwaySequenceCounter = -1;
				}
				
				window.GameVariables.walkAwaySequenceCounter++;
			}
		}
	});
});

