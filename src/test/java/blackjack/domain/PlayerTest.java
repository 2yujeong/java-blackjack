package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    private static final List<Card> CARDS_SCORE_19 = Arrays.asList(
        new Card(Symbol.ACE, Shape.HEART),
        new Card(Symbol.KING, Shape.HEART),
        new Card(Symbol.EIGHT, Shape.HEART)
    );
    private static final List<Card> CARDS_SCORE_20 = Arrays.asList(
        new Card(Symbol.ACE, Shape.HEART),
        new Card(Symbol.KING, Shape.HEART),
        new Card(Symbol.NINE, Shape.HEART)
    );
    private static final List<Card> CARDS_SCORE_21 = Arrays.asList(
        new Card(Symbol.ACE, Shape.HEART),
        new Card(Symbol.KING, Shape.HEART),
        new Card(Symbol.TEN, Shape.HEART)
    );
    private static final List<Card> CARDS_SCORE_22 = Arrays.asList(
        new Card(Symbol.JACK, Shape.HEART),
        new Card(Symbol.TEN, Shape.HEART),
        new Card(Symbol.TWO, Shape.HEART)
    );

    static Stream<Arguments> generateData() {
        return Stream.of(
            Arguments.of(CARDS_SCORE_20, true),
            Arguments.of(CARDS_SCORE_21, false)
        );
    }

    @ParameterizedTest(name = "{displayName}")
    @DisplayName("ACE를 1로 했을 때 카드 합이 21 미만일 경우 true, 그 이상인 경우 false를 반환한다.")
    @MethodSource("generateData")
    public void isAbleToReceiveCard(List<Card> inputCards, boolean result) {
        Cards cards = new Cards(inputCards);
        Player player = new Player("jason");
        player.receiveCards(cards);
        assertThat(player.isAbleToReceiveCard()).isEqualTo(result);
    }

    @Test
    @DisplayName("둘 다 21을 초과하지 않을 경우, 플레이어가 딜러보다 점가 높아야 이긴다.")
    public void judgeResult_PlayerAndDealerScoreUnder21() {
        Player player = new Player("json");
        Dealer dealer = new Dealer();
        player.receiveCards(new Cards(CARDS_SCORE_20));
        dealer.receiveCards(new Cards(CARDS_SCORE_19));
        assertThat(player.judgeResult(dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("딜러가 21을 초과할 때 플레이어가 21을 초과하면 플레이어 패배")
    public void judgeResult_PlayerAndDealerScoreOver21() {
        Player player = new Player("json");
        Dealer dealer = new Dealer();
        player.receiveCards(new Cards(CARDS_SCORE_22));
        dealer.receiveCards(new Cards(CARDS_SCORE_22));
        assertThat(player.judgeResult(dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러가 21을 초과할 때 플레이어가 21을 초과하지 않으면 플레이어 승")
    public void judgeResult_DealerScoreOver21() {
        Player player = new Player("json");
        Dealer dealer = new Dealer();
        player.receiveCards(new Cards(CARDS_SCORE_19));
        dealer.receiveCards(new Cards(CARDS_SCORE_22));
        assertThat(player.judgeResult(dealer)).isEqualTo(Result.WIN);
    }
}
