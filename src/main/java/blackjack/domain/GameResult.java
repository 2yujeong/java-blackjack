package blackjack.domain;

import blackjack.domain.player.Player;

import static blackjack.domain.Status.BURST;

public enum GameResult {
    WIN("승"),
    LOSE("패");

    private final String message;

    GameResult(String message) {
        this.message = message;
    }

    public static GameResult calculate(Player dealer, Player gamer) {
        if (isOnlyDealerBurst(dealer, gamer) || hasPlayerHigherScoreThanDealer(dealer, gamer)) {
            return WIN;
        }

        return LOSE;
    }

    private static boolean isOnlyDealerBurst(Player dealer, Player gamer) {
        return dealer.getStatus() == BURST &&
            gamer.getStatus() != BURST;
    }

    private static boolean hasPlayerHigherScoreThanDealer(Player dealer, Player gamer) {
        return dealer.getStatus() != BURST &&
            gamer.getStatus() != BURST &&
            dealer.getScore() < gamer.getScore();
    }

    public GameResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        return WIN;
    }

    public String getMessage() {
        return message;
    }
}
