@file:Import("block_data.kts")

import java.util.UUID
import kotlinx.coroutines.Job
import net.minecraft.server.level.ServerPlayer
import ru.hollowhorizon.hollowengine.common.events.entity.player.PlayerInteractEvent
import ru.hollowhorizon.hollowengine.common.scripting.story.functions.player.notify

val keyListener = mutableMapOf<UUID, Job>()

PlayerInteractEvent.BlockInteract.subscribe(this) { event ->
    val player = event.player as? ServerPlayer ?: return@subscribe
    val pos = event.state.blockPos
    
    if (player.uuid in selectionMode) {
        event.isCanceled = true
        
        if (allowedBlocks.remove(pos)) {
            player.notify("Блок $pos убран из разрешённых")
        } else {
            allowedBlocks.add(pos)
            player.notify("Блок $pos добавлен в разрешённые")
        }
    }

    if (pos !in allowedBlocks) {
        event.isCanceled = true
    }
}