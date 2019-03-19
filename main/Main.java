package main;

import player.Player;
import mechanics.PlayTheCards;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public final class Main {

    private Main() {
    }

    private static final class GameInputLoader {
        private final String mInputPath;

        private GameInputLoader(final String path) {
            mInputPath = path;
        }

        public GameInput load() {
            List<Integer> assetsIds = new ArrayList<>();
            List<String> playerOrder = new ArrayList<>();

            try {
                BufferedReader inStream = new BufferedReader(new FileReader(mInputPath));
                String assetIdsLine = inStream.readLine().replaceAll("[\\[\\] ']", "");
                String playerOrderLine = inStream.readLine().replaceAll("[\\[\\] ']", "");

                for (String strAssetId : assetIdsLine.split(",")) {
                    assetsIds.add(Integer.parseInt(strAssetId));
                }

                for (String strPlayer : playerOrderLine.split(",")) {
                    playerOrder.add(strPlayer);
                }
                inStream.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return new GameInput(assetsIds, playerOrder);
        }
    }

    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0]);
        GameInput gameInput = gameInputLoader.load();
        final int numberOfAssets = 4;
        final Integer[] kingBonus = {20, 15, 15, 10};
        final Integer[] queenBonus = {10, 10, 10, 5};
        PlayTheCards play = new PlayTheCards();
        Player[] players = play.playTheCards(gameInput);
        final int numberOfPlayers = players.length;
        int toAdd = 0;
        int itIsQueenTime = 0;
        int nrMax = 0;
        for (int row = 0; row < numberOfAssets; row++) {
            List<Integer> listOfResource = Arrays.asList(play.getTable()[row]);
            int firstMax = Collections.max(listOfResource);
            int index = listOfResource.indexOf(firstMax);
            players[index].setGold(players[index].getGold() + kingBonus[row]);
            listOfResource.set(index, -1);
            int nextmax = Collections.max(listOfResource);
            while (nextmax != -1) {
                if (nextmax == firstMax && itIsQueenTime == 0) {
                    toAdd = kingBonus[row];
                } else {
                    itIsQueenTime = 1;
                    if (nextmax < firstMax) {
                        nrMax++;
                    }
                }
                if (nrMax == 2) {
                    break;
                }
                if (itIsQueenTime == 1) {
                    toAdd = queenBonus[row];
                    firstMax = nextmax;
                }
                index = listOfResource.indexOf(firstMax);
                players[index].setGold(players[index].getGold() + toAdd);
                listOfResource.set(index, -1);
                nextmax = Collections.max(listOfResource);

            }
            nrMax = 0;
            itIsQueenTime = 0;
        }


        Player toSort = new Player();
        toSort.sort(players, 0, numberOfPlayers - 1);
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println(toUpperCase(players[i].getStrategy()) + ": " + players[i].getGold());
        }
    }
}



