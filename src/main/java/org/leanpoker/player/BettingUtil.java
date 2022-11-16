package org.leanpoker.player;

public class BettingUtil {

	public static int bet(GameState state, Action action) {
		org.leanpoker.player.GameState.Player p = findSelf(state);
		int currentBuyIn = state.current_buy_in;
		int minimumraise = state.minimum_raise;
		int currentBet = p.bet;
		switch (action) {
		case CHECK_FOLD:
			return 0;
		case ALL_IN:
			return p.stack;
		case CALL:
			return Math.min(p.stack, currentBuyIn - currentBet);
		case RAISE:
			return Math.min(p.stack, 2*(currentBuyIn - currentBet + minimumraise));
		case RAISE_X2:
			return Math.min(p.stack, 3*(currentBuyIn - currentBet + minimumraise));
		case RAISE_X4:
			return Math.min(p.stack, 4*(currentBuyIn - currentBet + minimumraise));
		case RAISE_X10:
			return Math.min(p.stack, 10*(currentBuyIn - currentBet + minimumraise));
		}
		return 0;
	}

	private static org.leanpoker.player.GameState.Player findSelf(GameState gs) {
		for (org.leanpoker.player.GameState.Player player : gs.players) {
			if (player.name.equals("IamNotVeryGoodAtThis")) {
				return player;
			}
		}
		throw new RuntimeException("Self not found");

	}
}
