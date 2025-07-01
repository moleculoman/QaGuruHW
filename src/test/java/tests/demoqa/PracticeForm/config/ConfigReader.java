package tests.demoqa.PracticeForm.config;

import org.aeonbits.owner.ConfigFactory;
import tests.demoqa.PracticeForm.config.web.WebConfig;

public enum ConfigReader {
    Instance;

    private static final WebConfig webConfig =
            ConfigFactory.create(
                    WebConfig.class,
                    System.getProperties()
            );

    public WebConfig read() {
        return webConfig;
    }
}
