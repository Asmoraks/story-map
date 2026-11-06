@file:Import("objects/items/quests_book/quests_book.startup.kts")

import efc.items.story.*
import net.minecraft.server.level.ServerPlayer
import ru.hollowhorizon.hollowengine.common.events.entity.player.PlayerInteractEvent
import ru.hollowhorizon.hollowengine.common.ui.net.openUi

@SubscribeEvent
fun PlayerInteractEvent(event: PlayerInteractEvent.ItemInteract) {
    val player = event.player as? ServerPlayer ?: return
    val itemStack = player.mainHandItem
    
    if (player.level().isClientSide) return
    if (itemStack.item != MENU_BOOK) return
    
    player.openUi("efc:quests_list")
}
