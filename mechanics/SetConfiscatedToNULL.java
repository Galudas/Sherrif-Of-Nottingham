package mechanics;

import player.Player;

import java.util.ArrayList;

public class SetConfiscatedToNULL extends Player {
    public SetConfiscatedToNULL() {

    }

    public SetConfiscatedToNULL(final Player[] players) {
        for (int i = 0; i < players.length; i++) {
            players[i].setConfiscated(new ArrayList<Integer>());
        }
    }
}
