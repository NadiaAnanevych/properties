package configs;


import constants.Constants;
import org.aeonbits.owner.Config;


@Config.Sources({
        "classpath:${env}.properties",
        "classpath:default.properties"
})

public interface TestPropertiesConfig extends Config {
        @Config.Key("baseUrl")
        @DefaultValue(Constants.BASE_URL)
        String getBaseUrl();

        @Config.Key("username")
        String getUsername();

        @Config.Key("password")
        String getPassword();

    }
