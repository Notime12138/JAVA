package WitchHunt.Module.Cards.RumorCards;

import WitchHunt.Module.Players.Player;

public abstract class RumorCard {
    private String name;
    private boolean status;


    /**
     * @param p1 titulaire de la carte
     * @param p2 l'objet de l'effet
     * @return rôle de la prochaine action
     */
    public abstract Player takeEffectHunt(Player p1,Player p2);

    /**
     * @param p1 titulaire de la carte
     * @param accuser accuser
     * @return rôle de la prochaine action
     */
    public abstract Player takeEffectWitch(Player p1,Player accuser);

    /**
     * @return La carte a-t-elle été révélée
     */
    public boolean isRevealed() {
        return status;
    }

    /**
     * @param status Changer l'état d'une carte, généralement pour une utilisation ou un nouveau tour
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return le nom de la carte
     */
    public String getName(){
        return this.name;
    }

    /**
     * @param p titulaire de la carte
     * @return Est-il possible d'utiliser cette carte
     */
    public boolean playableHunt(Player p) {
        return true;
    }

    /**
     * @param p titulaire de la carte
     * @return Est-il possible d'utiliser cette carte
     */
    public boolean playableWitch(Player p) {
        return true;
    }


}
