package player;

import assets.GetAsset;
import assets.IllegalGoods;
import mechanics.EliminateAndSearch;
import mechanics.GiveCards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class Player extends GiveCards {
    public static final int GOLD = 50;
    protected ArrayList<Integer> confiscated;
    private String comerciantOrSerif;
    private String strategy;
    private int gold;
    private HashMap<Integer, Integer> myCards;
    private int bribe;
    protected int dontuse;

    public Player() {

    }

    public Player(final String comerciantOrSerif, final String strategy) {
        this.comerciantOrSerif = comerciantOrSerif;
        this.strategy = strategy;
        gold = GOLD;
        myCards = new HashMap<Integer, Integer>();
        confiscated = new ArrayList<Integer>();
        dontuse = 0;
    }

    /*Get the confiscated cards*/
    public ArrayList<Integer> getConfiscated() {
        return confiscated;
    }

    /*Set confiscated cards*/
    public void setConfiscated(final ArrayList<Integer> confiscated) {
        this.confiscated = confiscated;
    }

    /*Get the Bribe for the serif*/
    public int getBribe() {
        return bribe;
    }

    /*Set the Bribe for the serif*/
    public void setBribe(final int bribe) {
        this.bribe = bribe;
    }

    /*Get the Cards(save from evry round)*/
    public HashMap<Integer, Integer> getMyCards() {
        return myCards;
    }

    /*Tells us if a Player is a comerciant or a serif*/
    public String getComerciantOrSerif() {
        return comerciantOrSerif;
    }

    /*Set a player as a serfi or a player*/
    public void setComerciantOrSerif(final String comerciantOrSerif) {
        this.comerciantOrSerif = comerciantOrSerif;
    }

    /*Tells us the strategy*/
    public String getStrategy() {
        return strategy;
    }

    /*Set Strategy*/
    public void setStrategy(final String strategy) {
        this.strategy = strategy;
    }

    /*Get the gold of player*/
    public int getGold() {
        return gold;
    }

    /*Set the gold of a player*/
    public void setGold(final int gold) {
        this.gold = gold;
    }

    /*If a player is a serif, he MUST check the sack of the others players ;)*/
    public void checkSack(final HashMap<Integer, Integer> actualSackDeclared, final HashMap<Integer,
            Integer> sackDeclared, final Player player) {

        Map.Entry<Integer, Integer> actual = actualSackDeclared.entrySet().iterator().next();
        Map.Entry<Integer, Integer> inSack;
        if (!sackDeclared.isEmpty()) {
            inSack = sackDeclared.entrySet().iterator().next();
        }
        if (player.getBribe() > 0 && getStrategy().equals("greedy")) {
            addInMyCards(actualSackDeclared, player);
            int theBribe = player.getBribe();
            setGold(getGold() + theBribe);
            return;
        }
        Set<Integer> toCheck = player.makeDiff(actualSackDeclared, sackDeclared);
        List<Integer> toIterateCardsUndeclared = new ArrayList<Integer>();
        if (toCheck.isEmpty()) {
            addInMyCards(actualSackDeclared, player);
            GetAsset asset = new GetAsset();
            IllegalGoods assets = new IllegalGoods();
            assets = asset.cardType(actual.getKey());
            player.setGold(player.getGold() + assets.getPenalty() * actual.getValue());
            gold -= assets.getPenalty() * actual.getValue();

        } else {
            toIterateCardsUndeclared.addAll(toCheck);
            Integer common = common(actualSackDeclared, sackDeclared);
            if (common != null) {
                EliminateAndSearch toSearch = new EliminateAndSearch();
                int commonValue = toSearch.searchCardValue(actualSackDeclared, common);
                Map<Integer, Integer> toAddInHisCards = new HashMap<Integer, Integer>();
                toAddInHisCards.put(common, commonValue);
                addInMyCards(toAddInHisCards, player);
            }

            for (int i = 0; i < toIterateCardsUndeclared.size(); i++) {
                EliminateAndSearch toSearch = new EliminateAndSearch();
                Integer key = toSearch.searchCard(actualSackDeclared,
                        toIterateCardsUndeclared.get(i));
                Integer value = toSearch.searchCardValue(actualSackDeclared,
                        toIterateCardsUndeclared.get(i));
                GetAsset card = new GetAsset();
                int goldPlayer = player.getGold();
                gold += card.cardType(key).getPenalty() * value;
                goldPlayer -= ((GetAsset) card).cardType(key).getPenalty() * value;
                player.setGold(goldPlayer);
                confiscated.add(key);
            }
        }
        if (player.getBribe() > 0) {
            player.setGold(player.getGold() + player.getBribe());
            player.setBribe(0);
        }
    }

    /*difference between 2 sets*/
    public Set<Integer> makeDiff(final HashMap<Integer, Integer> actual, final HashMap<Integer,
            Integer> declared) {
        Set<Integer> sources = new HashSet<>(actual.keySet());
        Set<Integer> targets = new HashSet<>(declared.keySet());
        sources.removeAll(targets);
        return sources;
    }

    /*intersection of 2 sets*/
    public Integer common(final HashMap<Integer, Integer> actual,
                          final HashMap<Integer, Integer> declared) {
        Integer[] toRet = new Integer[1];
        Set<Integer> sources = new HashSet<>(actual.keySet());
        Set<Integer> targets = new HashSet<>(declared.keySet());
        Set<Integer> mutual = (Set<Integer>) ((HashSet<Integer>) sources).clone();
        mutual.retainAll(targets);
        for (Integer i : mutual) {
            toRet[0] = i;
        }
        return toRet[0];
    }

    /*add in cards in players */
    public void addInMyCards(final Map<Integer, Integer> actualSackDeclared, final Player player) {

        Map<Integer, Integer> clone = new HashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> resourceCard : actualSackDeclared.entrySet()) {

            Integer resCard = new Integer(resourceCard.getKey());
            Integer howMuch = new Integer(resourceCard.getValue());

            if (player.getMyCards().isEmpty()) {
                player.getMyCards().put(resCard, howMuch);

            } else {
                if (clone.isEmpty()) {
                    clone.put(resCard, howMuch);

                }
                HashMap<Integer, Integer> helper = player.getMyCards();
                for (Map.Entry<Integer, Integer> savedCard : player.getMyCards().entrySet()) {
                    if (savedCard.getKey().equals(resCard)) {
                        Integer newToAdd = new Integer(savedCard.getValue());
                        newToAdd += howMuch;
                        clone.put(resCard, newToAdd);
                    } else {
                        EliminateAndSearch toSearch = new EliminateAndSearch();
                        int toCheck = toSearch.searchCard(player.getMyCards(), resCard);
                        if (toCheck == -1) {
                            clone.put(resCard, howMuch);
                        }
                    }


                }
            }
        }
        player.myCards.putAll(clone);

    }

    /*make the partition in quick sort*/
    public int partition(final Player[] players, final int low, final int high) {
        int pivot = players[high].getGold();
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            if (players[j].getGold() >= pivot) {
                i++;
                int temp = players[i].getGold();
                players[i].setGold(players[j].getGold());
                players[j].setGold(temp);
                String playerStrategy = players[i].getStrategy();
                players[i].setStrategy(players[j].getStrategy());
                players[j].setStrategy(playerStrategy);

            }
        }

        int temp = players[i + 1].getGold();
        players[i + 1].setGold(players[high].getGold());
        players[high].setGold(temp);
        String playerStrategy = players[i + 1].getStrategy();
        players[i + 1].setStrategy(players[high].getStrategy());
        players[high].setStrategy(playerStrategy);
        return i + 1;
    }

    /*Sort the players afte the score*/
    public void sort(final Player[] players, final int low, final int high) {
        if (low < high) {
            int pi = partition(players, low, high);
            sort(players, low, pi - 1);
            sort(players, pi + 1, high);
        }
    }


}
