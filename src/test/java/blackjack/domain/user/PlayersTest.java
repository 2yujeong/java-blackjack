package blackjack.domain.user;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    private Players players;

    @BeforeEach
    public void setUp() {
        players = new Players(Arrays.asList("amazzi", "dani", "pobi"));
    }

    @DisplayName("이름별로 참여자들을 생성한다.")
    @Test
    void createPlayers() {
        assertThat(players).isInstanceOf(Players.class);
    }

    @DisplayName("각 플레이어에게 초기에 카드 두장을 배분한다.")
    @Test
    void DistributeToEachPlayer() {
        Deck deck = new Deck();
        players.distributeToPlayer(deck);

        assertThat(players.getPlayers()
                .stream()
                .allMatch(user -> user.cards.getCards().size() == 2)).isTrue();
    }

    @DisplayName("각 플레이어의 모든 카드를 보여준다.")
    @Test
    void showCardsByPlayers() {
        Deck deck = new Deck();
        players.distributeToPlayer(deck);
        List<Cards> cardsGroup = players.showCardsByPlayers();

        assertThat(cardsGroup.stream()
                .allMatch(cards -> cards.getCards().size() == 2)).isTrue();
    }

    @DisplayName("플레이어 이름들을 확인한다.")
    @Test
    void getNames() {
        List<String> namesGroup = players.getNames();

        assertThat(namesGroup).isEqualTo(Arrays.asList("amazzi", "dani", "pobi"));
    }

    @DisplayName("Players 일급 컬렉션을 반환한다.")
    @Test
    void getPlayers() {
        assertThat(players.getPlayers()).hasSize(3);
    }
}
