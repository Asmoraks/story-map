@file:SharedScript

import java.util.UUID
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Block

val allowedBlocksType = mutableSetOf<Block>()
val allowedBlocks = mutableSetOf<BlockPos>()
val selectionMode = mutableSetOf<UUID>()