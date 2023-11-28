package vendingmachine.domain;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import vendingmachine.exception.VendingMachineException;

public class VendingMachine {

    private final EnumMap<Coin, Integer> coinCounts;
    private final List<Product> products;

    public VendingMachine(EnumMap<Coin, Integer> coinCounts, List<Product> products) {
        this.coinCounts = coinCounts;
        this.products = products;
        validate();
    }

    private void validate() {
        HashSet<Product> uniqueProducts = new HashSet<>(products);

        if (products.size() != uniqueProducts.size()) {
            throw VendingMachineException.DUPLICATE_PRODUCT.make();
        }
    }

    @Override
    public String toString() {
        return "VendingMachine{" +
                "coinCounts=" + coinCounts +
                ", products=" + products +
                '}';
    }
}
