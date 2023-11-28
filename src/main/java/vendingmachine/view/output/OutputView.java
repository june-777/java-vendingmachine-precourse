package vendingmachine.view.output;

import java.util.EnumMap;
import vendingmachine.domain.Coin;

public class OutputView {

    public void printVenderCoin(EnumMap<Coin, Integer> coinCounts) {
        System.out.println();
        System.out.println(Message.VENDER_COIN.message);

        // To Reviewer:
        // Enum 열거형 순서에 의존한 로직은 좋지 않다고 생각함
        for (Coin coin : coinCounts.keySet()) {
            System.out.printf(Message.VENDER_COIN_VIEW_FORM.message, coin.getAmount(), coinCounts.get(coin));
            System.out.println();
        }
    }

    private enum Message {

        VENDER_COIN("자판기가 보유한 동전"),
        VENDER_COIN_VIEW_FORM("%d원 - %d개");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
