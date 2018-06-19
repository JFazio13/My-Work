(function(gameVariables) {
	/* Initialization Variables */
	gameVariables.IsPAFLifeLineUsed = false;
	gameVariables.IsFFLifeLineUsed = false;
	gameVariables.IsATALifeLineUsed = false;
	gameVariables.IsSTQLifeLineUsed = false;
	gameVariables.IsDDLifeLineUsed = false;
	gameVariables.IsSTQLifeLineActiveAtStart = false;
	gameVariables.StartingQuestionLevel = 1;
	gameVariables.Resuming = 1;
	gameVariables.DisableLL = 1;
	gameVariables.DisableNow = 0;
	gameVariables.ResumeNow = 0;
	gameVariables.STQEnabled = 0;
	gameVariables.STQDisabled = 0;
	gameVariables.STQDisabledStart = 0;
	gameVariables.DDEnabled = 0;
	gameVariables.DDDisabled = 0;
	gameVariables.FirstQ = true;
	gameVariables.LLStarted = false;
	gameVariables.LLComplete = false;
	gameVariables.ClockStopped = false;
	gameVariables.ClockDone = false;
	gameVariables.FirstTick = true;
	gameVariables.Tier1Playing = false;
	gameVariables.MillionDollarClockTime = 45;
	gameVariables.GlintInterval = 0;
	gameVariables.TimeUp = false;
	gameVariables.Rotation = 0;
	gameVariables.CurrentTime = 0;
	gameVariables.RewindCounter = 0;
	gameVariables.TreeScaled = false;
	gameVariables.BarHeight = 952;
	gameVariables.CannotWalkAway = false;
	gameVariables.GameOver = false;

	gameVariables.ContestantFirstName = "JULIA";
	gameVariables.ContestantLastName = "MCDONALD";
	gameVariables.ContestantLocation = "LOCKPORT, NY";
	
	/* Do not modify the value of this variable, fastest finger is not fully implemented yet... */
	gameVariables.IsPlayingFasestFinger = false;
	/**************/
	
	/* Boolean Variables */
	gameVariables.IsDisclaimerShowing = true;
	gameVariables.IsDoubleDipActive = false;
	gameVariables.IsNameStrapShowing = false;
	gameVariables.IsMoneyTreeShowing = false;
	gameVariables.IsLogoShowing = false;
	gameVariables.IsExplainingRules = false;
	gameVariables.IsFirstQuestionOfGame = true;
	gameVariables.IsInCommercial = true;
	gameVariables.IsLevelStrapShowing = false;
	gameVariables.IsLifeLineStrapShowing = false;
	gameVariables.IsTotalPrizeMoneyShowing = false;
	gameVariables.IsWalkingAway = false;
	gameVariables.CannotLockInFinalAnswer = false;
	gameVariables.LockedIn = false;
	gameVariables.QuestionInProgress = false;
	gameVariables.ContinuePulsingLifeLineCenter = false;
	gameVariables.AnswerAIsOut = false;
	gameVariables.AnswerBIsOut = false;
	gameVariables.AnswerCIsOut = false;
	gameVariables.AnswerDIsOut = false;
	gameVariables.ShowLifeLineCenteredAnimation = false;
	gameVariables.FirstTierBackgroundSoundPlaying = false;
	gameVariables.STQ == false;

	/* Number Variables */
	gameVariables.AnswerAPercent = 0;
	gameVariables.AnswerBPercent = 0;
	gameVariables.AnswerCPercent = 0;
	gameVariables.AnswerDPercent = 0;
	gameVariables.QuestionLevel = null;
	gameVariables.RevealAnswerCounter = 0;
	gameVariables.RevealFastestFingerWinnersCounter = 0;
	gameVariables.RevealFastestFingerContestantCounter = 0;
	gameVariables.FastestFingerConstestantWinner = 3;
	gameVariables.FastestFingerRevealAnswerCounter = 0;
	gameVariables.FastestFingerSequenceCounter = 0;
	gameVariables.MoneyTreeSequenceCounter = 0;
	gameVariables.QuestionSequenceCounter = 0;
	gameVariables.pafLifeLineSequenceCounter = 0;
	gameVariables.ataLifeLineSequenceCounter = 0;
	gameVariables.stqLifeLineSequenceCounter = 0;
	gameVariables.ddLifeLineSequenceCounter = 0;
	gameVariables.walkAwaySequenceCounter = 0;
	gameVariables.ScaleTreeCounter = 0;
	gameVariables.LifeLineAnimationCounter = 0;
	gameVariables.STQUnlockedLevel = 6;
	gameVariables.IsFirstDoubleDipAnswerWrong = false;
	gameVariables.DDUsed = false;
		
	/* String and Char Variables */
	gameVariables.CurrentCorrectAnswer = "";
	gameVariables.CurrentTargetAnswer = "";
	
	/* Audio Variables */
	gameVariables.GeneralSound = null;
	gameVariables.LifelineActiveSound = null;
	gameVariables.LifelinePassiveSound = null;
	gameVariables.LongActiveSound = null;
	gameVariables.LongPassiveSound = null;
	gameVariables.ShortActiveSound = null;
	gameVariables.ShortPassiveSound = null;
	gameVariables.ScalingSound = null;
	gameVariables.BarSound = null;
	gameVariables.PulseSound = null;
	gameVariables.ATASound = null;
	gameVariables.GlintSound = new Audio("Sounds/clock_glint.mp3");

	/* Array Variables */
	gameVariables.QuestionsAndAnswers = [];
	gameVariables.SwitchQuestionsAndAnswers = [];
	gameVariables.AskTheAudienceVotingPercents = [0,0,0,0];
	gameVariables.PrizeAmounts = [100,200,300,500,1000,2000,4000,8000,16000,25000,50000,100000,250000,500000,1000000];
	gameVariables.FastestFingerContestantWinners = [false,false,true,false,false,false,true,false,false,false];
	gameVariables.FastestFingerContestantTimes = [2.54,4.54,5.54,6.54,9.54,13.54,15.54,16.54,7.54,19.54];
	gameVariables.FastestFingerContestants = []
	gameVariables.LightsDownSounds = ["lights_down_1.mp3","lights_down_1.mp3","lights_down_1.mp3","lights_down_1.mp3","lights_down_1.mp3","lights_down_1.mp3","lights_down_2.mp3","lights_down_3.mp3","lights_down_4.mp3","lights_down_5.mp3","lights_down_1.mp3","lights_down_2.mp3","lights_down_3.mp3","lights_down_4.mp3","lights_down_5.mp3"];
	gameVariables.RoundBackgroundSounds = ["q1_to_q5_bed.mp3","q1_to_q5_bed.mp3","q1_to_q5_bed.mp3","q1_to_q5_bed.mp3","q1_to_q5_bed.mp3","q6_clock.mp3","q7_clock.mp3","q8_clock.mp3","q9_clock.mp3","q10_clock.mp3","q11_clock.mp3","q12_clock.mp3","q13_clock.mp3","q14_clock.mp3","q15_clock.mp3"];
	gameVariables.FinalAnswerSounds = ["","","","","","final_answer_1.mp3","final_answer_2.mp3","final_answer_3.mp3","final_answer_4.mp3","final_answer_5.mp3","final_answer_1.mp3","final_answer_2.mp3","final_answer_3.mp3","final_answer_4.mp3","final_answer_5.mp3"];
	gameVariables.CorrectAnswerSounds = ["q1_to_q4_correct.mp3","q1_to_q4_correct.mp3","q1_to_q4_correct.mp3","q1_to_q4_correct.mp3","q5_correct.mp3","q6_correct.mp3","q7_correct.mp3","q8_correct.mp3","q9_correct.mp3","q10_correct.mp3","q11_correct.mp3","q12_correct.mp3","q13_correct.mp3","q14_correct.mp3","q15_correct.mp3"];
	gameVariables.WrongAnswerSounds = ["q1_to_q5_lose.mp3","q1_to_q5_lose.mp3","q1_to_q5_lose.mp3","q1_to_q5_lose.mp3","q1_to_q5_lose.mp3","q6_lose.mp3","q7_lose.mp3","q8_lose.mp3","q9_lose.mp3","q10_lose.mp3","q11_lose.mp3","q12_lose.mp3","q13_lose.mp3","q14_lose.mp3","q15_lose.mp3"];

	/* Interval/Timeout Variables */
	gameVariables.ShowAnswerTimeout = null;
	gameVariables.ShowWinnerTimeout = null;
	gameVariables.ScaleTreeTimeout = null;
	gameVariables.PAFClockTimeout = null;
	gameVariables.ClockTimeout = null;

})(window.GameVariables = window.GameVariables || {});








