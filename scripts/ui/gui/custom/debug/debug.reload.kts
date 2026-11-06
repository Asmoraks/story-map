@file:Import("systems/blocks_interact/block_data.kts")

import net.minecraft.server.level.ServerPlayer
import ru.hollowhorizon.hollowengine.client.ui.UiEvent
import ru.hollowhorizon.hollowengine.common.scripting.story.functions.player.notify
import ru.hollowhorizon.hollowengine.common.ui.net.closeUi

@SubscribeEvent
fun onUiEvent(event: UiEvent) {
    val player = event.player as? ServerPlayer ?: return
    val action = event.data.getString("action")

    when (action) {
        "toggle_selection" -> {
            if (selectionMode.remove(player.uuid)) {
                player.notify("Режим выбора блоков ВЫКЛЮЧЕН")
            } else {
                selectionMode.add(player.uuid)
                player.notify("Режим выбора блоков ВКЛЮЧЁН\nПКМ по блоку — добавить/убрать")
            }
        }

        "clear_allowed" -> {
            // если ты вернёшься на Set<BlockPos>
            // allowedBlocks.clear()
            player.notify("Список разрешённых блоков очищен")
        }

        "close" -> {
            // обычно меню закрывается само, но можно явно
            //player.closeUi()
        }
    }
}