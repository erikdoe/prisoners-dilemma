package com.mullekybernetik.gagame.strategies;

import com.mullekybernetik.gagame.match.Move;
import junit.framework.Assert;
import org.junit.Test;

public class TitForTatTest {

    @Test
    public void shouldFirstCooperate() {
        TitForTat tft = new TitForTat();
        Assert.assertEquals(Move.COOPERATE, tft.getMove());
    }

    @Test
    public void shouldCooperateWhenOpponentCooperated() {
        TitForTat tft = new TitForTat();
        tft.setOpponentsMove(Move.COOPERATE);
        Assert.assertEquals(Move.COOPERATE, tft.getMove());
    }

    @Test
    public void shouldDefectWhenOpponentDefected() {
        TitForTat tft = new TitForTat();
        tft.setOpponentsMove(Move.DEFECT);
        Assert.assertEquals(Move.DEFECT, tft.getMove());
    }

}