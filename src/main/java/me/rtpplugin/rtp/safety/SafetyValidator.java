package me.rtpplugin.rtp.safety;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.Location;
import java.util.Set;
import java.util.EnumSet;

public class SafetyValidator {

    private static final Set<Material> DANGEROUS_BLOCKS = EnumSet.of(
            Material.LAVA, Material.FIRE, Material.MAGMA_BLOCK,
            Material.CAMPFIRE, Material.SOUL_CAMPFIRE, Material.VOID_AIR, Material.CACTUS
    );

    public static boolean isSafe(Location location) {
        Block feet = location.getBlock();
        Block feet = feet.getRelative(0, 1, 0);
        Block ground = feet.getRelative(0, -1, 0);

        if (!ground.getType().isSolid()) return false;
        if (feet.getType().isSolid() || head.getType().isSolid()) return false;
        if (DANGEROUS_BLOCKS.contains(ground.getType())) return false;

        for (int x = -5; x <= 5; x++) {
            for (int z = -5; z <= 5; z++) {
                Block b = location.clone().add(x, -1, z).getBlock();
                if (DANGEROUS_BLOCKS.contains(b.getType())) return false;
                if (b.isLiquid()) return false;
            }
        }

        return true;
    }
}