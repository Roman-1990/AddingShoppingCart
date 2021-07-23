package tests;

import config.DataConfig;
import org.aeonbits.owner.ConfigFactory;

public class DataTest {
    private static DataConfig getTestData() {
        return ConfigFactory.newInstance().create(DataConfig.class, System.getProperties());
    }

    public static String getWebUrl() {
        return getTestData().webUrl();
    }

    public static String getApiUrl() {
        return getTestData().apiUrl();
    }

    public static String cookiAothorization() {
        return getTestData().cookiAothorization();
    }

    public static String getTestUsername() {
        return getTestData().testUsername();
    }

    public static String getTestPassword() {
        return getTestData().testPassword();
    }
}
