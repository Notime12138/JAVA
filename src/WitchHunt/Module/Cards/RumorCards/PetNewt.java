package WitchHunt.Module.Cards.RumorCards;

import WitchHunt.Module.Players.Player;

public class PetNewt extends RumorCard {
    private final String name = "Pet Newt";

    public Player takeEffectHunt(Player p1, Player ob){
        System.out.println(p1.getName() + " uses PetNewt-hunt");
        System.out.println("Choose a player to take his revealed card");
        Player p2 = ob;
        RumorCard rc= ob.getRevealedCards().get(0);
        p1.getCardsList().add(rc);
        rc.setStatus(false);
        System.out.println(p1.getName() + " takes " + rc.getName());
        ob.discardCard(rc);
        Player nextPlayer = ob;
        System.out.println("Choose " + nextPlayer.getName());
        return nextPlayer;
    }

    public Player takeEffectWitch(Player p1,Player accuser){
        System.out.println(p1.getName() + " uses PetNewt-witch?");
        return p1;
    }

    public String getName(){
        return this.name;
    }

    public boolean playableHunt(Player p1) {
        boolean flag=false;
        for (Player p : p1.getAvailablePlayers()) {
            if (!p.getRevealedCards().isEmpty()) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}

