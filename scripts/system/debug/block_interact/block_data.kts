@file:SharedScript

import java.util.UUID
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks

val allowedBlocksType = mutableSetOf<Block>()
val allowedBlocks = mutableSetOf<BlockPos>()
val selectionMode = mutableSetOf<UUID>()

allowedBlocksType += Blocks.OAK_DOOR
allowedBlocksType += Blocks.CHEST