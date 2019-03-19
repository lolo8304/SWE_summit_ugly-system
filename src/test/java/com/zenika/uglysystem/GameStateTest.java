package com.zenika.uglysystem;

import org.junit.Assert;
import org.junit.Test;

public class GameStateTest {

    @Test
    public void testFlags() {
        Assert.assertTrue(!GameState.getAllFlags().isEmpty());
    }


}
