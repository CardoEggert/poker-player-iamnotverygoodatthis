package org.leanpoker.player;

import java.util.List;

public class GameState {
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

	public static class CommunityCard{
	    public String rank;
	    public String suit;
	}

	public static class HoleCard{
	    public String rank;
	    public String suit;
	}

	public static class Player{
	    public int id;
	    public String name;
	    public String status;
	    public String version;
	    public int stack;
	    public int bet;
	    public List<HoleCard> hole_cards;
	}
}