package vendingmachine.domain;

import java.util.EnumMap;
import java.util.List;

public class VendingMachine {

    private final EnumMap<Coin, Integer> coinCounts;
    private final List<Product> products;

    public VendingMachine(EnumMap<Coin, Integer> coinCounts, List<Product> products) {
        this.coinCounts = coinCounts;
        this.products = products;
    }

    public void place() {
        VendingMachineValidator.validate(products);
    }


    @Override
    public String toString() {
        return "VendingMachine{" +
                "coinCounts=" + coinCounts +
                ", products=" + products +
                '}';
    }
}
