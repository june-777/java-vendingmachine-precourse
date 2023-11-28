package vendingmachine.exception;

public enum VendingMachineException {
    INVALID_AMOUNT("보유 금액으로 동전을 생성할 수 없습니다."),
    DUPLICATE_PRODUCT("중복된 상품이 존재합니다.");

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
