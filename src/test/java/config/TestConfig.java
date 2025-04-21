package config;

import org.aeonbits.owner.ConfigCache;

public class TestConfig {
    private static final EnvConfig ENV_CONF = ConfigCache.getOrCreate(EnvConfig.class);

    public static EnvConfig getEnvConfig() {
        return ENV_CONF;
    }
}