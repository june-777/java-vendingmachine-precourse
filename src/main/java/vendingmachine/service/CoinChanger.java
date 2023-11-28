package vendingmachine.service;

import java.util.EnumMap;
import vendingmachine.domain.Coin;

public interface CoinChanger {

    EnumMap<Coin, Integer> change(int amount);
}
