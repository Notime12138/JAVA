package WitchHunt.Module.Players;

import java.util.ArrayList;
import java.util.Random;

/**
 * Ai plus agressive
 */
public class AIStrategeyAttack implements AIStrategy {

    /**
     * @param vp AI lui même
     * @return L'action est sous forme de String, plus probable d'accuser
     */
    @Override
    public String nextAction(VirtuelPlayer vp) {
        if (vp.getUsableCardsH().isEmpty()) {
            return "Accuser";
        }
        int choice = new Random().nextInt(4);
        if (choice == 0 || choice == 1) {
            return "Reveal";
        } else {
            return "Accuser";
        }
    }

    /**
     * @param vp AI lui même
     * @return Choisir un joueur, plus probable de choisir le joueur human
     */
    @Override
    public Player getPlayerToAccuse(VirtuelPlayer vp) {
        ArrayList<Player> players = vp.getAvailableAccused();
        Player toAccuse = players.get(0);
        int nbCard = 5;
        for (Player p : players) {
            if (p.getUsableCardsW().size() < nbCard) {
                nbCard = p.getUsableCardsW().size();
                toAccuse = p;
            }
        }
        return toAccuse;
    }
}
