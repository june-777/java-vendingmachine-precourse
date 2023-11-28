package vendingmachine.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import vendingmachine.exception.VendingMachineException;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private static final Map<Integer, Coin> cachedCoin = new HashMap<>();

    static {
        for (Coin coin : values()) {
            cachedCoin.put(coin.amount, coin);
        }
    }
    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public static Coin getCoin(int amount) {
        return Optional.ofNullable(cachedCoin.get(amount))
                .orElseThrow(VendingMachineException.INVALID_AMOUNT::make);
    }

    public static int calculateMinAmount() {
        return Arrays.stream(values())
                .mapToInt(coin -> coin.amount)
                .min()
                .orElseThrow();
    }
}
