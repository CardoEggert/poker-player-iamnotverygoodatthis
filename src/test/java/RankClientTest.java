import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.leanpoker.player.GameState;
import org.leanpoker.player.rank.RankClient;
import org.leanpoker.player.rank.RankResponse;

class RankClientTest {

    @Test
    void getRank() {
        ArrayList<GameState.HoleCard> list = new ArrayList<>();
        list.add(new GameState.HoleCard("5", "diamonds"));
        list.add(new GameState.HoleCard("6", "diamonds"));
        list.add(new GameState.HoleCard("7", "diamonds"));
        list.add(new GameState.HoleCard("7", "spades"));
        list.add(new GameState.HoleCard("8", "diamonds"));
        list.add(new GameState.HoleCard("9", "diamonds"));

        RankResponse result = RankClient.getRank(list);

        Assertions.assertEquals(result.rank,8);

    }
}