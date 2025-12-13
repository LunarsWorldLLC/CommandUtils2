package me.dunescifye.commandutils.utils;

import me.dunescifye.commandutils.CommandUtils;
import me.fivekfubi.api.I_DATA_Protection;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Function;

/**
 * Optimized utility class for FiveK Protection integration.
 *
 * For radius mining operations, this class provides batch protection checking
 * that minimizes API calls by caching nearby protections.
 */
public class FiveKProtectionUtils {

    /**
     * Function to get protection at a location. Set this in your plugin's onEnable.
     * Example: FiveKProtectionUtils.getProtectionAt = loc -> YourProtectionManager.getProtection(loc);
     */
    public static Function<Location, I_DATA_Protection> getProtectionAt = null;

    /**
     * Function to get all protections in a chunk. More optimal for batch operations.
     * Set this if your API supports it for better performance.
     * Example: FiveKProtectionUtils.getProtectionsInChunks = chunks -> YourProtectionManager.getProtections(chunks);
     */
    public static Function<Set<org.bukkit.Chunk>, Collection<I_DATA_Protection>> getProtectionsInChunks = null;

    /**
     * Check if a player can break at a location.
     * Use this for single block checks.
     */
    public static boolean canBreakAt(Player player, Location location) {
        if (!CommandUtils.fiveKProtectionEnabled || getProtectionAt == null) {
            return true;
        }

        I_DATA_Protection protection = getProtectionAt.apply(location);
        return canBreakInProtection(player, protection);
    }

    /**
     * Check if player has permission to break in a protection.
     */
    private static boolean canBreakInProtection(Player player, I_DATA_Protection protection) {
        if (protection == null) {
            return true; // No protection = wilderness
        }

        String playerUUID = player.getUniqueId().toString();

        // Check if player is owner
        if (playerUUID.equals(protection.get_owner_uuid())) {
            return true;
        }

        // Check if player is a member
        Map<String, String> members = protection.get_members_map();
        if (members != null && members.containsKey(playerUUID)) {
            return true;
        }

        return false;
    }

    /**
     * OPTIMIZED: Filter blocks that are protected and the player cannot break.
     *
     * This is the most efficient method for radius mining - call it once with all blocks,
     * and it returns only the blocks the player is allowed to break.
     *
     * @param player The player breaking blocks
     * @param blocks The set of blocks to check
     * @return Set of blocks the player is allowed to break
     */
    public static Set<Block> filterBreakableBlocks(Player player, Set<Block> blocks) {
        if (!CommandUtils.fiveKProtectionEnabled) {
            return blocks; // No protection enabled, all blocks are breakable
        }

        if (blocks.isEmpty()) {
            return blocks;
        }

        // Try optimized chunk-based lookup first
        if (getProtectionsInChunks != null) {
            return filterBreakableBlocksOptimized(player, blocks);
        }

        // Fallback to per-block checking
        if (getProtectionAt != null) {
            return filterBreakableBlocksSimple(player, blocks);
        }

        return blocks; // No API configured
    }

    /**
     * Optimized filtering using chunk-based protection lookup.
     * Gets all protections in affected chunks ONCE, then checks each block.
     */
    private static Set<Block> filterBreakableBlocksOptimized(Player player, Set<Block> blocks) {
        // Collect all unique chunks
        Set<org.bukkit.Chunk> chunks = new HashSet<>();
        for (Block block : blocks) {
            chunks.add(block.getChunk());
        }

        // Get all protections in these chunks (single API call)
        Collection<I_DATA_Protection> protections = getProtectionsInChunks.apply(chunks);
        if (protections == null || protections.isEmpty()) {
            return blocks; // No protections in area
        }

        // Cache protection data for fast lookup
        List<CachedProtection> cachedProtections = new ArrayList<>();
        for (I_DATA_Protection protection : protections) {
            if (!canBreakInProtection(player, protection)) {
                Location center = protection.get_location();
                int range = protection.get_protection_range_amount();
                boolean infiniteY = protection.get_c_range_infinite_y();
                cachedProtections.add(new CachedProtection(center, range, infiniteY));
            }
        }

        if (cachedProtections.isEmpty()) {
            return blocks; // Player has access to all protections
        }

        // Filter blocks
        Set<Block> breakable = new HashSet<>();
        for (Block block : blocks) {
            if (!isBlockProtected(block.getLocation(), cachedProtections)) {
                breakable.add(block);
            }
        }

        return breakable;
    }

    /**
     * Simple filtering that checks each block individually.
     * Less optimal but works with basic API.
     */
    private static Set<Block> filterBreakableBlocksSimple(Player player, Set<Block> blocks) {
        Set<Block> breakable = new HashSet<>();
        for (Block block : blocks) {
            if (canBreakAt(player, block.getLocation())) {
                breakable.add(block);
            }
        }
        return breakable;
    }

    /**
     * Fast check if a location is within any cached protection zone.
     */
    private static boolean isBlockProtected(Location blockLoc, List<CachedProtection> protections) {
        for (CachedProtection protection : protections) {
            if (isWithinProtection(blockLoc, protection)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a location is within a protection's range.
     */
    private static boolean isWithinProtection(Location blockLoc, CachedProtection protection) {
        Location center = protection.center;

        // Must be in same world
        if (!blockLoc.getWorld().equals(center.getWorld())) {
            return false;
        }

        int range = protection.range;

        // Check X and Z distance
        int dx = Math.abs(blockLoc.getBlockX() - center.getBlockX());
        int dz = Math.abs(blockLoc.getBlockZ() - center.getBlockZ());

        if (dx > range || dz > range) {
            return false;
        }

        // Check Y if not infinite
        if (!protection.infiniteY) {
            int dy = Math.abs(blockLoc.getBlockY() - center.getBlockY());
            if (dy > range) {
                return false;
            }
        }

        return true;
    }

    /**
     * Cached protection data for fast lookup during batch operations.
     */
    private static class CachedProtection {
        final Location center;
        final int range;
        final boolean infiniteY;

        CachedProtection(Location center, int range, boolean infiniteY) {
            this.center = center;
            this.range = range;
            this.infiniteY = infiniteY;
        }
    }
}
