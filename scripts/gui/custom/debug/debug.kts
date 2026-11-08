@file:Import("system/debug/block_interact/block_data.kts")

import net.minecraft.server.level.ServerPlayer
import ru.hollowhorizon.hollowengine.client.ui.shape.path
import ru.hollowhorizon.hollowengine.common.data.dataKey
import ru.hollowhorizon.hollowengine.common.scripting.story.functions.player.notify
import ru.hollowhorizon.hollowengine.common.ui.net.openUi

fun openDebugMenu(player: ServerPlayer) {
    val enable = player.uuid in selectionMode
    player.openUi("efc:debug") {
        
        onEvent { payload ->
            when (payload.getString("action")) {
                "toggle_selection" -> {
                    if (player.uuid in selectionMode){
                        selectionMode.remove(player.uuid)
                        player.notify("test")
                    } else {
                        selectionMode.add(player.uuid)
                        player.notify("test2")
                    }
                    path { put(dataKey("test"), enable) }
                }
                
                "close" -> close()                
                
                
            }
        }
    }
}