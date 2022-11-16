package org.leanpoker.player.rank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RankResponse {

    public int rank;

    @Override
    public String toString() {
        return "RankResponse{" +
                "rank=" + rank +
                '}';
    }
}
