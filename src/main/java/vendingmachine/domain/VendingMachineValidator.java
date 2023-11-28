package vendingmachine.domain;

import java.util.HashSet;
import java.util.List;
import vendingmachine.exception.VendingMachineException;

public class VendingMachineValidator {

    public static void validate(final List<Product> products) {
        validateDuplicate(products);
        validateProductCounts(products);
        validateProductPrice(products);
    }

    private static void validateDuplicate(final List<Product> products) {
        HashSet<Product> uniqueProducts = new HashSet<>(products);

        if (products.size() != uniqueProducts.size()) {
            throw VendingMachineException.DUPLICATE_PRODUCT.make();
        }
    }

    private static void validateProductCounts(final List<Product> products) {
        for (Product product : products) {
            if (product.isEmpty()) {
                throw VendingMachineException.INVALID_PRODUCT_COUNT.make();
            }
        }
    }

    private static void validateProductPrice(final List<Product> products) {
        for (Product product : products) {
            if (product.isLessThanPrice(100)) {
                throw VendingMachineException.INVALID_PRODUCT_MIN_PRICE.make();
            }
            if (!product.isDivideByPrice(10)) {
                throw VendingMachineException.INVALID_PRODUCT_PRICE_UNIT.make();
            }
        }
    }
}
