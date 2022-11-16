package org.leanpoker.player;

public class ScoreToActionUtil {

    public static Action scoreToAction(int score) {
        return switch (score) {
            case 0 -> // High card
                    Action.CHECK_FOLD;
            case 1 -> // Pair
                    Action.CALL;
            case 2 -> // Two Pairs
                    Action.RAISE; // Three of a kind
            case 3, 4 -> // Straight
                    Action.RAISE_X2; // Flush
            // Full house
            // Four of a kind
            case 5, 6, 7, 8 -> // Straight flush
                    Action.ALL_IN;
            default -> Action.CHECK_FOLD;
        };
    }

}
