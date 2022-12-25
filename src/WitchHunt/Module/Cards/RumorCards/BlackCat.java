package WitchHunt.Module.Cards.RumorCards;

import WitchHunt.Module.Players.Player;


public class BlackCat extends RumorCard {
    private final String name = "Black Cat";

    public Player takeEffectWitch(Player p1,Player accuser){
        System.out.println(p1.getName() + " uses BlackCat-witch?");
        return p1;
    }

    public Player takeEffectHunt(Player p1, Player ob) {
        p1.getCardsList().add(p1.getGame().getCardList().get(0));
        System.out.println(p1.getName() + " uses BlackCat-hunt");
        System.out.println(p1.getName() + " adds " + p1.getGame().getCardList().get(0).getName());
        p1.discardCard(this);
        return p1;
    }

    public String getName(){
        return this.name;
    }

    public boolean playableHunt(Player p1){
        return ! p1.getGame().getCardList().isEmpty();
    }

}
