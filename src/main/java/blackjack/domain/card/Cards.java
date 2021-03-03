package blackjack.domain.card;

import blackjack.exception.CardDuplicateException;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;
    private int index = 0;

    public Cards(final List<Card> cards) {
        duplicateValidate(cards);

        this.cards = cards;
    }

    private void duplicateValidate(List<Card> cards) {
        if (cards.size() != cards.stream().distinct().count()) {
            throw new CardDuplicateException();
        }
    }

    public Card next() {
        return cards.get(index++);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

}
