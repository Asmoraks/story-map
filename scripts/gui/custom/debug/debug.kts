@file:Import("system/debug/block_interact/block_data.kts")

import net.minecraft.server.level.ServerPlayer
import ru.hollowhorizon.hollowengine.common.data.dataKey
import ru.hollowhorizon.hollowengine.common.ui.net.openUi

fun openDebugMenu(player: ServerPlayer) {
    player.openUi("efc:debug") {
        val enable = player.uuid in selectionMode
        put(dataKey("test"), enable)
        onEvent { payload ->
            when (payload.getString("action")) {
                "toggle_selection" -> {
                    if (enable){
                        selectionMode.remove(player.uuid)
                    } else {
                        selectionMode.add(player.uuid)
                    }
                    val enable = player.uuid in selectionMode
                    put(dataKey("test"), enable)
                }
                
                "close" -> close()                
                
                
            }
        }
    }
}