package com.zenika.uglysystem.game;

import org.junit.Assert;
import org.junit.Test;

public class SomeTest {

    private Game initCorrectGameWith2Players(){
        String namePlayer1 = "Lolo";
        String namePlayer2 = "Pierre";
        Game game = new Game();
        game.add(namePlayer1);
        game.add(namePlayer2);
        return game;
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

    @Test
    public void should_return_true_if_game_is_playable(){
        //Given
        Game game = initCorrectGameWith2Players();

        //When
        boolean checkIfPlayable = game.isPlayable();

        //Assert
        Assert.assertTrue(checkIfPlayable);
    }

    @Test
    public void should_return_number_of_players_equal_to_2(){
        //Given
        Game game = initCorrectGameWith2Players();

        //When
        int playersNb=game.howManyPlayers();

        //Assert
        Assert.assertEquals(2,playersNb);
    }

    @Test
    public void should_return_Rock_Question_created_with_index_equal_to_1(){
        //Given
        Game game = initCorrectGameWith2Players();
        int questionIndex=1;

        //When
        String rockQuestion=game.createRockQuestion(questionIndex);

        //Assert
        Assert.assertEquals("Rock Question "+questionIndex,rockQuestion);
    }

    @Test()
    public void should_return_true_if_player_was_correctly_answered(){
        //Given
        Game game = initCorrectGameWith2Players();

        //When
        game.roll(1);
        boolean correctAnswer = game.wasCorrectlyAnswered();

        //Assert
        Assert.assertTrue(correctAnswer);
    }

    @Test
    public void should_return_true_if_player_was_wrong(){
        //Given
        Game game = initCorrectGameWith2Players();

        //When
        game.roll(1);
        boolean wrongAnswer = game.wrongAnswer();

        //Assert
        Assert.assertTrue(wrongAnswer);
    }

    @Test
    public void should_return_true_if_the_first_player_is_in_the_penalty_box(){
        //Given
        Game game = initCorrectGameWith2Players();
        //When
        game.roll(2);
        game.wrongAnswer();

        //Assert
        Assert.assertTrue(game.inPenaltyBox[0]);
    }

    @Test
    public void should_return_true_if_the_first_player_was_in_penalty_box_and_is_getting_out_of_the_penalty_box(){
        //Given
        Game game = initCorrectGameWith2Players();
        //When
        game.roll(2); //first player
        game.wrongAnswer();
        game.roll(1); //second player
        game.wrongAnswer();
        game.roll(3); //first player with roll modulo 2 != 0
        game.wasCorrectlyAnswered();

        //Assert
        Assert.assertEquals(5,game.places[0]);
        Assert.assertTrue(game.isGettingOutOfPenaltyBox);
    }

    @Test
    public void should_return_true_if_the_first_player_was_in_penalty_box_and_is_not_getting_out_of_the_penalty_box(){
        //Given
        Game game = initCorrectGameWith2Players();
        //When
        game.roll(1); //first player
        game.wrongAnswer();
        game.roll(3); //second player
        game.wrongAnswer();
        game.roll(4); //first player with roll modulo 2 = 0
        game.wasCorrectlyAnswered();

        //Assert
        Assert.assertFalse(game.isGettingOutOfPenaltyBox);
    }
}
