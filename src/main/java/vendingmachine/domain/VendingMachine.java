package vendingmachine.domain;

import java.util.EnumMap;
import java.util.List;
import vendingmachine.exception.VendingMachineException;

public class VendingMachine {
    private final Coins coins;
    private final List<Product> products;

    public VendingMachine(Coins coins, List<Product> products) {
        this.coins = coins;
        this.products = products;
    }

    public void place() {
        VendingMachineValidator.validate(products);
    }

    public VendingMachineStatus calculateStatus(final int amount) {
        if (isAllProductOutOfStock()) {
            return VendingMachineStatus.CLOSE;
        }
        if (!canBuySomething(amount)) {
            return VendingMachineStatus.CLOSE;
        }
        return VendingMachineStatus.OPEN;
    }

    private boolean canBuySomething(int amount) {
        int minPrice = calculateRemainMinPrice();
        if (amount < minPrice) {
            return false;
        }
        return true;
    }

    private boolean isAllProductOutOfStock() {
        return products.stream()
                .allMatch(Product::isEmpty);
    }

    private int calculateRemainMinPrice() {
        return products.stream()
                .filter(product -> !product.isEmpty())
                .mapToInt(Product::getPrice)
                .min()
                .orElse(0);
    }

    /**
     * @throw: NON_EXIST_PRODUCT 자판기에 등록되지 않은 상품 주문
     * @throw: OUT_OF_STOCK_PRODUCT 자판기에 등록은 되어 있지만, 재고가 없는 상품 주문
     * @throw: 재고는 있지만, 남은 금액보다 비싼 상품 주문
     */
    public int sell(final Product targetProduct, final int amount) {
        if (targetProduct.isEmpty()) {
            throw VendingMachineException.OUT_OF_STOCK_PRODUCT.make();
        }

        if (targetProduct.isGreaterThanPrice(amount)) {
            throw VendingMachineException.EXPENSIVE_PRODUCT.make();
        }

        targetProduct.minusCount();
        return amount - targetProduct.getPrice();
    }

    /**
     * @throw: NON_EXIST_PRODUCT 자판기에 등록되지 않은 상품
     */
    public Product getProduct(final String buyProductName) {
        Product product = Product.of(buyProductName);

        return products.stream()
                .filter(eachProduct -> eachProduct.equals(product))
                .findFirst()
                .orElseThrow(VendingMachineException.NON_EXIST_PRODUCT::make);
    }

    public EnumMap<Coin, Integer> changeCoins(int amount) {
        return coins.calculateChanges(amount);
    }

    @Override
    public String toString() {
        return "VendingMachine{" +
                "coinCounts=" + coins +
                ", products=" + products +
                '}';
    }
}
