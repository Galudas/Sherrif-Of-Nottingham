package stategy;

import java.util.ArrayList;
import java.util.HashMap;

public class GreedyStrategy extends BasicStategy implements Strategy {
    public static final int NUMBEROFASSETS = 6;
    private static int numberOfrounds;

    public GreedyStrategy(final ArrayList<Integer> sack) {
        super(sack);
        numberOfrounds = 1;
    }
    /*Create The sack for GreedyPlayer*/
    public void createSack(final int greedyComerciant) {
        super.createSack();
        if (greedyComerciant % 2 == 0) {
            if (getActualSackDeclared().size() < NUMBEROFASSETS - 1) {
                int card = theMostValueableIllegalCard(this.getSack());
                if (card != 0) {
                    HashMap<Integer, Integer> cards = getActualSackDeclared();
                    cards.put(card, 1);
                    getActualSackDeclared().put(card, 1);
                    usedCards.put(card, 1);
                }
                return;
            }
        }


    }
}
