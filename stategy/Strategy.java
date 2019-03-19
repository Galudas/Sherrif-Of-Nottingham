package stategy;

import player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface Strategy {

    HashMap<Integer, Integer> getSackDeclared();

    Map<Integer, Integer> getUsedCards();

    void setUsedCards(Map<Integer, Integer> usedCards);

    void setSackDeclared(HashMap<Integer, Integer> sackDeclared);

    HashMap<Integer, Integer> getActualSackDeclared();

    void setActualSackDeclared(HashMap<Integer, Integer> actualSackDeclared);

    ArrayList<Integer> getSack();

    void setSack(ArrayList<Integer> sack);

    void createSack(int greedyComerciant);

    void createSack();

    void createSack(Player player);
}
