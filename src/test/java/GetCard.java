import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.leanpoker.player.GameState;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetCard {

    ObjectMapper mapper = new ObjectMapper();
    String game_statate_string = """
            {
              "players":[
                {
                  "name":"IamNotVeryGoodAtThis",
                  "stack":1000,
                  "status":"active",
                  "bet":0,
                  "hole_cards":[{"rank":"2", "suit":"diamonds"}, {"rank":"3", "suit":"spades"}],
                  "version":"Version name 1",
                  "id":0
                },
                {
                  "name":"Player 2",
                  "stack":1000,
                  "status":"active",
                  "bet":0,
                  "hole_cards":[],
                  "version":"Version name 2",
                  "id":1
                }
              ],
              "tournament_id":"550d1d68cd7bd10003000003",
              "game_id":"550da1cb2d909006e90004b1",
              "round":0,
              "bet_index":0,
              "small_blind":10,
              "orbits":0,
              "dealer":0,
              "community_cards":[],
              "current_buy_in":0,
              "pot":0
            }
            """;
    GameState state = mapper.treeToValue(mapper.readTree(game_statate_string), GameState.class);

    public GetCard() throws JsonProcessingException {
    }

    @Test
    void getsCommunityCards() {
        var cards = state.getPlayerCards();
        Assertions.assertEquals(cards.size(), 2);
    }
}
