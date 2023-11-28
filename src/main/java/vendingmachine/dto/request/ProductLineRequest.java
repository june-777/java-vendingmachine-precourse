package vendingmachine.dto.request;

public class ProductLineRequest {

    private final String name;
    private final int price;
    private final int count;

    public ProductLineRequest(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "ProductLineRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
