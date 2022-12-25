package WitchHunt.Module.Players;

import WitchHunt.Module.Cards.IdentityCards.Identity;
import WitchHunt.Module.Cards.RumorCards.RumorCard;
import WitchHunt.Module.Game;

import java.util.*;

/**
 * utilisateur, joueur humain
 */
public class HumanPlayer extends Player {


    public HumanPlayer(int nb_card, String name, Game game) {
        super(nb_card, name, game);
    }

    /**
     * @return pas un bot
     */
    @Override
    public boolean isBot() {
        return false;
    }

    /**
     * choisir identity en console
     */
    @Override
    public void chooseIdentity() {
        System.out.println(this.getName() + "! Choisissez votre Idenetité :\n" +
                "1 : Witch\n" +
                "2 : Villager");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (Objects.equals(choice, "1") || Objects.equals(choice, "Witch") || Objects.equals(choice, "witch") || Objects.equals(choice, "w") || Objects.equals(choice, "W")) {
            this.setIdentity(Identity.Witch);
        } else {
            this.setIdentity((Identity.Villager));
        }
    }

    /**
     * @param accuser accuser
     * @return next player
     * call MainFrame to use sur vue
     */
    @Override
    public Player defendAccusation(Player accuser) {
        System.out.println("Use card to defend Accuse");
        this.getGame().getMf().setAccuser(accuser);
        return null;
    }

    @Override
    public Player play() {
        Player nextPlayer;
        ArrayList<RumorCard> usable = this.getUsableCardsH();

        if (usable.isEmpty()) {
            System.out.println("Sorry, you dont have any card, you can only accuse");
        }

        System.out.println("Vous voulez: \n" +
                "1 accuser\n" +
                "2 hunt");

        Scanner scanner1 = new Scanner(System.in);
        int choice = scanner1.nextInt();

        System.out.println("You can use these cards");
        for (RumorCard card : usable) {
            System.out.println(usable.indexOf(card) + 1 + " " + card.getName());
        }

        if (choice == 1 || usable.size() == 0) {
            ArrayList<Player> available = this.getAvailableAccused();
            for (Player p : available) {
                System.out.println(available.indexOf(p)+1 + " " + p.getName());
            }
            System.out.println("Entrez l'index du joueur que vous souhaitez accuser");

            Scanner scannerP = new Scanner(System.in);
            int indexP = scannerP.nextInt();

            Player toAccuse = available.get(indexP - 1);
            nextPlayer = this.accuse(toAccuse);
        } else {
            for (RumorCard card : usable) {
                System.out.println(usable.indexOf(card)+1 + " " + card.getName());
            }
            System.out.println("Entrez l'index de la carte que vous souhaitez utiliser");
            Scanner scannerC = new Scanner(System.in);
            int indexC = scannerC.nextInt();

            RumorCard cardU = usable.get(indexC - 1);
            cardU.setStatus(true);

            nextPlayer = cardU.takeEffectHunt(this,this);
        }
        return nextPlayer;
    }

    @Override
    public int chooseCase() {
        System.out.println("Vous voulez :\n" +
                "1 reveal identity\n" +
                "2 discard a card\n" +
                "Entre the number(1 / 2)");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /**
     * @return null
     * call MainFrame
     */
    @Override
    public Player selectPlayer() {
        return null;
    }

}
