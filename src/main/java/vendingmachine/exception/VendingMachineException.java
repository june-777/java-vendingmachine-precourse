package vendingmachine.exception;

public enum VendingMachineException {
    INVALID_AMOUNT("보유 금액으로 동전을 생성할 수 없습니다."),
    DUPLICATE_PRODUCT("중복된 상품이 존재합니다."),
    INVALID_PRODUCT_COUNT("상품 등록 시, 상품의 개수는 1개 이상이어야 합니다."),
    INVALID_PRODUCT_MIN_PRICE("상품 등록 시, 상품 가격은 100원부터 시작해야 합니다."),
    INVALID_PRODUCT_PRICE_UNIT("상품 등록 시, 상품 가격은 10원으로 나누어 떨어져야 합니다."),

    NON_EXIST_PRODUCT("주문한 상품은 존재하지 않는 상품입니다."),
    OUT_OF_STOCK_PRODUCT("주문한 상품의 수량이 다 떨어졌습니다."),
    EXPENSIVE_PRODUCT("남은 금액으로 살 수 없는 상품입니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    VendingMachineException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }

    public IllegalArgumentException make() {
        return new IllegalArgumentException(getMessage());
    }
}
