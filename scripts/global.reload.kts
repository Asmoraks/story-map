@file:Import("objects/items/quests_book/quests_book.startup.kts")

import efc.items.story.*
import ru.hollowhorizon.hollowengine.common.events.entity.player.PlayerEvent
import ru.hollowhorizon.hollowengine.common.npcs.items.itemFilter
import ru.hollowhorizon.hollowengine.common.scripting.story.functions.player.countItems
import ru.hollowhorizon.hollowengine.common.scripting.story.functions.player.giveItem

@SubscribeEvent
fun onPlayerJoin(event: PlayerEvent.Join) {
    val player = event.player
    
    val myItem = itemFilter(item("efc:menu_book"))
    if (player.countItems(myItem) == 0) {
        player.giveItem(item("efc:menu_book", 1))
    }
    
}
