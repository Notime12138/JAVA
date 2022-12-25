package WitchHunt.Module.Cards.RumorCards;

import WitchHunt.Module.Players.Player;

public class Toad extends RumorCard {
    private final String name = "Toad";

    public Player takeEffectHunt(Player p1, Player ob) {
        System.out.println(p1.getName() + " uses toad-hunt");
        p1.revealIdentity();
        if (p1.isWitch()){
            int a=p1.getAvailablePlayers().indexOf(p1);
            if (a==p1.getAvailablePlayers().size()){
                return p1.getAvailablePlayers().get(0);
            }else {
                return p1.getAvailablePlayers().get(a+1);
            }
        }else{
            return ob;
        }
    }

    public Player takeEffectWitch(Player p1,Player accuser){
        System.out.println(p1.getName() + " uses toad-witch?");
        return p1;
    }

    public String getName(){
        return this.name;
    }

}
