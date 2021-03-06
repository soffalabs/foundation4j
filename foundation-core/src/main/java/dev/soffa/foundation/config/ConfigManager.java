package dev.soffa.foundation.config;

public interface ConfigManager {

    <T> T bind(String prefix, Class<T> kind);

    String require(String name);

    default <T> T bindInternal(String prefix, Class<T> kind) {
        return bind("app.internal." + prefix, kind);
    }

}
