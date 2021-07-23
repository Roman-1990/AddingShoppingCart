package helpers;

import com.codeborne.selenide.Configuration;
import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverSettings {

    private static ProjectConfig getDriverConfig() {
        return ConfigFactory.newInstance().create(ProjectConfig.class, System.getProperties());
    }

    public static String getWebRemoteDriver() {
        // https://%s:%s@selenoid.autotests.cloud/wd/hub/
        return String.format(getDriverConfig().remoteDriverUrl(),
                getDriverConfig().webRemoteDriverUser(),
                getDriverConfig().webRemoteDriverPassword());
    }

    public static boolean isRemoteWebDriver() {
        return !getDriverConfig().remoteDriverUrl().equals("");
    }
    public static void configureDriver() {


        Configuration.browser = getDriverConfig().browser();
        Configuration.browserVersion = getDriverConfig().browserVersion();
        Configuration.browserSize = getDriverConfig().browserSize();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (isRemoteWebDriver()) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.remote = getWebRemoteDriver();
        }
        Configuration.browserCapabilities = capabilities;
    }
}
