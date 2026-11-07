import net.minecraft.server.level.ServerPlayer
import ru.hollowhorizon.hollowengine.common.ui.net.UiSession
import ru.hollowhorizon.hollowengine.common.ui.net.openUi

private suspend fun ServerPlayer.debugMenu() {
    var session: UiSession? = null
    
    session = openUi("efc:debug") {
        onEvent { payload ->
            when (payload.getString("action")) {
                "close" -> close()
            }
        }
    } 
}
