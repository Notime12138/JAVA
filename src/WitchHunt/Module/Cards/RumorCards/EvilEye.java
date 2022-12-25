package WitchHunt.Module.Cards.RumorCards;

import WitchHunt.Module.Players.Player;

public class EvilEye extends RumorCard {
    private final String name = "Evil Eye";

    public Player takeEffectHunt(Player p1, Player ob){
        Player nextPlayer = ob;
        System.out.println(p1.getName() + " uses EvilEye-hunt to choose " + nextPlayer.getName());
        return nextPlayer;
    }

    public Player takeEffectWitch(Player p1,Player accuser){
        Player nextPlayer = p1.selectPlayer();
        System.out.println(p1.getName() + " uses EvilEye-witch? to choose " + nextPlayer.getName());
        return nextPlayer;
    }

    public String getName(){
        return this.name;
    }

}
