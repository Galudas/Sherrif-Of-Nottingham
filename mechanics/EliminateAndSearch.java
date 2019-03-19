package mechanics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EliminateAndSearch extends GiveCards {
    public EliminateAndSearch() {
        super();
    }

    /*Eliminate used cards from a set of Cards*/
    public void eliminateUsedCards(final Integer toEliminate, final ArrayList<Integer> cards,
                                   Integer howManyTimes) {
        int length = cards.size() - 1;
        while (length > -1) {
            if (toEliminate.equals(cards.get(length))) {
                if (howManyTimes > 0) {
                    cards.remove(length);
                    howManyTimes--;
                }
                if (howManyTimes == -1) {
                    cards.remove(length);
                }
            }
            length--;
        }


    }

    public void eliminateUsedCards(final ArrayList<Integer> list, final Map<Integer,
            Integer> toRemove) {
        for (Map.Entry<Integer, Integer> resourceCard : toRemove.entrySet()) {
            eliminateUsedCards(resourceCard.getKey(), list, resourceCard.getValue());
        }
    }

    /*Search a key of a card*/
    public int searchCard(final HashMap<Integer, Integer> map, final int key) {
        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() == key) {
                return entry.getKey();
            }
        }
        return -1;
    }

    /*Search a value of a card*/
    public int searchCardValue(final HashMap<Integer, Integer> map, final int key) {
        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() == key) {
                return entry.getValue();
            }
        }
        return -1;
    }
}
