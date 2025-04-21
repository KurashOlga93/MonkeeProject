package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:config/config.properties"
})
public interface EnvConfig extends Config {
    @Key("user")
    String user();

    @Key("password")
    String password();
}