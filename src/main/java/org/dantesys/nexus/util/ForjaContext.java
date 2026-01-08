package org.dantesys.nexus.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public record ForjaContext(
        Level level,
        Elementos afinidade,
        int slot,
        Player player
) {}