package vendingmachine.domain;

import java.util.Objects;

public class Product {
    private final String name;
    private final int price;
    private final int count;

    public Product(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isLessThanPrice(int price) {
        return this.price < price;
    }

    public boolean isDivideByPrice(int price) {
        return this.price >= price && this.price % price == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
