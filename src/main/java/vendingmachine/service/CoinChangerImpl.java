package vendingmachine.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.EnumMap;
import java.util.List;
import vendingmachine.domain.Coin;
import vendingmachine.exception.VendingMachineException;

/**
 * To Reviewer:
 * 1. Coin 에서 CoinChangerImpl의 대부분의 로직을 위임할 수 있을 것 같음
 * 2. Coin 데이터와 잔돈 변환 로직이 산개되어 있는 느낌이 듬
 */
public class CoinChangerImpl implements CoinChanger {
    @Override
    public EnumMap<Coin, Integer> change(int amount) {
        validateChangeable(amount);
        return changeBy(amount);
    }

    private void validateChangeable(int amount) {
        int minAmount = Coin.calculateMinAmount();
        if(!canChange(amount, minAmount)) {
            throw VendingMachineException.INVALID_AMOUNT.make();
        }
    }

    private boolean canChange(int amount, int minAmount) {
        return amount >= minAmount && amount % minAmount == 0;
    }

    private EnumMap<Coin, Integer> changeBy(int amount) {
        EnumMap<Coin, Integer> changeCounts = initChangeCount();
        while(amount != 0) {
            int pickCoinAmount = pickCoinAmount();
            if(canInsert(amount, pickCoinAmount)) {
                amount -= pickCoinAmount;
                Coin coin = Coin.getCoin(pickCoinAmount);
                changeCounts.put(coin, changeCounts.getOrDefault(coin, 0) + 1);
            }
        }
        return changeCounts;
    }

    private EnumMap<Coin, Integer> initChangeCount() {
        EnumMap<Coin, Integer> changeCounts = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            changeCounts.put(coin, 0);
        }
        return changeCounts;
    }

    private boolean canInsert(int amount, int coin) {
        return amount >= coin;
    }

    private int pickCoinAmount() {
        return Randoms.pickNumberInList(List.of(
                Coin.COIN_500.getAmount(),
                Coin.COIN_100.getAmount(),
                Coin.COIN_50.getAmount(),
                Coin.COIN_10.getAmount()));
    }
}
