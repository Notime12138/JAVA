package WitchHunt.Module.Players;

/**
 * Différentes stratégies que l'IA peut utiliser, divisées en plus agressives et plus défensives
 */
public interface AIStrategy {

    /**
     * @param vp AI lui même
     * @return L'action est sous forme de String
     */
    String nextAction(VirtuelPlayer vp);

    /**
     * @param vp AI lui même
     * @return Choisir un joueur
     */
    Player getPlayerToAccuse(VirtuelPlayer vp);
}
