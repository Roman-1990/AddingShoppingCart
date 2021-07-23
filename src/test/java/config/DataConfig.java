package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/app.properties"
})

public interface DataConfig extends Config {
    @Key("userLogin")
    String testUsername();

    @Key("userPassword")
    String testPassword();

    @Key("web.url")
    String webUrl();

    @Key("api.url")
    String apiUrl();

    @Key("cookiAothorization")
    String cookiAothorization();

}
