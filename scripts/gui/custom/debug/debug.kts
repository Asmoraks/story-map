@file:Import("system/debug/block_interact/block_data.kts")

import net.minecraft.server.level.ServerPlayer
import ru.hollowhorizon.hollowengine.common.ui.net.openUi

fun openDebugMenu(player: ServerPlayer) {
    player.openUi("efc:debug") {
        onEvent { payload ->
            when (payload.getString("action")) {
                "close" -> close()
                "toggle_selection" -> close()
            }
        }
    }
}