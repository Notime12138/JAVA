package WitchHunt.Module.Cards.RumorCards;

import WitchHunt.Module.Cards.IdentityCards.Identity;
import WitchHunt.Module.Players.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class TheInquisition extends RumorCard {
    private final String name = "The Inquisition";

    public Player takeEffectWitch(Player p1,Player accuser){
        if (p1.isBot()){
            p1.discardCard(p1.getUsableCards().get(0));
            return p1;
        }else {
            System.out.println("choose a card to discard");
            ArrayList<RumorCard> usable = p1.getUsableCards();
            for (RumorCard card : usable) {
                System.out.println(usable.indexOf(card) + " " + card.getName());
            }
            Scanner scanner = new Scanner(System.in);
            int p = scanner.nextInt();
            p1.discardCard(p1.getUsableCards().get(p));
            System.out.println(p1.getName() + " uses The Inquisition-Witch?");
            System.out.println(p1.getName() + " discards "+p1.getUsableCards().get(p).getName());
            return p1;
        }

    }

    public Player takeEffectHunt(Player p1, Player ob) {
        Player nextplayer = ob;
        Identity id = nextplayer.getIdentity().getIdentity();
        if (p1.isBot()) {
                System.out.println(p1.getName() + " uses The Inquisition-hunt");
                System.out.println(p1.getName() + " chooses "+nextplayer.getName());
                return p1.selectPlayer();
        } else {
            System.out.println(p1.getName() + " uses The Inquisition-hunt");
            System.out.println(p1.getName() + " chooses "+nextplayer.getName());
            System.out.println("his identity is: " + id);
            return p1.selectPlayer();
        }
    }

        public String getName () {
            return this.name;
        }

        public boolean playableHunt (Player p1){
            if (p1.isIdentityRevealed() && p1.getIdentity().getIdentity() == Identity.Villager) {
                return true;
            } else {
                return false;
            }
        }

}
