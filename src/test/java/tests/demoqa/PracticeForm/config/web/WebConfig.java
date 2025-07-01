package tests.demoqa.PracticeForm.config.web;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${env}.properties",
        "file:~/${env}.properties",
        "file:./${env}.properties"
})
public interface WebConfig extends Config {
    @Key("browser")
    @DefaultValue("chrome")
    Browser browser();
    @Key("browserVersion")
    @DefaultValue("128.0")
    String browserVersion();
    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();
    @Key("baseUrl")
    String baseUrl();
    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();
    @Key("remoteUrl")
    String remoteUrl();
}
