package org.leanpoker.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Player {
	final static Logger logger = LoggerFactory.getLogger(Player.class);

	static final String VERSION = "Default Java folding player";

	static final ObjectMapper mapper = new ObjectMapper();

	public static int betRequest(JsonNode request) {
		try {
			GameState state = mapper.treeToValue(request, GameState.class);
			logger.info("state {}", state);
		} catch (Exception e) {
			logger.error("gameState", e);
		}
		return 0;
	}

	public static void showdown(JsonNode game) {
		try {
			GameState state = mapper.treeToValue(game, GameState.class);
			logger.info("state {}", state);
		} catch (Exception e) {
			logger.error("gameState", e);
		}
	}
}
