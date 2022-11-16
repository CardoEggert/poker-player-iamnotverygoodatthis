package org.leanpoker.player;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScoreToActionUtilTest {

    @Test
    void rank0() {
        Assertions.assertEquals(Action.CHECK_FOLD, ScoreToActionUtil.scoreToAction(0));
    }

    @Test
    void rank1() {
        Assertions.assertEquals(Action.CALL, ScoreToActionUtil.scoreToAction(1));
    }

    @Test
    void rank2() {
        Assertions.assertEquals(Action.RAISE, ScoreToActionUtil.scoreToAction(2));
    }

    @Test
    void rank3() {
        Assertions.assertEquals(Action.RAISE_X2, ScoreToActionUtil.scoreToAction(3));
    }

    @Test
    void rank4() {
        Assertions.assertEquals(Action.RAISE_X2, ScoreToActionUtil.scoreToAction(4));
    }

    @Test
    void rank5() {
        Assertions.assertEquals(Action.ALL_IN, ScoreToActionUtil.scoreToAction(5));
    }

    @Test
    void rank6() {
        Assertions.assertEquals(Action.ALL_IN, ScoreToActionUtil.scoreToAction(6));
    }

    @Test
    void rank7() {
        Assertions.assertEquals(Action.ALL_IN, ScoreToActionUtil.scoreToAction(7));
    }

    @Test
    void rank8() {
        Assertions.assertEquals(Action.ALL_IN, ScoreToActionUtil.scoreToAction(8));
    }
}