package WitchHunt.Module.Cards.RumorCards;

import WitchHunt.Module.Players.HumanPlayer;
import WitchHunt.Module.Players.Player;

import java.util.Random;

public class DuckingStool extends RumorCard {
    private final String name = "Ducking Stool";

    public Player takeEffectHunt(Player p1,Player ob){
        Player p2 = ob;
        if (p2 instanceof HumanPlayer) {
            // a new window
            return null;
        } else {
            int choice = p2.chooseCase();
            if (choice == 1) {
                System.out.println(p2.getName() + " reveals his identity ");
                p2.revealIdentity();
                if (p2.isWitch()) {
                    p1.addPoint(1);
                    return p1;
                } else {
                    p1.addPoint(-1);
                    return p2;
                }
            } else {
                int n = new Random().nextInt(p2.getUsableCards().size());
                System.out.println(p2.getName() + " discards " + p2.getUsableCards().get(n).getName());
                p2.discardCard(p2.getUsableCards().get(n));
                return p2;
            }
        }

    }

    public Player takeEffectWitch(Player p1,Player accuser){
        System.out.println(p1.getName() + " uses DuckingStool-Witch?");
        return p1.selectPlayer();
    }

    public String getName(){
        return this.name;
    }

}
