package stategy;

import assets.IllegalGoods;
import assets.LegalGoods;
import player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;


public class BasicStategy extends IllegalGoods implements Strategy {
    private static final int CHEESE_ID = 1;
    private static final int BREAD_ID = 2;
    private static final int CHICKEN_ID = 3;
    private static final int SILK_ID = 10;
    private static final int PEPPER_ID = 11;
    private static final int BARREL_ID = 12;
    private static final int FREQUENCY = 13;
    protected Map<Integer, Integer> usedCards;
    protected ArrayList<Integer> sack;
    private int[] frequency;
    private HashMap<Integer, Integer> sackDeclared;
    private HashMap<Integer, Integer> actualSackDeclared;

    /**/
    public HashMap<Integer, Integer> getSackDeclared() {
        return sackDeclared;
    }

    /**/
    public Map<Integer, Integer> getUsedCards() {
        return usedCards;
    }

    /**/
    public void setUsedCards(final Map<Integer, Integer> usedCards) {
        this.usedCards = usedCards;
    }

    /**/
    public void setSackDeclared(final HashMap<Integer, Integer> sackDeclared) {
        this.sackDeclared = sackDeclared;
    }

    /**/
    public HashMap<Integer, Integer> getActualSackDeclared() {
        return actualSackDeclared;
    }

    /**/
    public void setActualSackDeclared(final HashMap<Integer, Integer> actualSackDeclared) {
        this.actualSackDeclared = actualSackDeclared;
    }

    /**/
    public ArrayList<Integer> getSack() {
        return sack;
    }

    /**/
    public void setSack(final ArrayList<Integer> sack) {
        this.sack = sack;
    }

    /**/
    public void setFrequency(final int[] frequency) {
        this.frequency = frequency;
    }


    @Override
    public void createSack(final int greedyComerciant) {

    }


    @Override
    public void createSack(final Player player) {

    }

    public BasicStategy(final ArrayList<Integer> sack) {
        setFrequency(new int[FREQUENCY]);
        setSack(sack);
        setSackDeclared(new HashMap<Integer, Integer>());
        setActualSackDeclared(new HashMap<Integer, Integer>());
        setUsedCards(new HashMap<Integer, Integer>());
    }

    /*Returns the Cards Frequency*/
    public void cardsFrequency() {
        int numberOfCards = this.sack.size();
        int contor1 = 0;
        int contor2 = 0;
        ArrayList<Integer> copy = (ArrayList<Integer>) sack.clone();

        Collections.sort(copy);
        Set<Integer> uniqueCards = new HashSet<Integer>(copy);
        int numberOfUniqueCards = uniqueCards.size() - 1;
        Iterator<Integer> goThroughUniqueCards = uniqueCards.iterator();
        int uniqueCard = goThroughUniqueCards.next();
        Iterator<Integer> goThroughCards = copy.iterator();
        int cardFromDeck = goThroughCards.next();
        for (int i = 0; i < numberOfCards; i++) {
            while (cardFromDeck == uniqueCard) {
                contor1++;
                this.frequency[cardFromDeck]++;
                if (contor1 == numberOfCards) {
                    i = numberOfCards;
                    break;
                }
                cardFromDeck = goThroughCards.next();
            }
            if (contor2 != numberOfUniqueCards) {
                uniqueCard = goThroughUniqueCards.next();
                contor2++;
            }
        }
    }

    /*Creates the sack with cards to playe this round*/
    public void createSack() {
        this.cardsFrequency();
        if (onlyIllegal()) {
            int appelId = 0;
            int amount = 1;
            int theMost = theMostValueableIllegalCard(this.sack);
            this.sackDeclared.put(appelId, amount);
            this.actualSackDeclared.put(theMost, amount);
            this.usedCards.put(theMost, amount);

        } else if (!onlyIllegal()) {
            Map<Integer, Integer> priorityCards = getAllMax(this.frequency);
            if (priorityCards.size() == 1) {
                for (Map.Entry<Integer, Integer> entry : priorityCards.entrySet()) {
                    this.sackDeclared.put(entry.getKey(), entry.getValue());
                    this.actualSackDeclared.put(entry.getKey(), entry.getValue());
                    this.usedCards.put(entry.getKey(), entry.getValue());
                }
            }
            if (priorityCards.size() > 1) {
                Map<Integer, Integer> cardsToBeDeclared = theMostValuableSetOfCards(priorityCards);
                Map.Entry<Integer, Integer> entry = cardsToBeDeclared.entrySet().iterator().next();
                this.sackDeclared = new HashMap<>(cardsToBeDeclared);
                this.actualSackDeclared = new HashMap<>(cardsToBeDeclared);
                Integer toAdd = new Integer(entry.getKey());
                this.usedCards.put(toAdd, entry.getValue());
            }
        }
    }

