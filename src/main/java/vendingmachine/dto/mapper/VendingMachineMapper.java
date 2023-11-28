package vendingmachine.dto.mapper;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Product;
import vendingmachine.domain.VendingMachine;
import vendingmachine.dto.request.ProductLineRequest;
import vendingmachine.dto.request.ProductRequest;

public class VendingMachineMapper {

    public VendingMachine of(final EnumMap<Coin, Integer> coinCounts,
                             final ProductRequest productRequest
    ) {
        List<Product> products = new ArrayList<>();
        for (ProductLineRequest productLineRequest : productRequest.getProducts()) {
            Product product = toProduct(productLineRequest);
            products.add(product);
        }

        return new VendingMachine(coinCounts, products);
    }

    private Product toProduct(final ProductLineRequest productLineRequest) {
        return new Product(
                productLineRequest.getName(),
                productLineRequest.getPrice(),
                productLineRequest.getCount());
    }
}
