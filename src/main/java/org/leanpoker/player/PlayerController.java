package org.leanpoker.player;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

@Controller()
public class PlayerController {
	  final static Logger logger = LoggerFactory.getLogger(PlayerController.class);

    ObjectMapper mapper = new ObjectMapper();

    @Get(produces = MediaType.TEXT_PLAIN)
    public String doGet() {
        return "Java player is running";
    }

    @Post(produces = MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String doPost(@Body Map<String, String> body) throws JsonProcessingException {
        String action = body.get("action");
        String gameState = body.get("game_state");
        try {
            GameState state = mapper.readValue(gameState, GameState.class);
			logger.error("state", state);
		} catch (Exception e) {
			logger.error("gameState", e);
		}
        if ("bet_request".equals(action)) {
            return String.valueOf(Player.betRequest(mapper.readTree(gameState)));
        }
        if ("showdown".equals(action)) {
            Player.showdown(mapper.readTree(gameState));
        }
        if ("version".equals(action)) {
            return Player.VERSION;
        }
        return "";
    }

}
