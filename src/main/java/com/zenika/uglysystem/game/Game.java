package com.zenika.uglysystem.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {

    private static final Logger LOG = LoggerFactory.getLogger(Game.class);

    private static final String POP = "Pop";
    private static final String SCIENCE = "Science";
    private static final String SPORTS = "Sports";
    private static final String ROCK = "Rock";

    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    private int currentPlayer = 0;

    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
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
        LOG.info("{} was added", playerName);
        LOG.info("They are player number {}", players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        LOG.info("{} is the current player", players.get(currentPlayer));
        LOG.info("They have rolled a {}", roll);
        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                LOG.info(players.get(currentPlayer) + " is getting out of the penalty box");
                places[currentPlayer] = places[currentPlayer] + roll;
                if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
                LOG.info("{}'s new location is {}", players.get(currentPlayer), places[currentPlayer]);
                final String category = currentCategory();
                LOG.info("The category is {}", category);
                askQuestion();
            } else {
                LOG.info("{} is not getting out of the penalty box", players.get(currentPlayer));
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
            LOG.info("{}'s new location is {}", players.get(currentPlayer), places[currentPlayer]);
            final String category = currentCategory();
            LOG.info("The category is {}", category);
            askQuestion();
        }
    }

    private void askQuestion() {
        final String category = currentCategory();
        Object objectRemoved=new Object();
        if (category.equals(POP))
            objectRemoved = popQuestions.removeFirst();
        if (category.equals(SCIENCE))
            objectRemoved = scienceQuestions.removeFirst();
        if (category.equals(SPORTS))
            objectRemoved = sportsQuestions.removeFirst();
        if (category.equals(ROCK))
            objectRemoved = rockQuestions.removeFirst();
        LOG.info("{}", objectRemoved);
    }

    private String currentCategory() {
        if (places[currentPlayer] == 0) return POP;
        if (places[currentPlayer] == 4) return POP;
        if (places[currentPlayer] == 8) return POP;
        if (places[currentPlayer] == 1) return SCIENCE;
        if (places[currentPlayer] == 5) return SCIENCE;
        if (places[currentPlayer] == 9) return SCIENCE;
        if (places[currentPlayer] == 2) return SPORTS;
        if (places[currentPlayer] == 6) return SPORTS;
        if (places[currentPlayer] == 10) return SPORTS;
        return ROCK;
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                LOG.info("Answer was correct!!!!");
                purses[currentPlayer]++;
                LOG.info("{} now has {}", players.get(currentPlayer), purses[currentPlayer]);
                boolean winner = didPlayerWin();
                currentPlayer++;

                if (currentPlayer == players.size())
                    currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size())
                    currentPlayer = 0;
                return true;
            }
        } else {
            LOG.info("Answer was corrent!!!!");
            purses[currentPlayer]++;
            LOG.info("{} now has {}", players.get(currentPlayer), purses[currentPlayer]);
            boolean winner = didPlayerWin();
            currentPlayer++;

            if (currentPlayer == players.size())
                currentPlayer = 0;
            return winner;
        }
    }

    public boolean wrongAnswer() {
        LOG.info("Question was incorrectly answered");
        LOG.info("{} was sent to the penalty box", players.get(currentPlayer));
        inPenaltyBox[currentPlayer] = true;
        currentPlayer++;
        if (currentPlayer == players.size())
            currentPlayer = 0;
        return true;
    }

    private boolean didPlayerWin() {
        return purses[currentPlayer] != 6;
    }
}
