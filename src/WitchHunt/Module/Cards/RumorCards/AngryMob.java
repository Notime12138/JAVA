package WitchHunt.Module.Cards.RumorCards;

import WitchHunt.Module.Cards.IdentityCards.Identity;
import WitchHunt.Module.Players.Player;

public class AngryMob extends RumorCard {
    private final String name = "Angry Mob";



    public Player takeEffectWitch(Player p1,Player accuser){
        System.out.println(p1.getName() + " uses Angry Mob-Witch?");
        return p1;
    }

    public Player takeEffectHunt(Player p1, Player ob){
        Player nextPlayer = ob;
        nextPlayer.revealIdentity();
        Identity id = nextPlayer.getIdentity().getIdentity();
        System.out.println(p1.getName() + " uses Angry Mob-hunt");
        System.out.println(p1.getName() + " reveals "+nextPlayer.getName());
        System.out.println(nextPlayer.getName() + " identity is:"+id);
        if (id == Identity.Witch){
            p1.addPoint(2);
            return p1;
        }else {
            p1.addPoint(-2);
            return nextPlayer;
        }
    }

    public String getName(){
        return this.name;
    }

    public boolean playableHunt(Player p1){
        return p1.isIdentityRevealed() && p1.getIdentity().getIdentity() == Identity.Villager;
    }

}
