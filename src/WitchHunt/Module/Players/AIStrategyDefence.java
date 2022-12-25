package WitchHunt.Module.Players;

import java.util.ArrayList;
import java.util.Random;

/**
 * Ai qui est plus enclin à survivre et joue les cartes plus fréquemment
 */
public class AIStrategyDefence implements AIStrategy {

    /**
     * @param vp AI lui même
     * @return L'action est sous forme de String, plus probable de reveler une carte
     */
    @Override
    public String nextAction(VirtuelPlayer vp) {
        if (vp.getUsableCardsH().isEmpty()) {
            return "Accuser";
        }
        int choice = new Random().nextInt(7);
        if (choice == 0 || choice == 1) {
            return "Accuser";
        } else {
            return "Reveal";
        }
    }

    /**
     * @param vp AI lui même
     * @return Choisir un joueur, par un fonction de Random()
     */
    @Override
    public Player getPlayerToAccuse(VirtuelPlayer vp) {
        ArrayList<Player> players = vp.getAvailableAccused();
        int choice = new Random().nextInt(players.size());
        return players.get(choice);
    }
}
