package com.zenika.uglysystem.game;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SomeTest {

    @Test
    @Ignore
    public void true_is_true() throws Exception {
        assertTrue(false);
    }

    @Test
    public void should_add_a_player_to_the_game(){
        //Given
        String name = "Lolo";
        Game game = new Game();

        //When
        game.add(name);

        //Assert
        Assert.assertEquals(game.players.get(0),name);
    }

    @Test
    public void should_return_true_if_game_is_not_playable(){
        //Given
        String namePlayer1 = "Lolo";
        Game game = new Game();
        game.add(namePlayer1);

        //When
        boolean checkIfPlayable = game.isPlayable();

        //Assert
        Assert.assertFalse(checkIfPlayable);
    }

    private Game initCorrectGame(){
        String namePlayer1 = "Lolo";
        String namePlayer2 = "Pierre";
        Game game = new Game();
        game.add(namePlayer1);
        game.add(namePlayer2);
        return game;
    }

    @Test
    public void should_return_true_if_game_is_playable(){
        //Given
        Game game = initCorrectGame();

        //When
        boolean checkIfPlayable = game.isPlayable();

        //Assert
        Assert.assertTrue(checkIfPlayable);
    }

    @Test
    public void should_return_true_if_player_was_correctly_answered(){
        //Given
        Game game = initCorrectGame();

        //When
        game.roll(1);
        boolean correctAnswer = game.wasCorrectlyAnswered();

        //Assert
        Assert.assertTrue(correctAnswer);
    }

    @Test
    public void should_return_true_if_player_was_wrong(){
        //Given
        Game game = initCorrectGame();

        //When
        game.roll(1);
        boolean wrongAnswer = game.wrongAnswer();

        //Assert
        Assert.assertTrue(wrongAnswer);
    }

    @Test
    public void should_return_true_if_the_first_player_is_in_the_penalty_box(){
        //Given
        Game game = initCorrectGame();
        //When
        game.roll(2);
        game.wrongAnswer();

        //Assert
        Assert.assertTrue(game.inPenaltyBox[0]);
    }

    @Test
    public void should_return_true_if_the_first_player_was_in_penalty_box_and_is_getting_out_of_the_penalty_box(){
        //Given
        Game game = initCorrectGame();
        //When
        game.roll(2);
        game.wrongAnswer();
        game.roll(1);
        game.wrongAnswer();
        game.roll(3);
        game.wasCorrectlyAnswered();

        //Assert
        Assert.assertEquals(5,game.places[0]);
        Assert.assertTrue(game.isGettingOutOfPenaltyBox);
    }

    @Test
    public void should_return_true_if_the_first_player_was_in_penalty_box_and_is_not_getting_out_of_the_penalty_box(){
        //Given
        Game game = initCorrectGame();
        //When
        game.roll(2);
        game.wrongAnswer();
        game.roll(1);
        game.wrongAnswer();
        game.roll(4);
        game.wasCorrectlyAnswered();

        //Assert
        Assert.assertFalse(game.isGettingOutOfPenaltyBox);
    }

    @Test
    public void should_return_check_different_category(){
        //Given
        Game game = initCorrectGame();
        //When
        game.roll(0);
        game.wrongAnswer();
        game.roll(1);
        game.wrongAnswer();
        game.roll(2);
        game.wrongAnswer();
        game.roll(3);
        game.wrongAnswer();
        game.roll(4);
        boolean correctAnswer = game.wasCorrectlyAnswered();

        //Assert
        Assert.assertTrue(correctAnswer);
    }
}
