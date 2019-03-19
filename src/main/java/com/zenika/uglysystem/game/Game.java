package com.zenika.uglysystem.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Game {
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
	private static final Logger LOGGER = Logger.getLogger( "GameLogger" );
	
	private static final String SCIENCE = "Science";
	private static final String SPORTS = "Sports";
    
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	public boolean add(String playerName) {
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    LOGGER.log(Level.INFO, playerName + " was added");
	    LOGGER.log(Level.INFO, "They are player number " + players.size());

		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		LOGGER.log(Level.INFO, players.get(currentPlayer) + " is the current player");
		LOGGER.log(Level.INFO, "They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				LOGGER.log(Level.INFO, players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

			    LOGGER.log(Level.INFO, players.get(currentPlayer) 
						+ "'s new location is " 
						+ places[currentPlayer]);
			    LOGGER.log(Level.INFO, "The category is " + currentCategory());

				askQuestion();
			} else {
				LOGGER.log(Level.INFO, players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
			LOGGER.log(Level.INFO, players.get(currentPlayer) 
					+ "'s new location is " 
					+ places[currentPlayer]);
			LOGGER.log(Level.INFO, "The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory() == "Pop")
			LOGGER.log(Level.INFO, popQuestions.removeFirst().toString());
		if (currentCategory() == SCIENCE)
			LOGGER.log(Level.INFO, scienceQuestions.removeFirst().toString());
		if (currentCategory() == SPORTS)
			LOGGER.log(Level.INFO, sportsQuestions.removeFirst().toString());
		if (currentCategory() == "Rock")
			LOGGER.log(Level.INFO, rockQuestions.removeFirst().toString());
	}
	
	
	private String currentCategory() {
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return SCIENCE;
		if (places[currentPlayer] == 5) return SCIENCE;
		if (places[currentPlayer] == 9) return SCIENCE;
		if (places[currentPlayer] == 2) return SPORTS;
		if (places[currentPlayer] == 6) return SPORTS;
		if (places[currentPlayer] == 10) return SPORTS;
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				LOGGER.log(Level.INFO, "Answer was correct!!!!");
				purses[currentPlayer]++;
				LOGGER.log(Level.INFO, players.get(currentPlayer) 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			LOGGER.log(Level.INFO, "Answer was corrent!!!!");
			purses[currentPlayer]++;
			LOGGER.log(Level.INFO, players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		LOGGER.log(Level.INFO, "Question was incorrectly answered");
		LOGGER.log(Level.INFO, players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
