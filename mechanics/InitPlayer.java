package mechanics;

import player.Player;
import stategy.BasicStategy;
import stategy.BribeStrategy;
import stategy.GreedyStrategy;
import stategy.Strategy;

import java.util.ArrayList;

public abstract class InitPlayer {
    public static Strategy initPlayers(final String strategy, final ArrayList<Integer> cards,
                                       final Player playerS,
                                       final int greedyComerciant) {
        if (!cards.isEmpty()) {
            if (strategy.equals("basic")) {
                Strategy player = new BasicStategy(cards);
                player.createSack();
                return player;
            }
            if (strategy.equals("greedy")) {
                Strategy player = new GreedyStrategy(cards);
                player.createSack(greedyComerciant);
                return player;
            }
            if (strategy.equals("bribed")) {
                Strategy player = new BribeStrategy(cards);
                player.createSack(playerS);
                return player;
            }
        }
        return null;
    }

}
