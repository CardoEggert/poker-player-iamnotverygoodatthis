package org.leanpoker.player;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreToActionUtilTest {

    @Test
    oid scoreToAction() {
        List<GameState.HoleCard> playerCards = List.of(new GameState.HoleCard("J", "diamonds"), new GameState.HoleCard("10", "spades"));
        System.out.println(ScoreToActionUtil.scoreToAction(playerCards, List.of(),0,1000));
    }
}