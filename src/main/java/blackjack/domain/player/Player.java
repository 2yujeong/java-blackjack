package blackjack.domain.player;

import blackjack.domain.Status;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.exception.CardDuplicateException;
import blackjack.util.ScoreCalculator;

import java.util.List;

public abstract class Player {

    private final Deck deck;
    private final String name;

    private final ScoreCalculator scoreCalculator;

    public Player(String name, ScoreCalculator scoreCalculator) {
        this.deck = new Deck();
        this.name = name;
        this.scoreCalculator = scoreCalculator;
    }

    public void addCardToDeck(Card card) {
        if (deck.contains(card)) {
            throw new CardDuplicateException();
        }

        deck.add(card);
    }

    public List<Card> getDeckAsList() {
        return deck.getCards();
    }

    public int getScore() {
        return scoreCalculator.apply(deck);
    }

    public Status getStatus() {
        return Status.evaluateScore(getScore());
    }

    public String getName() {
        return name;
    }

    public boolean isSameName(String name) {
        return getName().equals(name);
    }

    public abstract boolean isDrawable();

}
