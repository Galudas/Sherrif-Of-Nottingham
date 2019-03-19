package mechanics;

import assets.GetAsset;
import assets.IllegalGoods;
import main.GameInput;
import player.Player;
import stategy.Strategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class PlayTheCards extends GameInput {

    private Integer[][] table;

    public PlayTheCards() {

    }
    /*This is a matrix for bonus*/
    public Integer[][] getTable() {
        return table;
    }
    /*This is a matrix for bonus*/
    public void setTable(final Integer[][] table) {
        this.table = table;
    }
    /*How A round should work till giving the points*/
    public Player[] playTheCards(final GameInput gameInput) {
        final int legalGoodsOver = 3;
        final int howManyChese = 3;
        final int howManyChickenOrBread = 2;
        final int silk = 10;
        final int legalGoods = 3;
        final int numberOfAssets = 4;
        List<Integer> fromInputAssets = gameInput.getAssetIds();
        Queue<Integer> assetsIds = new LinkedList<>(fromInputAssets);
        List<String> playerNames = gameInput.getPlayerNames();
        int numberOfPlayers = playerNames.size();
        Player[] players = new Player[numberOfPlayers];
        GiveCards cardsForPlayers = new GiveCards(numberOfPlayers);
        cardsForPlayers.giveFirstSetOfCards(assetsIds, numberOfPlayers, 0);
        int numberOfRounds = numberOfPlayers * 2;
        int counter = 0;
        int numberOfiterations = 0;
        int toCompare;
        int firstTime = 1;
        int greedyComerciant = 0;
        FIndSerif fIndSerif = new FIndSerif();
        for (int i = 0; i < numberOfRounds; i++) {
            toCompare = i;
            if (toCompare > numberOfPlayers - 1) {
                toCompare = toCompare - numberOfPlayers;
            }
            for (int j = 0; j < numberOfPlayers; j++) {
                if (toCompare == j) {
                    if (firstTime == 1) {
                        players[counter] = new Player("Serif", playerNames.get(j));
                        numberOfiterations++;
                        counter++;
                    } else {
                        players[counter].setComerciantOrSerif("Serif");
                        counter++;
                    }
                } else {
                    if (firstTime == 1) {
                        players[counter] = new Player("Comerciant", playerNames.get(j));
                        counter++;
                    } else {
                        players[counter].setComerciantOrSerif("Comerciant");
                        counter++;
                    }
                }


            }
            counter = 0;
            firstTime++;
            for (int j = 0; j < numberOfPlayers; j++) {
                int serifThisRound = fIndSerif.findSerif(players, numberOfPlayers);
                if (players[j].getComerciantOrSerif().equals("Comerciant")) {
                    if (players[j].getStrategy().equals("greedy")) {
                        greedyComerciant++;
                    }
                    ArrayList<Integer> cards = cardsForPlayers.getSetOfCards().get(j);
                    InitPlayer myPlayer = new InitPlayer() {
                    };
                    Strategy toPlay = myPlayer.initPlayers(players[j].getStrategy(), cards,
                            players[j],
                            greedyComerciant);
                    players[serifThisRound].checkSack(toPlay.getActualSackDeclared(),
                            toPlay.getSackDeclared(),
                            players[j]);
                    EliminateAndSearch todestroy = new EliminateAndSearch();
                    todestroy.eliminateUsedCards(toPlay.getSack(), toPlay.getUsedCards());
                    if (!players[j].getConfiscated().isEmpty()) {
                        int size1 = players[j].getConfiscated().size();
                        if (size1 > 0) {
                            for (int k = 0; k < size1; k++) {
                                todestroy.eliminateUsedCards(players[j].getConfiscated().get(k),
                                        cards, -1);
                            }

                        }


                    }
                    SetConfiscatedToNULL setConfiscatedToNULL = new SetConfiscatedToNULL(players);
                }
                cardsForPlayers.giveMoreCards(cardsForPlayers, assetsIds);
            }

        }
        setTable(new Integer[numberOfAssets][numberOfPlayers]);
        for (int row = 0; row < numberOfAssets; row++) {
            Arrays.fill(table[row], 0);
        }
        IllegalGoods good = new IllegalGoods();
        for (int l = 0; l < numberOfPlayers; l++) {
            int quantity = -1;
            Map<Integer, Integer> clone = new HashMap<Integer, Integer>();
            clone = (Map<Integer, Integer>) players[l].getMyCards().clone();
            for (Map.Entry<Integer, Integer> resource : clone.entrySet()) {


                if (resource.getKey() > legalGoods) {
                    if (resource.getKey() == silk) {
                        quantity = howManyChese;
                    } else {
                        quantity = howManyChickenOrBread;
                    }

                    GetAsset asset = new GetAsset();

                    good = asset.cardType(resource.getKey());
                    int illegalProfit = good.getProfit();
                    players[l].setGold(players[l].getGold() + illegalProfit * resource.getValue());
                    int withWhatToReplace = good.getBonus();
                    players[l].getMyCards().remove(resource.getKey());
                    Map<Integer, Integer> toAddInHisCards = new HashMap<Integer, Integer>();
                    toAddInHisCards.put(withWhatToReplace, resource.getValue() * quantity);
                    players[l].addInMyCards(toAddInHisCards, players[l]);

                }
                if (resource.getKey() > legalGoodsOver) {
                    EliminateAndSearch toSearch = new EliminateAndSearch();
                    int quantityIllegal = toSearch.searchCardValue(players[l].getMyCards(),
                            good.getBonus());
                    table[good.getBonus()][l] = quantityIllegal;
                } else {
                    table[resource.getKey()][l] = resource.getValue();
                }

            }
            ScoreCalculator toCalculate = new ScoreCalculator();
            int score = toCalculate.scoreCalculator(players[l]);
            players[l].setGold(players[l].getGold() + score);
        }
        return players;
    }


}
