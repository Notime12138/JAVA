package WitchHunt.Module.Players;

import WitchHunt.Module.Cards.IdentityCards.Identity;
import WitchHunt.Module.Cards.IdentityCards.IdentityCard;
import WitchHunt.Module.Cards.RumorCards.RumorCard;
import WitchHunt.Module.Game;

import java.util.ArrayList;

public abstract class Player {
    private int choice = 1;
    private final IdentityCard identity;
    private boolean statusIdentity;
    private int point;
    private final ArrayList<RumorCard> cardsList;
    private String name;
    Game game;

    /**
     * @param nb_card Le nombre de cartes que chaque joueur devrait avoir
     * @param name nom des joueurs
     * @param game le jeu dans lequel se trouve le joueur
     */
    public Player(int nb_card, String name, Game game) {
        this.name = name;
        this.point = 0;
        this.cardsList = new ArrayList<>(nb_card);
        this.identity = new IdentityCard(Identity.Witch);
        this.game = game;
        this.statusIdentity = false;
    }

    /**
     * @return Utilisé pour déterminer s'il s'agit d'un joueur AI
     */
    public abstract boolean isBot();

    /**
     * choisir sa propre identité
     */
    public abstract void chooseIdentity();

    /**
     * @return jouez son propre tour principal
     */
    public abstract Player play();

    /**
     * @return Sélectionnez le joueur comme objet de l'effet
     */
    public abstract Player selectPlayer();

    /**
     * @return A cause des effets de carte, on peut choisir différents comportements
     */
    public abstract int chooseCase();

    /**
     * @param accuser accuser
     * @return prochain joueur à jouer
     * Cette fonction est appelée lorsqu'on est chargée
     */
    public abstract Player defendAccusation(Player accuser);

    /**
     * @return list of players which can be accused(identity unknown)
     */
    public ArrayList<Player> getAvailableAccused() {
        ArrayList<Player> available = new ArrayList<>();
        for (Player p : this.game.getPlayerList()) {
            if (! p.statusIdentity) {
                available.add(p);
            }
        }
        available.remove(this);
        return available;
    }

    /**
     * @return list of players which is still alive(unknown or known as a villager
     */
    public ArrayList<Player> getAvailablePlayers() {
        ArrayList<Player> available = new ArrayList<>();
        for (Player p : this.game.getPlayerList()) {
            if (!p.statusIdentity || !p.isWitch()) {
                available.add(p);
            }
        }
        available.remove(this);
        return available;
    }

    /**
     * @param player objet d'accusation
     * @return prochain joueur à jouer
     */
    public Player accuse(Player player) {
        return player.defendAccusation(this);
    }

    /**
     * @return Déterminez s'il s'agit d'une sorcière
     */
    public boolean isWitch() {
        return this.identity.getIdentity() == Identity.Witch;
    }

    /**
     * @param point obtenir des points
     */
    public void addPoint(int point) {
        this.point += point;
    }

    /**
     * @param card Piocher des cartes du paquet
     */
    public void getCard(RumorCard card) {
        this.cardsList.add(card);
    }

    /**
     * révéler carte d'identité
     */
    public void revealIdentity() {
        this.statusIdentity = true;
        System.out.println(this.getName() + " is a " + this.getIdentity().getIdentity());
    }

    /**
     * @param card the one need to be used
     */
    public void revealCard(RumorCard card) {
        if (!card.isRevealed()) {
            card.setStatus(true);
            //System.out.println(this.getName() + " is using card " + card.getName());
        }else {
            System.out.println("Vous avez deja utilise cette carte");
        }
    }

    /**
     * @param card the one need to be discarded
     *             remove the card from player's cards and add it into the discarded cards
     */
    public void discardCard(RumorCard card) {
        try {
            this.cardsList.remove(card);
            this.getGame().getDiscardedCard(card);
            System.out.println(this.getName() + " discard " + card.getName());
        } catch (Exception e) {
            System.out.println("Vous n'avez pas cette carte");
        }
    }

    /**
     * at the end of a round everyone need to discard all their cards
     */
    public void discardAllCards() {
        while (! this.cardsList.isEmpty()) {
            this.discardCard(this.cardsList.remove(0));
        }
    }

    /**
     * @return Liste des cartes en main disponibles
     */
    public ArrayList<RumorCard> getUsableCards() {
        ArrayList<RumorCard> usable = new ArrayList<>(this.cardsList.size());
        for (RumorCard card : this.cardsList) {
            if (! card.isRevealed()) {
                usable.add(card);
            }
        }
        return usable;
    }


    /**
     * @return Liste des cartes qui satisfont l'effet de Hunt
     */
    public ArrayList<RumorCard> getUsableCardsH() {
        ArrayList<RumorCard> usable = new ArrayList<>(this.cardsList.size());
        for (RumorCard card : this.cardsList) {
            if (! card.isRevealed() && card.playableHunt(this)) {
                usable.add(card);
            }
        }
        return usable;
    }

    /**
     * @return Liste des cartes qui satisfont l'effet de Witch
     */
    public ArrayList<RumorCard> getUsableCardsW() {
        ArrayList<RumorCard> usable = new ArrayList<>(this.cardsList.size());
        for (RumorCard card : this.cardsList) {
            if (! card.isRevealed() && card.playableWitch(this)) {
                usable.add(card);
            }
        }
        return usable;
    }

    /**
     * @return Liste des cartes utilisées
     */
    public ArrayList<RumorCard> getRevealedCards() {
        ArrayList<RumorCard> revealed = new ArrayList<>(this.cardsList.size());
        for (RumorCard card : this.cardsList) {
            if (card.isRevealed()) {
                revealed.add(card);
            }
        }
        return revealed;
    }

    /**
     * @param accuser accuser
     * @return Prochain joueur à jouer
     */
    public Player nextPlayerRevealIdentity(Player accuser) {
        this.revealIdentity();

        if (this.getGame().isRoundEnded()) {
            this.getGame().endRound();
        }

        if (this.getGame().isGameEnded()) {
            System.out.println("Game ended");
            System.out.println("Winner : " + this.getGame().getHighest());
        }

        if (this.isWitch()) {
            accuser.addPoint(1);
            return accuser;
        } else {
            return this;
        }
    }

    /**
     * @return identity de joueur
     */
    public IdentityCard getIdentity() {
        return identity;
    }

    /**
     * @param identity choisir identity
     */
    public void setIdentity(Identity identity) {
        this.identity.setIdentity(identity);
    }

    /**
     * @return Déterminer si l'identité a été révélée
     */
    public boolean isIdentityRevealed() {
        return statusIdentity;
    }

    /**
     * @param status Modifier le statut d'identité du joueur
     */
    public void setStatusIdentity(boolean status) {
        this.statusIdentity = status;
    }

    /**
     * @return point du joueur
     */
    public int getPoint() {
        return point;
    }

    /**
     * @return Liste des mains du joueur
     */
    public ArrayList<RumorCard> getCardsList() {
        return cardsList;
    }

    /**
     * @return nom du joueur
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Renommer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return le jeu dans lequel se trouve le joueur
     */
    public Game getGame() {
        return this.game;
    }
}
