package me.dunescifye.commandutils.utils;

import com.massivecraft.factions.*;

import me.dunescifye.commandutils.CommandUtils;
import me.fivekfubi.api.BaseRaidersAPI;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.ClaimPermission;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

import static com.massivecraft.factions.listeners.FactionsBlockListener.playerCanBuildDestroyBlock;

public class FUtils {
    public static boolean isInsideClaim(final Player player, final Location location) {
        if (CommandUtils.griefPreventionEnabled) {
            final Claim claim = GriefPrevention.instance.dataStore.getClaimAt(location, true, null);
            if (claim == null) return false;
            return claim.getOwnerID().equals(player.getUniqueId()) || claim.hasExplicitPermission(player, ClaimPermission.Build);
        } else if (CommandUtils.factionsUUIDEnabled) {
            return playerCanBuildDestroyBlock(player, location, "destroy", true);
        }

        return true;
    }
    public static boolean isWilderness(Location location) {
        if (CommandUtils.griefPreventionEnabled)
            return GriefPrevention.instance.dataStore.getClaimAt(location, true, null) == null;
        else if (CommandUtils.factionsUUIDEnabled)
            return Board.getInstance().getFactionAt(new FLocation(location)).isWilderness();
        return true;
    }

    public static boolean isInClaimOrWilderness(final Player player, final Location location) {
        if (CommandUtils.griefPreventionEnabled) {
            final Claim claim = GriefPrevention.instance.dataStore.getClaimAt(location, true, null);
            return claim == null || claim.getOwnerID() == null || claim.getOwnerID().equals(player.getUniqueId()) || claim.hasExplicitPermission(player, ClaimPermission.Build);
        } else if (CommandUtils.factionsUUIDEnabled) {
            return playerCanBuildDestroyBlock(player, location, "destroy", true);
        }

        // Check BaseRaiders Protection
        if (CommandUtils.baseRaidersEnabled) {
            return BaseRaidersAPI.get().has_permission(player, location, "break");
        }

        return true;
    }

    /**
     * OPTIMIZED: Filter a set of blocks to only those the player can break.
     * Use this for radius mining instead of checking each block individually.
     *
     * @param player The player breaking blocks
     * @param blocks The set of blocks to check
     * @return Set of blocks the player is allowed to break
     */
    public static Set<Block> filterBreakableBlocks(final Player player, final Set<Block> blocks) {
        if (blocks.isEmpty()) {
            return blocks;
        }

        Set<Block> breakable = new HashSet<>(blocks);

        // Filter by GriefPrevention
        if (CommandUtils.griefPreventionEnabled) {
            breakable.removeIf(block -> {
                final Claim claim = GriefPrevention.instance.dataStore.getClaimAt(block.getLocation(), true, null);
                return claim != null && claim.getOwnerID() != null
                    && !claim.getOwnerID().equals(player.getUniqueId())
                    && !claim.hasExplicitPermission(player, ClaimPermission.Build);
            });
        }

        // Filter by Factions
        if (CommandUtils.factionsUUIDEnabled) {
            breakable.removeIf(block -> !playerCanBuildDestroyBlock(player, block.getLocation(), "destroy", true));
        }

        // Filter by BaseRaiders Protection
        if (CommandUtils.baseRaidersEnabled) {
            breakable.removeIf(block -> !BaseRaidersAPI.get().has_permission(player, block.getLocation(), "break"));
        }

        return breakable;
    }
}
