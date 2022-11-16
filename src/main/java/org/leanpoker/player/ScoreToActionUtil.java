package org.leanpoker.player;

import java.util.List;
import java.util.Set;

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

    public static final Set<String> HIGH_CARDS = Set.of("A", "K", "Q");

    public static Action scoreToAction(List<GameState.HoleCard> cards, int score) {
        if (cards.size() == 2) {
            boolean isPair = cards.get(0).rank.equals(cards.get(1).rank);
            if (isPair) {
                return Action.RAISE_X10;
            }
            boolean firstCardHigh = HIGH_CARDS.contains(cards.get(0).rank);
            boolean secondCardHigh = HIGH_CARDS.contains(cards.get(1).rank);
            boolean sameSuit = cards.get(0).suit.equals(cards.get(1).suit);
            if (firstCardHigh && secondCardHigh) {
                if (sameSuit) {
                    return Action.RAISE_X4;
                }
                return Action.RAISE_X2;
            } else {
                if (sameSuit) {
                    return Action.RAISE;
                }
                return Action.CALL;
            }
        }
        return scoreToAction(score);
    }

}
