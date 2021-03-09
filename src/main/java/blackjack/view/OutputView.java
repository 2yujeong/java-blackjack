package blackjack.view;

import blackjack.domain.card.Cards;
import blackjack.domain.game.WinningResult;
import blackjack.domain.game.WinOrLose;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Gamblers;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static final String RESULT_INFORMATION = "%s카드: %s - 결과 %s" + System.lineSeparator();

    private OutputView() {
    }

    public static void printInitialCards(final Dealer dealer, final Gamblers gamblers) {
        StringBuilder sb = new StringBuilder();
        sb.append(dealer.getName() + "와");
        for (Gambler gambler : gamblers) {
            sb.append(gambler.getName() + ",");
        }
        sb.append("에게 2장의 카드를 나누었습니다");

        printPlayerCardsInformation(dealer);
        for (Player player : gamblers) {
            printPlayerCardsInformation(player);
        }
    }

    public static void printPlayerCardsInformation(final Player player) {
        printMessageByFormat("%s카드: %s",
                player.getName().getValue(), makeCardInfo(player.getCards()));
        printLineSeparator();
    }

    private static String makeCardInfo(final Cards cards) {
        return cards.cards().stream()
                .map(card -> card.getDenomination().denomination() + card.getSuit().suit())
                .collect(Collectors.joining(", "));
    }

    public static void informDealerReceived() {
        printMessage("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(final WinningResult winningResult) {
        printCardsAndScore(winningResult);
        printFinalWinningResult(winningResult);
    }

    private static void printCardsAndScore(final WinningResult winningResult) {
        printDealerResult(winningResult);
        for (Player player : winningResult.getGamblerMap().keySet()) {
            printMessageByFormat(RESULT_INFORMATION, player.getName().getValue(), makeCardInfo(player.getCards()), player.getScore().getValue());
        }
    }

    private static void printDealerResult(final WinningResult winningResult) {
        Cards cards = winningResult.getDealerCards();
        String dealerCardInfo = makeCardInfo(cards);

        printMessageByFormat(RESULT_INFORMATION, "딜러", dealerCardInfo, cards.calculateScore().getValue());
    }

    private static void printFinalWinningResult(final WinningResult winningResult) {
        printDealerWinningResult(winningResult);
        printGamblerWinningResult(winningResult);
    }

    private static void printDealerWinningResult(WinningResult winningResult) {
        String printFormat = "%s : %s 승 %s 무 %s 패" + System.lineSeparator();

        OutputView.printMessageByFormat(
                printFormat, "딜러", winningResult.countDealerWin(), winningResult.countDealerDraw(), winningResult.countDealerLose()
        );
    }

    private static void printGamblerWinningResult(WinningResult winningResult) {
        Map<Player, WinOrLose> winningTable = winningResult.getGamblerMap();
        for (Player player : winningTable.keySet()) {
            OutputView.printMessage(player.getName().getValue() + " : " + winningTable.get(player).getSymbol());
        }
    }

    public static void printLineSeparator() {
        System.out.print(System.lineSeparator());
    }

    public static void printMessage(final Object message) {
        System.out.println(message);
    }

    public static void printMessageByFormat(final String format, final Object... message) {
        System.out.printf(format, message);
    }


}
