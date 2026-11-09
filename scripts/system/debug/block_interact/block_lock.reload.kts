@file:Import("block_data.kts")

import java.util.UUID
import kotlinx.coroutines.Job
import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.DoorBlock
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import ru.hollowhorizon.hollowengine.common.events.entity.player.PlayerInteractEvent
import ru.hollowhorizon.hollowengine.common.scripting.story.functions.player.notify

val keyListener = mutableMapOf<UUID, Job>()

fun getRelatedPositions(level: Level, pos: BlockPos): Set<BlockPos> {
    val state = level.getBlockState(pos)
    val positions = mutableSetOf(pos)
    
    if (state.block is DoorBlock) {
        val half = state.getValue(DoorBlock.HALF)
        if (half == DoubleBlockHalf.LOWER) {
            positions += pos.above()
        } else {
            positions += pos.below()
        }
    }
    return positions
}

PlayerInteractEvent.BlockInteract.subscribe(this) { event ->
    val player = event.player as? ServerPlayer ?: return@subscribe
    val pos = event.state.blockPos
    val level = player.level()
    val block = level.getBlockState(pos).block
    
    if (event.hand != InteractionHand.MAIN_HAND) return@subscribe
    if (block !in allowedBlocksType) return@subscribe
    
    val related = getRelatedPositions(level, pos)
    
    if (player.uuid in selectionMode) {
        event.isCanceled = true
        
        val alreadyAllowed = related.any { it in allowedBlocks }
        if (alreadyAllowed) {
            related.forEach{ allowedBlocks.remove(it)}
            player.notify("Блок $related убран из разрешённых")
        } else {
            related.forEach{ allowedBlocks.add(it)}
            player.notify("Блок $related добавлен в разрешённые")
        }
        return@subscribe
    }

    if (related.none { it in allowedBlocks}) {
        event.isCanceled = true
    }
}