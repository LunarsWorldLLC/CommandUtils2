package me.fivekfubi.api;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Stub interface for BaseRaiders protection plugin API.
 * The actual implementation is provided by the BaseRaiders plugin at runtime.
 */
public interface BaseRaidersProvider {
    void test();

    List<String> get_player_protection_nicks_test(Player player);

    Map<String, I_DATA_Protection> get_protection_database_cache();

    boolean has_permission(Player player, Location location, String permission);

    boolean has_permission(UUID uuid, Location location, String permission);

    boolean has_permission(String playerName, Location location, String permission);
}
