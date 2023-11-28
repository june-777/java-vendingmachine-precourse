package vendingmachine.service;

import java.util.EnumMap;
import vendingmachine.domain.Coin;
import vendingmachine.domain.VendingMachine;
import vendingmachine.dto.mapper.VendingMachineMapper;
import vendingmachine.dto.request.ProductRequest;

public class VendingService {

    private final CoinChanger coinChanger;
    private final VendingMachineMapper vendingMachineMapper;

    public VendingService(CoinChanger coinChanger, VendingMachineMapper vendingMachineMapper) {
        this.coinChanger = coinChanger;
        this.vendingMachineMapper = vendingMachineMapper;
    }

    public EnumMap<Coin, Integer> changeToCoin(final int amount) {
        return coinChanger.change(amount);
    }

    public VendingMachine createVendingMachine(final EnumMap<Coin, Integer> coinCounts,
                                               final ProductRequest productRequest
    ) {
        VendingMachine vendingMachine = vendingMachineMapper.of(coinCounts, productRequest);
        vendingMachine.place();

        return vendingMachine;
    }

}