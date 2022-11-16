package org.leanpoker.player;

import java.util.Collections;
import java.util.List;

public class GameState {

    public static final String PlayerName = "IamNotVeryGoodAtThis";
    public String tournament_id;
    public String game_id;
    public int round;
    public int bet_index;
    public int small_blind;
    public int current_buy_in;
    public int pot;
    public int minimum_raise;
    public int dealer;
    public int orbits;
    public int in_action;
    public List<Player> players;
    public List<CommunityCard> community_cards;

    public static class CommunityCard {

        public String rank;
        public String suit;

        @Override
        public String toString() {
            return "CommunityCard [rank=" + rank + ", suit=" + suit + "]";
        }

    }

    public static class HoleCard {

        public String rank;
        public String suit;

        public HoleCard() {
        }

        public HoleCard(String rank, String suit) {
            this.rank = rank;
            this.suit = suit;
        }

        @Override
        public String toString() {
            return "HoleCard [rank=" + rank + ", suit=" + suit + "]";
        }

    }

    public static class Player {

        public int id;
        public String name;
        public String status;
        public String version;
        public int stack;
        public int bet;
        public List<HoleCard> hole_cards;

        @Override
        public String toString() {
            return "Player [id=" + id + ", name=" + name + ", status=" + status + ", version=" + version + ", stack="
                    + stack + ", bet=" + bet + ", hole_cards=" + hole_cards + "]";
        }
    }

    @Override
    public String toString() {
        return "GameState [tournament_id=" + tournament_id + ", game_id=" + game_id + ", round=" + round
                + ", bet_index=" + bet_index + ", small_blind=" + small_blind + ", current_buy_in=" + current_buy_in
                + ", pot=" + pot + ", minimum_raise=" + minimum_raise + ", dealer=" + dealer + ", orbits=" + orbits
                + ", in_action=" + in_action + ", players=" + players + ", community_cards=" + community_cards + "]";
    }

    public List<HoleCard> getPlayerCards() {
        var ourPlayer = players.stream().filter(x -> x.name.equals(PlayerName)).findFirst();

        if (ourPlayer.isPresent()) {
            return ourPlayer.get().hole_cards;
        }
        return Collections.emptyList();
    }

}