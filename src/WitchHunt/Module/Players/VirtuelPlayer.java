package WitchHunt.Module.Players;

import WitchHunt.Module.Cards.IdentityCards.Identity;
import WitchHunt.Module.Cards.RumorCards.RumorCard;
import WitchHunt.Module.Game;

import java.util.*;

public class VirtuelPlayer extends Player {
    private AIStrategy strategy;
    private int s;

    /**
     * @param nb_card Le nombre de cartes que chaque joueur devrait avoir
     * @param name nom des joueurs
     * @param game le jeu dans lequel se trouve le joueur
     * choisir un strategy par fonctions Random()
     */
    public VirtuelPlayer(int nb_card, String name, Game game) {
        super(nb_card, name, game);
        int s = new Random().nextInt(2);
        if (s == 0) {
            this.strategy = new AIStrategyDefence();
            this.s = 0;
        } else {
            this.strategy = new AIStrategeyAttack();
            this.s = 1;
        }
    }

    /**
     * @return is a bot
     */
    @Override
    public boolean isBot() {
        return true;
    }

    /**
     * choisir identity par un fonction Random()
     */
    @Override
    public void chooseIdentity() {
        Random random = new Random();
        int ch = random.nextInt(2);
        if ((ch == 0)) {
            this.setIdentity(Identity.Witch);
        } else {
            this.setIdentity(Identity.Villager);
        }
        System.out.println(this.getIdentity().getIdentity());
    }

    @Override
    public Player defendAccusation(Player accuser) {
        ArrayList<RumorCard> usable = this.getUsableCardsW();

        for (RumorCard card : usable) {
            System.out.println(usable.indexOf(card)+1 + " " + card.getName());
        }

        Player nextPlayer;
        if (usable.isEmpty()) {
            nextPlayer = this.nextPlayerRevealIdentity(accuser);
        } else {
            if (this.getIdentity().getIdentity() == Identity.Witch || this.s == 0) {
                int ch_card = new Random().nextInt(usable.size());
                RumorCard card = usable.get(ch_card);
                nextPlayer = card.takeEffectWitch(this, accuser);
                this.revealCard(card);
            } else {
                nextPlayer = this.nextPlayerRevealIdentity(accuser);
            }
        }
        if (nextPlayer instanceof VirtuelPlayer) {
            nextPlayer.play();
        } else {
        }

        return nextPlayer;
    }

    /**
     * @return prochain joueur à jouer
     * Utiliser les décisions renvoyées dans AIStrategy
     */
    @Override
    public Player play() {
        Player nextPlayer;
        ArrayList<RumorCard> usable = this.getUsableCardsH();
        String choice = this.strategy.nextAction(this);
        if (choice.equals("Accuser")) {
            Player toAccuse = this.strategy.getPlayerToAccuse(this);
            System.out.println(this.getName() + " accuse " + toAccuse.getName());
            nextPlayer = this.accuse(toAccuse);
        } else {
            int cardIndex = new Random().nextInt(usable.size());
            RumorCard card = usable.get(cardIndex);
            this.revealCard(card);
            System.out.println(this.getName() + " used " + card.getName() + " to hunt");

            Player ob = this.selectPlayer();

            nextPlayer = card.takeEffectHunt(this, ob);
        }
        if (nextPlayer instanceof VirtuelPlayer) {
            nextPlayer.play();
        } else {
            System.out.println("Turn de Jouer");
        }

        return nextPlayer;
    }

    @Override
    public int chooseCase() {
        int i = new Random().nextInt(2);
        return i+1 ;
    }

    @Override
    public Player selectPlayer() {
        ArrayList<Player> available = this.getAvailablePlayers();
        int a = available.indexOf(this);
        int choice = new Random().nextInt(available.size());
        while (choice == a) {
            choice = new Random().nextInt(available.size());
        }
        return available.get(choice);
    }


}
