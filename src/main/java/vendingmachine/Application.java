package vendingmachine;

import vendingmachine.configuration.ApplicationConfiguration;
import vendingmachine.controller.Controller;

public class Application {
    public static void main(String[] args) {
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
        Controller controller = applicationConfiguration.controller();
        controller.process();
    }
}
