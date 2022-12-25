package WitchHunt.Module.Cards.RumorCards;

import WitchHunt.Module.Players.Player;

import java.util.Random;

public class HookedNose extends RumorCard {
    private final String name = "Hooked Nose";

    public Player takeEffectHunt(Player p1, Player ob){
        Player nextplayer = ob;
        Random ra = new Random();
        int n = ra.nextInt(nextplayer.getUsableCards().size());
        p1.getCardsList().add(nextplayer.getUsableCards().get(n));
        System.out.println(p1.getName() + " uses HookedNose-hunt");
        System.out.println(p1.getName() + " chooses "+nextplayer.getName());
        System.out.println(p1.getName() + " takes "+nextplayer.getUsableCards().get(n).getName());
        nextplayer.getCardsList().remove(n);
        return nextplayer;
    }

    public Player takeEffectWitch(Player p1,Player accuser){
        Random rd = new Random();
        int n = rd.nextInt(accuser.getUsableCards().size());
        p1.getUsableCards().add(accuser.getUsableCards().get(n));
        accuser.getUsableCards().get(n).setStatus(false);
        System.out.println(p1.getName() + " uses HookedNose-Witch?");
        System.out.println(p1.getName() + " takes " + accuser.getUsableCards().get(n).getName() + " from " + accuser.getName());
        accuser.getUsableCards().remove(n);
        return p1;
    }

    public String getName(){
        return this.name;
    }

}
