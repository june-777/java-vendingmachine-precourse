package vendingmachine.dto.mapper;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Coins;
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
        Coins coins = new Coins(coinCounts);
        return new VendingMachine(coins, products);
    }

    private Product toProduct(final ProductLineRequest productLineRequest) {
        return Product.from(
                productLineRequest.getName(),
                productLineRequest.getPrice(),
                productLineRequest.getCount());
    }
}
