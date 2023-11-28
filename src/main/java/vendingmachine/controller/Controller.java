package vendingmachine.controller;

import java.util.EnumMap;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Product;
import vendingmachine.domain.VendingMachine;
import vendingmachine.domain.VendingMachineStatus;
import vendingmachine.dto.request.ProductRequest;
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
        EnumMap<Coin, Integer> coinCounts = setVendingCoin();
        renderingCoin(coinCounts);

        VendingMachine vendingMachine = setVendingProducts(coinCounts);
        executeVendingMachine(vendingMachine);
    }

    private EnumMap<Coin, Integer> setVendingCoin() {
        return ExceptionHandler.handle(() -> {
            int venderAmount = inputView.readVenderAmount();
            return vendingService.changeToCoin(venderAmount);
        });
    }

    private void renderingCoin(EnumMap<Coin, Integer> coinCounts) {
        outputView.printVenderCoin(coinCounts);
    }

    private VendingMachine setVendingProducts(EnumMap<Coin, Integer> coinCounts) {
        return ExceptionHandler.handle(() -> {
            String productsInfo = inputView.readProducts();
            ProductRequest productRequest = ProductRequest.of(productsInfo);
            return vendingService.createVendingMachine(coinCounts, productRequest);
        });
    }

    private void executeVendingMachine(VendingMachine vendingMachine) {
        int amount = setPutAmount();

        while (vendingMachine.calculateStatus(amount) != VendingMachineStatus.CLOSE) {
            Product buyProduct = setBuyProduct(amount, vendingMachine);
            amount = vendingMachine.sell(buyProduct, amount);
        }
        EnumMap<Coin, Integer> remainCoinCounts = vendingMachine.changeCoins(amount);
        renderingChanges(amount, remainCoinCounts);
    }

    private int setPutAmount() {
        return ExceptionHandler.handle(inputView::readPutAmount);
    }

    private Product setBuyProduct(final int amount, final VendingMachine vendingMachine) {
        return ExceptionHandler.handle(() -> {
            outputView.printAmount(amount);
            String buyProduct = inputView.readBuyProduct(amount);
            return vendingMachine.getProduct(buyProduct);
        });
    }

    private void renderingChanges(int amount, EnumMap<Coin, Integer> remainCoinCounts) {
        outputView.printAmount(amount);
        outputView.printChange(remainCoinCounts);
    }
}
