@file:Import("system/debug/block_interact/block_data.kts")

import net.minecraft.server.level.ServerPlayer
import ru.hollowhorizon.hollowengine.common.data.dataKey
import ru.hollowhorizon.hollowengine.common.ui.net.openUi

fun openDebugMenu(player: ServerPlayer) {
    player.openUi("efc:debug") {
        val enable = player.uuid in selectionMode
        put(dataKey("selection_mode"), enable)
        put(dataKey("blocks_type"), allowedBlocksType.toString())
        onEvent { payload ->
            when (payload.getString("action")) {
                "toggle_selection" -> {
                    if (player.uuid in selectionMode){
                        selectionMode.remove(player.uuid)
                    } else {
                        selectionMode.add(player.uuid)
                    }
                    put(dataKey("selection_mode"), player.uuid in selectionMode)
                }
                "blocks_selection" -> {
                    put(dataKey("blocks_type"), allowedBlocksType.toString())
                }
                
                "close" -> close()
            }
        }
    }
}