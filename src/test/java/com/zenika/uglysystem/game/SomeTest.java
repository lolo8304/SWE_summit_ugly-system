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
    public void should_return_true_if_game_is_playable(){
        //Given
        String namePlayer1 = "Lolo";
        String namePlayer2 = "Pierre";
        Game game = new Game();
        game.add(namePlayer1);
        game.add(namePlayer2);

        //When
        boolean checkIfPlayable = game.isPlayable();

        //Assert
        Assert.assertTrue(checkIfPlayable);
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

}
