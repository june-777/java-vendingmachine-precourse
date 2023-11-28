package vendingmachine.view.input;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.view.validator.FormValidator;
import vendingmachine.view.validator.NumberValidator;

public class InputView {

    public int readVenderAmount() {
        System.out.println(Message.VENDER_AMOUNT.message);
        String input = Console.readLine().trim();
        NumberValidator.validate(input);

        return Integer.parseInt(input);
    }

    public String readProducts() {
        System.out.println();
        System.out.println(Message.VENDING_PRODUCT.message);
        String productsInput = Console.readLine().trim();
        FormValidator.validate(productsInput);

        return productsInput;
    }

    private enum Message {
        //TODO
        VENDER_AMOUNT("자판기가 보유하고 있는 금액을 입력해 주세요."),
        VENDING_PRODUCT("상품명과 가격, 수량을 입력해 주세요.");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
