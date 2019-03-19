package mechanics;

import main.GameInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class GiveCards extends GameInput {
    public static final int NUMBER_OFMAXIMUM_CARDS = 6;

    public GiveCards() {

    }

    private List<ArrayList<Integer>> setOfCards;

    public GiveCards(final int numberOfPlayers) {
        setOfCards = new ArrayList<ArrayList<Integer>>(numberOfPlayers);
    }

    /*Set the cards for a player*/
    public void setSetOfCards(final List<ArrayList<Integer>> setOfCards) {
        this.setOfCards = setOfCards;
    }

    /*get the cards of a player*/
    public List<ArrayList<Integer>> getSetOfCards() {
        return setOfCards;
    }

    /*give the first set of cards (obvious name :) )*/
    public void giveFirstSetOfCards(final Queue<Integer> assetsIds, final int numberOfPlayers,
                                    int cardToAdd) {

        for (int i = 0; i < numberOfPlayers; i++) {
            ArrayList<Integer> cards = new ArrayList<>();
            while (cardToAdd < NUMBER_OFMAXIMUM_CARDS) {
                cards.add(assetsIds.peek());
                assetsIds.remove(assetsIds.peek());
                assetsIds.add(cards.get(cardToAdd));
                cardToAdd++;
            }
            setOfCards.add(cards);
            cardToAdd = 0;
        }
    }

    /*give More cards if it is needed*/
    public void giveMoreCards(final GiveCards cardsOfPlayer, final Queue<Integer> assetsIds) {
        for (int i = 0; i < cardsOfPlayer.getSetOfCards().size(); i++) {
            ArrayList<Integer> arrays = cardsOfPlayer.getSetOfCards().get(i);
            int counter = arrays.size() - 1;
            while (arrays.size() < NUMBER_OFMAXIMUM_CARDS) {
                arrays.add(assetsIds.peek());
                assetsIds.remove(assetsIds.peek());
                assetsIds.add(arrays.get(counter));
                counter++;
            }

        }
    }


}