    /**/
    public int theMostValueableIllegalCard(final ArrayList<Integer> sack) {
        if (sack.contains(SILK_ID)) {
            return SILK_ID;
        }
        if (sack.contains(PEPPER_ID)) {
            return PEPPER_ID;
        }
        if (sack.contains(BARREL_ID)) {
            return BARREL_ID;
        }
        return 0;
    }

    /**/
    public boolean onlyIllegal() {
        int counter = 0;
        for (int i = 0; i < this.sack.size(); i++) {
            if (this.sack.get(i) == SILK_ID
                    || this.sack.get(i) == PEPPER_ID
                    || this.sack.get(i) == BARREL_ID) {
                counter++;
            }
        }
        if (counter == this.sack.size()) {
            return true;
        }
        return false;
    }

    public static Map<Integer, Integer> getAllMax(final int[] inputArray) {

        int maxValue = -1;
        int index = 0;
        HashMap<Integer, Integer> priorityCards = new HashMap<Integer, Integer>();
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] > maxValue) {
                maxValue = inputArray[i];
                index = i;

            }
            if (inputArray[i] == maxValue) {
                priorityCards.put(i, inputArray[i]);
            }

        }
        priorityCards.put(index, maxValue);
        return priorityCards;
    }

    /**/
    public Map<Integer, Integer> theMostValuableSetOfCards(final Map<Integer, Integer> cards) {
        int max = -1;
        int cardId = -1;
        Map<Integer, Integer> cardSet = new HashMap<>();
        LegalGoods asset = new LegalGoods();
        for (Map.Entry<Integer, Integer> resourceCard : cards.entrySet()) {
            int number = new Integer(resourceCard.getValue());
            if (resourceCard.getKey() == 0) {
                asset.getApple();
            } else if (resourceCard.getKey() == CHEESE_ID) {
                asset.getCheese();
            } else if (resourceCard.getKey() == BREAD_ID) {
                asset.getBread();
            } else if (resourceCard.getKey() == CHICKEN_ID) {
                asset.getChicken();

            } else {
                continue;
            }
            int profit = number * asset.getProfit();
            if (profit == max) {
                if (resourceCard.getKey() == BREAD_ID || resourceCard.getKey() == CHICKEN_ID) {
                    if (cardId == BREAD_ID || cardId == CHICKEN_ID) {
                        int firstCard = findFirstAparition();
                        cardId = resourceCard.getKey();
                        cardSet.clear();
                        max = profit;
                        cardSet.put(firstCard, resourceCard.getValue());

                    }
                }
            }

            if (profit > max) {
                cardId = resourceCard.getKey();
                cardSet.clear();
                max = profit;
                cardSet.put(resourceCard.getKey(), resourceCard.getValue());
            }

        }
        return cardSet;
    }

    /*Finds the numbers of aparition in clone*/
    public int howMany(final Integer toCompare, final ArrayList<Integer> clone) {
        Integer howMany = new Integer(0);
        for (int i = 0; i < clone.size(); i++) {
            if (toCompare.equals(clone.get(i))) {
                howMany++;
            }
        }
        return howMany;
    }

    /*Finds the first aparition just for cchicken or bread */
    public int findFirstAparition() {
        for (int i = 0; i < this.getSack().size(); i++) {
            if (getSack().get(i) == BREAD_ID) {
                return BREAD_ID;
            }
            if (getSack().get(i) == CHICKEN_ID) {
                return CHICKEN_ID;
            }
        }
        return -1;
    }

}
