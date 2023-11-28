package vendingmachine.configuration;

import vendingmachine.controller.Controller;
import vendingmachine.service.CoinChanger;
import vendingmachine.service.CoinChangerImpl;
import vendingmachine.service.VendingService;
import vendingmachine.view.input.InputView;
import vendingmachine.view.output.OutputView;

public class ApplicationConfiguration {

    public Controller controller() {
        return new Controller(inputView(), outputView(), vendingService());
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }

    private VendingService vendingService() {
        return new VendingService(coinChangerImpl());
    }

    private CoinChanger coinChangerImpl() {
        return new CoinChangerImpl();
    }
}
