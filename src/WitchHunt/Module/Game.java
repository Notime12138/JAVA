package WitchHunt.Module;

import WitchHunt.Module.Cards.RumorCards.*;
import WitchHunt.Module.Players.HumanPlayer;
import WitchHunt.Module.Players.Player;
import WitchHunt.Module.Players.VirtuelPlayer;
import WitchHunt.Vue.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private final int numPlayer;
    private static Game instance = null;
    private final ArrayList<Player> playerList;
    private final ArrayList<RumorCard> cardList;
    private Player nextPlayer;
    private final int cardPerPlayer;
    private MainFrame mf;
    private JTextPane jtp;

    /**
     * @param nb_players nb du joueurs robots contient
     */
    private Game(int nb_players) {
        this.numPlayer = nb_players;
        this.cardPerPlayer = getCardPerPlayer(nb_players);
        this.playerList = createPlayers(nb_players,this.cardPerPlayer);
        this.nextPlayer = this.playerList.get((int)(1+Math.random()*(nb_players)));
        this.cardList = createCards();
    }

    /**
     * @param nb_players nb du joueurs robots contient
     * @param i Le personnage choisi par le joueur pour commencer le jeu
     */
    public Game(int nb_players,int i) {
        this.numPlayer = nb_players;
        this.cardPerPlayer = getCardPerPlayer(nb_players);
        this.playerList = createPlayers(nb_players,this.cardPerPlayer);
        this.nextPlayer = this.playerList.get(i);
        this.cardList = createCards();
    }

    /**
     * @param nb nb du joueurs
     * @return game
     * singleton
     */
    public static Game getInstance(int nb) {
        if (instance == null) {
            instance = new Game(nb);
        }return instance;
    }

    /**
     * @return game
     */
    public static Game getInstance() {
        return instance;
    }


    /**
     * @param nb_player nb du joueurs
     * @return Le nombre de cartes que tout le monde devrait avoir
     */
    public int getCardPerPlayer(int nb_player) {
        int nb_card;
        if (nb_player == 6) {
            nb_card = 2;
        } else {
            nb_card = 7 - nb_player;
        }
        return nb_card;
    }

    /**
     * @param nb_players nb du joueurs
     * @param nb_card Le nombre de cartes que tout le monde devrait avoir
     * @return Liste du joueurs
     */
    private ArrayList<Player> createPlayers(int nb_players, int nb_card) {
        final ArrayList<Player> newPlayers = new ArrayList<>(nb_players);
        newPlayers.add(new HumanPlayer(nb_card, "Joueur", this));
        for (int i = 0; i < nb_players - 1; i++) {
            newPlayers.add(new VirtuelPlayer(nb_card, "BOT" + i, this));
        }return newPlayers;
    }

    /**
     * @return Liste des cartes
     */
    private ArrayList<RumorCard> createCards() {
        final ArrayList<RumorCard> cards = new ArrayList<>(13);
        Collections.addAll(cards,
                new AngryMob(),
                new TheInquisition(),
                new PointedHat(),
                new HookedNose(),
                new BroomStick(),
                new Wart(),
                new DuckingStool(),
                new Cauldron(),
                new EvilEye(),
                new Toad(),
                new BlackCat(),
                new PetNewt());
        Collections.shuffle(cards);
        return cards;
    }

    /**
     * Distribuer des cartes aux joueurs
     */
    private void donnerCards() {
        System.out.println(this.cardList.size());
        System.out.println(this.playerList.size() + " players are playing");
        for (Player p : this.playerList) {
           for (int i = 0; i < this.cardPerPlayer; ++i) {
                p.getCard(this.cardList.get(0));
                this.cardList.remove(0);
            }
        }
        this.cardList.trimToSize();

        if (this.cardList.isEmpty()) {
            System.out.println("No cards remain");
        } else {
            for (RumorCard card : this.cardList) {
                System.out.println("remains these cards" + card.getName());
            }
        }
    }

    /**
     * @param card carte jetée
     */
    public void getDiscardedCard(RumorCard card) {
        this.cardList.add(card);
    }

    /**
     * @return Le jeu est-il fini
     */
    public boolean isGameEnded() {
        for (Player p : this.playerList) {
            if (p.getPoint() >= 5) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return Le joueur actuel avec le score le plus élevé
     */
    public Player getHighest() {
        Player playerH = playerList.get(0);
        for (Player p : playerList) {
            if (p.getPoint() > playerH.getPoint()) {
                playerH = p;
            }
        }
        return playerH;
    }

    /**
     * @return Liste des joueurs
     */
    public ArrayList<Player> getPlayerList() {
        return this.playerList;
    }

    /**
     * @return Habituellement utilisé comme un liste des cartes jetées
     */
    public ArrayList<RumorCard> getCardList() {
        return this.cardList;
    }

    public void startGame() {
        while (! isGameEnded()) {
            this.donnerCards();
            for (Player p : playerList) {
                //test if all cards have been distributed
                System.out.println("\n" + p.getName() + " has these cards\n");
                for (RumorCard card : p.getCardsList()) {
                    System.out.println(p.getCardsList().indexOf(card)+1 + " " + card.getName());
                }
                //every player choose their identity
                p.chooseIdentity();
            }
            this.startRound();
        }
        System.out.println("Le gagnant est : " + this.getHighest() );
    }

    /**
     * @param mf mainfraime used to visualiser
     */
    public void startGame_G(MainFrame mf) {
        this.mf = mf;
            this.donnerCards();
            for (Player p : playerList) {
                if (p instanceof VirtuelPlayer) {
                    //every bot choose their identity
                    p.chooseIdentity();
                }
            }
        while (nextPlayer instanceof VirtuelPlayer) {
            this.startRound();
        }
        if (isGameEnded()) {
            System.out.println("Le gagnant est : " + this.getHighest());
        }
    }

    /**
     * commencer un nouveau cycle
     */
    public void startRound() {
        while (! this.isRoundEnded()) {
            this.startTurn();
        }
        System.out.println("Fin du round");
        System.out.println(this.cardList.size());
        this.endRound();
    }

    /**
     * @return Déterminer si le tour est terminé ou non
     */
    public boolean isRoundEnded() {
        int nbRevealed = 0;
        for (Player p : playerList) {
            if (p.isIdentityRevealed()) {
                nbRevealed++;
            }
        }
        return nbRevealed == this.numPlayer - 1;
    }

    /**
     * Commencer le tour du joueur
     */
    public void startTurn() {
        System.out.println("C'est le turn de " + nextPlayer.getName());
        ArrayList<RumorCard> usable = nextPlayer.getUsableCards();
        //check the cards at the beginning of their turn
        for (RumorCard card : usable) {
            System.out.println(usable.indexOf(card)+1 + " " + card.getName());
        }
        nextPlayer = nextPlayer.play();
    }

    /**
     * À la fin du tour, tous les joueurs doivent défausser les cartes et remettre les cartes à la liste des cartes
     */
    public void endRound() {
        for (Player p : playerList) {
            p.discardAllCards();
            p.setStatusIdentity(false);
            System.out.println(p.getName() + "'s score : " + p.getPoint());
        }
    }

    public MainFrame getMf() {
        return mf;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public void setJtp(JTextPane jtp) {
        this.jtp = jtp;
    }

}
