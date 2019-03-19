package mechanics;

import player.Player;

public class FIndSerif extends Player {
    public FIndSerif() {
        super();
    }

    /*It finds the serif from an array of players*/
    public int findSerif(final Player[] players, final int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            if (players[i].getComerciantOrSerif().equals("Serif")) {
                return i;
            }
        }
        return -1;
    }
}
