package org.leanpoker.player;

import java.util.ArrayList;
import java.util.List;

import org.leanpoker.player.rank.RankClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Player {

    final static Logger logger = LoggerFactory.getLogger(Player.class);

    static final String VERSION = "Playing Java player 0.1";

    static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static int betRequest(JsonNode request) {
        try {
            GameState state = mapper.treeToValue(request, GameState.class);
            var rank = getRank(state);
            var action = ScoreToActionUtil.scoreToAction(rank);
            logger.info("state {}", request.toPrettyString());
            logger.info("action {}", action);
            return BettingUtil.bet(state,action);
        } catch (Exception e) {
            logger.error("gameState", e);
        }
        return 0;
    }

    public static void showdown(JsonNode game) {
        try {
            GameState state = mapper.treeToValue(game, GameState.class);
            logger.info("state {}", game.toPrettyString());
        } catch (Exception e) {
            logger.error("gameState", e);
        }
    }

    public static int getRank(GameState state) {
        List<GameState.HoleCard> cards = new ArrayList<>();
        cards.addAll(state.getPlayerCards());
        cards.addAll(state.community_cards.stream().map(cc -> new GameState.HoleCard(cc.rank, cc.suit)).toList());
        var response = RankClient.getRank(cards);
        return response.rank;
    }
}
