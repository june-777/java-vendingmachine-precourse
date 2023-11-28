package vendingmachine.domain;

import java.util.EnumMap;

public class Coins {
    private final EnumMap<Coin, Integer> coinCounts;

    public Coins(EnumMap<Coin, Integer> coinCounts) {
        this.coinCounts = coinCounts;
    }

    public EnumMap<Coin, Integer> calculateChanges(int amount) {
        EnumMap<Coin, Integer> changeAndCount = new EnumMap<>(Coin.class);
        for (Coin coin : coinCounts.keySet()) {

            while (canChange(coin, amount)) {

                int coinCount = coinCounts.get(coin);
                int coinAmount = coin.getAmount();
                int changeCount = calculateChangeCount(amount, coinAmount, coinCount);
                int changeAmount = calculateChangeAmount(coinAmount, changeCount);

                amount = updateAmount(amount, changeAmount);
                coinCount = updateCoinCount(coinCount, changeCount);
                updateChanges(changeAndCount, coin, coinCount, changeCount);
            }
        }
        return changeAndCount;
    }

    private boolean canChange(Coin coin, int amount) {
        int coinCount = coinCounts.get(coin);
        int coinAmount = coin.getAmount();
        return isRemainCoin(coinCount) && amount > 0 && amount >= coinAmount;
    }

    private boolean isRemainCoin(int coinCount) {
        return coinCount > 0;
    }

    private void updateChanges(EnumMap<Coin, Integer> remainCoinCounts,
                               Coin coin, int coinCount, int changeCount
    ) {
        coinCounts.put(coin, coinCount);
        remainCoinCounts.put(coin, changeCount);
    }

    private static int updateAmount(int amount, int changeAmount) {
        amount -= changeAmount;
        return amount;
    }

    private static int updateCoinCount(int coinCount, int changeCount) {
        coinCount -= changeCount;
        if (coinCount < 0) {
            coinCount = 0;
        }
        return coinCount;
    }

    private int calculateChangeAmount(int coinAmount, int changeCount) {
        return changeCount * coinAmount;
    }

    private int calculateChangeCount(int amount, int coinAmount, int coinCount) {
        int changeCount = amount / coinAmount;
        if (changeCount > coinCount) {
            changeCount = coinCount;
        }
        return changeCount;
    }
}
