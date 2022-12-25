package WitchHunt.Module.Cards.IdentityCards;

public class IdentityCard {
    Identity identity;

    /**
     * @param identity type enum "witch" ou "villager"
     */
    public IdentityCard(Identity identity) {
        this.identity = identity;
    }

    /**
     * @return retourner l'identity
     */
    public Identity getIdentity() {
        return this.identity;
    }

    /**
     * @param identity changer l'identity
     */
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

}
