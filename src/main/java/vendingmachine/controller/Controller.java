package vendingmachine.controller;

import java.util.EnumMap;
import vendingmachine.domain.Coin;
import vendingmachine.service.VendingService;
import vendingmachine.view.input.InputView;
import vendingmachine.view.output.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final VendingService vendingService;

    public Controller(InputView inputView, OutputView outputView, VendingService vendingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.vendingService = vendingService;
    }

    public void process() {
        EnumMap<Coin, Integer> coinCounts = setVenderCoin();
        renderingCoin(coinCounts);
    }

    private EnumMap<Coin, Integer> setVenderCoin() {
        return ExceptionHandler.handle(() -> {
            int venderAmount = inputView.readVenderAmount();
            return vendingService.changeToCoin(venderAmount);
        });
    }

    private void renderingCoin(EnumMap<Coin, Integer> coinCounts) {
        outputView.printVenderCoin(coinCounts);
    }
}
