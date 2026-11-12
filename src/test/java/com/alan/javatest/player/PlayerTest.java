package com.alan.javatest.player;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void looses_when_dice_number_is_too_low(){
        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.rol()).thenReturn(2);

        Player player = new Player (dice, 3);
        assertFalse(player.play());
    }

    @Test
    public void looses_when_dice_number_is_bag(){
        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.rol()).thenReturn(4);

        Player player = new Player (dice, 3);
        assertTrue(player.play());
    }

}