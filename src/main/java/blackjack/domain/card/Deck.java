package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final LinkedList<Card> deck;

    public Deck() {
        deck = Card.getCachingCards();
        Collections.shuffle(deck);
    }

    public Card draw() {
        if (deck.isEmpty()) {
            throw new IndexOutOfBoundsException("deck에 남아있는 카드가 존재하지 않습니다.");
        }
        return deck.poll();
    }
}
