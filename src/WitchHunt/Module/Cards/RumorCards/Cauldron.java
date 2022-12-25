package WitchHunt.Module.Cards.RumorCards;

import WitchHunt.Module.Cards.IdentityCards.Identity;
import WitchHunt.Module.Players.Player;

import java.util.Random;

public class Cauldron extends RumorCard {
    private final String name = "Cauldron";

    public Player takeEffectHunt(Player p1, Player ob){
        System.out.println(p1.getName() + " uses Cauldron-hunt ");
        p1.revealIdentity();
        int a=p1.getAvailablePlayers().indexOf(p1);
        if (p1.getIdentity().getIdentity() == Identity.Witch){
            System.out.println("The left player takes the turn");
            if (a == p1.getAvailablePlayers().size()-1){
                return p1.getAvailablePlayers().get(0);
            }else {
                return p1.getAvailablePlayers().get(a+1);
            }
        }else {
            Player nextPlayer = ob;
            System.out.println("Choose " + nextPlayer.getName());
            return nextPlayer;
        }
    }

    public Player takeEffectWitch(Player p1,Player accuser){
        int n = new Random().nextInt(accuser.getUsableCards().size());
        System.out.println(p1.getName() + " uses Cauldron-Witch?");
        System.out.println(accuser.getName() + " discards "+accuser.getCardsList().get(n).getName());
        accuser.discardCard(accuser.getCardsList().get(n));
        return p1;
    }

    public String getName(){
        return this.name;
    }

}
