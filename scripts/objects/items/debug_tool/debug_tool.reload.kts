@file:Import("systems/blocks_interact/block_data.kts", "objects/items/debug_tool/debug_tool.startup.kts")

import efc.items.debug.*
import net.minecraft.server.level.ServerPlayer
import ru.hollowhorizon.hollowengine.common.events.entity.player.PlayerInteractEvent
import ru.hollowhorizon.hollowengine.common.ui.net.openUi

@SubscribeEvent
fun PlayerInteractEvent(event: PlayerInteractEvent.ItemInteract) {
    val player = event.player as? ServerPlayer ?: return
    val itemStack = player.mainHandItem
    
    if (player.level().isClientSide) return
    if (itemStack.item != DEBUG_TOOL) return
    
    player.openUi("efc:debug")
}