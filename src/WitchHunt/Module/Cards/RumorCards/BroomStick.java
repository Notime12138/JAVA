package WitchHunt.Module.Cards.RumorCards;

import WitchHunt.Module.Players.Player;

public class BroomStick extends RumorCard {
    private final String name = "Broom Stick";

    public Player takeEffectWitch(Player p1,Player accuser){
        System.out.println(p1.getName() + " uses BroomStick-Witch? to take next turn");
        return p1;
    }

    public Player takeEffectHunt(Player p1, Player ob){
        System.out.println(p1.getName() + " uses BroomStick-hunt");
        return ob;
    }

    public String getName(){
        return this.name;
    }

}
