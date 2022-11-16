package org.leanpoker.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ScoreToActionUtil {

    public static Action scoreToActionWithMoreCards(List<GameState.HoleCard> cards, List<GameState.HoleCard> playerCards, List<GameState.HoleCard> communityCards, int score, int buyInAmount) {
        return switch (score) {
            case 0 -> highCardAction(cards, buyInAmount);
            case 1 -> pairCardAction(cards, playerCards, communityCards, buyInAmount);
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

    private static Action highCardAction(List<GameState.HoleCard> cards, int buyInAmount) {
        if (cards.size() < 7) {
            if (buyInAmount > 100) {
                return Action.CHECK_FOLD;
            }
            return Action.CALL;
        }
        return Action.CHECK_FOLD;
    }

    private static Action pairCardAction(
            List<GameState.HoleCard> cards,
            List<GameState.HoleCard> playerCards,
            List<GameState.HoleCard> communityCards,
            int buyInAmount) {
        boolean communityHasPair = communityHasPairs(communityCards);
        boolean playerHasPair = playerCards.get(0).rank.equals(playerCards.get(1).rank);
        if (!communityHasPair && !playerHasPair) {
            final String rankingPair = communityCards
                    .stream()
                    .filter(communityCard -> playerCards.stream().anyMatch(playerCard -> playerCard.rank.equals(communityCard.rank)))
                    .findAny()
                    .get()
                    .rank;
            boolean rankingPairHigh = HIGH_CARDS.contains(rankingPair);
            if (rankingPairHigh) {
                return Action.CALL;
            }
            if (buyInAmount > 100) {
                return Action.CHECK_FOLD;
            }
            return Action.CALL;
        }
        if (playerHasPair) {
            boolean highCardPair = HIGH_CARDS.contains(playerCards.get(0).rank);
            if (highCardPair) {
                return Action.ALL_IN;
            }
            if (cards.size() < 7) {
                if (buyInAmount > 100) {
                    return Action.CHECK_FOLD;
                }
                return Action.CALL;
            }
            return Action.CALL;
        }
        return Action.CHECK_FOLD;
    }

    private static boolean communityHasPairs(List<GameState.HoleCard> communityCards) {
        var cardsByRank = communityCards.stream().collect(Collectors.groupingBy(x -> x.rank));
        for (Map.Entry<String, List<GameState.HoleCard>> cardByRank : cardsByRank.entrySet()) {
            if (cardByRank.getValue().size() > 1) {
                return true;
            }
        }
        return false;
    }

    public static final Set<String> HIGH_PREFLOP_CARDS = Set.of("A", "K", "Q");
    public static final Set<String> HIGH_CARDS = Set.of("A", "K", "Q", "J", "10");
    public static final Set<String> LOW_CARDS = Set.of("2", "3", "4", "5", "6", "7", "8");

    public static Action scoreToAction(List<GameState.HoleCard> playerCards, List<GameState.HoleCard> communityCards, int score, int buyInAmount) {
        var cards = new ArrayList<GameState.HoleCard>();
        cards.addAll(playerCards);
        cards.addAll(communityCards);
        if (cards.size() == 2) {
            return preFlopAction(playerCards, buyInAmount);
        }
        return scoreToActionWithMoreCards(cards, playerCards, communityCards, score, buyInAmount);
    }

    public static Action preFlopAction(List<GameState.HoleCard> cards, int buyInAmount) {
        boolean isPair = cards.get(0).rank.equals(cards.get(1).rank);
        boolean sameSuit = cards.get(0).suit.equals(cards.get(1).suit);
        boolean firstCardHigh = HIGH_PREFLOP_CARDS.contains(cards.get(0).rank);
        boolean secondCardHigh = HIGH_PREFLOP_CARDS.contains(cards.get(1).rank);
        if (isPair) {
            if (firstCardHigh && secondCardHigh) {
                return Action.ALL_IN;
            }
            return Action.CALL;
        }
        if (firstCardHigh && secondCardHigh) {
            return Action.RAISE;
        } else if (firstCardHigh || secondCardHigh) {
            if (buyInAmount > 250) {
                return Action.CHECK_FOLD;
            }
            if (sameSuit) {
                return Action.RAISE;
            }
            return Action.CALL;
        } else {
            boolean firstCardLow = LOW_CARDS.contains(cards.get(0).rank);
            boolean secondCardLow = LOW_CARDS.contains(cards.get(1).rank);
            if (!firstCardLow && !secondCardLow) {
                if (buyInAmount > 50) {
                    return Action.CHECK_FOLD;
                }
                return Action.CALL;
            } else if (firstCardLow && secondCardLow) {
                return Action.CHECK_FOLD;
            }
            if (sameSuit) {
                if (buyInAmount > 50) {
                    return Action.CHECK_FOLD;
                }
                return Action.CALL;
            }
            return Action.CHECK_FOLD;
        }
    }
}
