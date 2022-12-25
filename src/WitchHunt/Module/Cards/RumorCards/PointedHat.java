package WitchHunt.Module.Cards.RumorCards;

import WitchHunt.Module.Players.Player;

import java.util.ArrayList;


public class PointedHat extends RumorCard {
    private final String name = "Pointed Hat";

    public Player takeEffectHunt(Player p1, Player ob){
        ArrayList<RumorCard> rc = p1.getRevealedCards();
        p1.getCardsList().add(rc.get(0));
        rc.get(0).setStatus(false);
        System.out.println(p1.getName() + " uses Pointed Hat-Hunt");
        System.out.println(p1.getName() + " take "+rc.get(0).getName());
        return ob;
    }

    public Player takeEffectWitch(Player p1,Player accuser){
        ArrayList<RumorCard> rc = p1.getRevealedCards();
        rc.add(p1.getRevealedCards().get(0));
        p1.getRevealedCards().get(0).setStatus(false);
        System.out.println(p1.getName() + " uses Pointed Hat-Witch?");
        System.out.println(p1.getName() + " take "+rc.get(0).getName());
        return p1;
    }

    public String getName(){
        return this.name;
    }

    public boolean playableHunt(Player p1){
        return !p1.getRevealedCards().isEmpty();
    }

    public boolean playableWitch(Player p1){
        return !p1.getRevealedCards().isEmpty();
    }

}
