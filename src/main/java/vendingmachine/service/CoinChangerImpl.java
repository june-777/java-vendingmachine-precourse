package vendingmachine.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.EnumMap;
import java.util.List;
import vendingmachine.domain.Coin;
import vendingmachine.exception.VenderException;

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
            throw VenderException.INVALID_AMOUNT.make();
        }
    }

    private boolean canChange(int amount, int minAmount) {
        return amount >= minAmount && amount % minAmount == 0;
    }
}
