package WitchHunt.Module.Cards.RumorCards;

import WitchHunt.Module.Players.Player;

public class Wart extends RumorCard {
    private final String name = "Wart";

    public Player takeEffectWitch(Player p1,Player accuser){
        System.out.println(p1.getName() + " uses Wart-Witch?");
        return p1;
    }

    public Player takeEffectHunt(Player p1, Player ob){
        System.out.println(p1.getName() + " uses Wart-hunt");
        return ob;
    }

    public String getName(){
        return this.name;
    }

}
