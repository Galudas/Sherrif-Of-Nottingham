package stategy;

import mechanics.EliminateAndSearch;
import player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class BribeStrategy extends BasicStategy implements Strategy {
    private final int numberOfAssets = 6;
    private final int silkId = 10;
    private final int allowedToAdd6 = 6;
    private final int allowedToAdd3 = 3;
    private final int fiveCardsToAdd  = 5;
    private final int tenCardsToAdd = 10;

    public BribeStrategy(final ArrayList<Integer> sack) {
        super(sack);
    }

    /*Create sack for Bribe PLayer*/
    public void createSack(final Player player) {
        int cardsAllowed = -1;
        int howMany = 0;
        if (player.getGold() == 0 || player.getGold() < numberOfAssets - 1) {
            super.createSack();
            return;
        }
        int illegalCards = numberOfIllegalCards();
        if (illegalCards == 0) {
            super.createSack();
            player.setBribe(0);
            return;
        }
        if (illegalCards >= 2) {
            if (player.getGold() > silkId) {
                cardsAllowed = allowedToAdd6;
            } else {
                cardsAllowed = allowedToAdd3;
            }
        }
        if (illegalCards < 2) {

            if (player.getGold() > fiveCardsToAdd) {
                cardsAllowed = allowedToAdd3;
            } else {

                super.createSack();
                return;
            }
        }
        ArrayList<Integer> clone = (ArrayList<Integer>) this.getSack().clone();
        while (cardsAllowed > 0) {
            EliminateAndSearch eliminate = new EliminateAndSearch();
            int cardToPut = theMostValueableIllegalCard(clone);
            Integer howManys = howMany(cardToPut, clone);
            eliminate.eliminateUsedCards(cardToPut, clone, -1);
            if (cardToPut != 0) {
                int appelId = 0;
                HashMap<Integer, Integer> cards = getActualSackDeclared();
                HashMap<Integer, Integer> declared = getSackDeclared();
                declared.put(appelId, 1);
                cards.put(cardToPut, howManys);
                setActualSackDeclared(cards);
                setSackDeclared(declared);
                usedCards.put(cardToPut, 1);
                sack.remove(sack.indexOf(cardToPut));
            } else if (cardToPut == 0) {
                break;
            }
            howMany++;
        }
        if (howMany <= 2) {
            player.setBribe(fiveCardsToAdd);
            player.setGold(player.getGold() - fiveCardsToAdd);
        }
        if (howMany > 2) {
            player.setBribe(tenCardsToAdd);
            player.setGold(player.getGold() - tenCardsToAdd);
        }
    }

    /**/
    public int numberOfIllegalCards() {
        ArrayList<Integer> clone = (ArrayList<Integer>) this.getSack().clone();
        int ilegalCard = theMostValueableIllegalCard(clone);
        int numberOfIlegalCards = 0;
        while (ilegalCard != 0) {
            int index = clone.indexOf(ilegalCard);
            clone.remove(index);
            numberOfIlegalCards++;
            ilegalCard = theMostValueableIllegalCard(clone);
        }
        return numberOfIlegalCards;
    }

}
