package blackjack.domain.player;

import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractPlayer implements Player {
    protected static final int ACE_DIFF = 10;
    protected static final int BLACKJACK = 21;

    private final List<Card> cards;
    private final Name name;

    public AbstractPlayer() {
        this("anonymous");
    }

    public AbstractPlayer(String name) {
        cards = new ArrayList<>();
        this.name = new Name(name);
    }

    @Override
    public void drawCard(final Card card) {
        cards.add(card);
    }

    @Override
    public boolean isCanDraw() {
        return false;
    }

    @Override
    public int getValue() {
        int lowValue = getLowValue();
        int highValue = getHighValue(lowValue);
        if (highValue > BLACKJACK) {
            return lowValue;
        }
        return highValue;
    }

    private int getLowValue() {
        return cards.stream()
            .mapToInt(card->card.getCardNumber().getValue())
            .sum();
    }

    private int getHighValue(int lowValue) {
        int highValue = lowValue;
        if (cards.stream().anyMatch(card -> card.getCardNumber() == CardNumber.ACE)) {
            highValue += ACE_DIFF;
        }
        return highValue;
    }

    public ResultType getResult(Dealer dealer) {
        int userValue = getValue();
        int dealerValue = dealer.getValue();
        if (userValue > BLACKJACK || userValue < dealerValue) {
            return ResultType.LOSS;
        }
        if (userValue == dealerValue) {
            return ResultType.DRAW;
        }
        return ResultType.WIN;
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
