@file:Import("objects/items/debug_tool/debug_tool.startup.kts", 
"gui/custom/debug/debug.kts")

import efc.items.debug.*
import net.minecraft.server.level.ServerPlayer
import ru.hollowhorizon.hollowengine.common.events.entity.player.PlayerInteractEvent

@SubscribeEvent
fun DebugTool(event: PlayerInteractEvent.ItemInteract) {
    val player = event.player as? ServerPlayer ?: return
    
    if (player.mainHandItem.item != DEBUG_TOOL) return
    
    openDebugMenu(player)
}