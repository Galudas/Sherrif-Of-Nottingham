package mechanics;

import assets.GetAsset;
import assets.IllegalGoods;
import player.Player;

import java.util.Map;

public class ScoreCalculator extends Player {
    public ScoreCalculator() {
    }
    /*It calculates the score of the player*/
    public int scoreCalculator(final Player player) {
        int score = 0;
        for (Map.Entry<Integer, Integer> entry : player.getMyCards().entrySet()) {
            GetAsset asset = new GetAsset();

            IllegalGoods assets = asset.cardType(entry.getKey());
            score = score + assets.getProfit() * entry.getValue();

        }
        return score;
    }
}
