package me.fivekfubi.api;

/**
 * Stub class for BaseRaiders protection plugin API.
 * The actual implementation is provided by the BaseRaiders plugin at runtime.
 */
public class BaseRaidersAPI {
    private static BaseRaidersProvider provider;

    public static BaseRaidersProvider get() {
        if (provider == null)
            throw new IllegalStateException("API not initialized yet");
        return provider;
    }

    public static void setProvider(BaseRaidersProvider p) {
        provider = p;
    }
}
