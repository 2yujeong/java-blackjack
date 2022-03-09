package blackjack;

public class Dealer {
    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    Result judge(Cards cards) {
        return this.cards.score().judge(cards.score());
    }
}
