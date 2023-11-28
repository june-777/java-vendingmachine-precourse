package vendingmachine.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import vendingmachine.exception.VendingMachineException;

class CoinChangerImplTest {

    CoinChanger coinChanger = new CoinChangerImpl();

    @DisplayName("자판기가 보유하고 있는 금액을 입력했을 때")
    @Nested
    class CoinChangeTest {

        @DisplayName("[Exception] 동전으로 거스름할 수 없는 금액이라면 domain에서 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 9, 11, 49, 51, 99, 101, 499, 501})
        void cantChange(int amountInput) {
            Assertions.assertThatThrownBy(() -> coinChanger.change(amountInput))
                    .hasMessage(VendingMachineException.INVALID_AMOUNT.getMessage());
        }

        @DisplayName("[Exception] 동전으로 거스름할 수 있는 금액이라면 domain에서 예외가 발생하지 않는다.")
        @ParameterizedTest
        @ValueSource(ints = {10, 50, 100, 500, 510, 520, 120, 130, 150, 600})
        void canChange(int amountInput) {
            Assertions.assertThatCode(() -> coinChanger.change(amountInput))
                    .doesNotThrowAnyException();
        }
    }
}