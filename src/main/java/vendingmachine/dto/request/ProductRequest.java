package vendingmachine.dto.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductRequest {

    private final List<ProductLineRequest> products;

    public ProductRequest(List<ProductLineRequest> products) {
        this.products = products;
    }

    public static ProductRequest of(final String productsInput) {
        List<ProductLineRequest> products = new ArrayList<>();

        List<String> productsInfo = Arrays.asList(productsInput.split(";"));
        for (String productInfoForm : productsInfo) {

            String productInfo = removeSquareBrackets(productInfoForm);
            List<String> product = List.of(productInfo.split(","));

            ProductLineRequest productLineRequest = toProductLineRequest(product);
            products.add(productLineRequest);
        }
        return new ProductRequest(products);
    }

    private static String removeSquareBrackets(String element) {
        return element.substring(1, element.length() - 1);
    }

    private static ProductLineRequest toProductLineRequest(List<String> product) {
        return new ProductLineRequest(
                product.get(0),
                Integer.parseInt(product.get(1)),
                Integer.parseInt(product.get(2))
        );
    }

    public List<ProductLineRequest> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "products=" + products +
                '}';
    }
}
