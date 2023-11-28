package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import org.junit.jupiter.api.Test;

class CoinsTest {

    @Test
    void change() {
        EnumMap<Coin, Integer> coinCounts = new EnumMap<>(Coin.class);
        coinCounts.put(Coin.COIN_500, 3);
        coinCounts.put(Coin.COIN_100, 2);
        coinCounts.put(Coin.COIN_50, 1);
        coinCounts.put(Coin.COIN_10, 1);
        Coins coins = new Coins(coinCounts);

        EnumMap<Coin, Integer> changes = coins.calculateChanges(1170);
        assertThat(changes)
                .containsEntry(Coin.COIN_500, 2)
                .containsEntry(Coin.COIN_100, 1)
                .containsEntry(Coin.COIN_50, 1)
                .containsEntry(Coin.COIN_10, 1);
    }
}