@file:Import("systems/blocks_interact/block_data.kts")

import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine
import net.minecraft.server.level.ServerPlayer
import ru.hollowhorizon.hollowengine.common.scripting.story.functions.player.notify
import ru.hollowhorizon.hollowengine.common.ui.net.UiSession
import ru.hollowhorizon.hollowengine.common.ui.net.closeUi
import ru.hollowhorizon.hollowengine.common.ui.net.openUi

/**
 * Открывает debug-меню и ждёт, пока игрок его закроет (кнопка "Закрыть" или закрытие окна).
 * Пока меню открыто — обрабатывает toggle/clear.
 */
suspend fun ServerPlayer.openDebugMenu(): Unit = suspendCancellableCoroutine { cont ->
    val session: UiSession = openUi("efc:debug") {
        onEvent { payload ->
            when (payload.getString("action")) {
                "toggle_selection" -> {
                    if (selectionMode.remove(uuid)) {
                        notify("Режим выбора блоков ВЫКЛЮЧЕН")
                    } else {
                        selectionMode.add(uuid)
                        notify("Режим выбора блоков ВКЛЮЧЁН\nПКМ по блоку — добавить/убрать")
                    }
                }

                "clear_allowed" -> {
                    allowedBlocks.clear()
                    notify("Список разрешённых блоков очищен")
                }

                "close" -> {
                    closeUi()
                    if (cont.isActive) cont.resume(Unit)
                }
            }
        }

        // если игрок закрыл UI крестиком / Esc
        onClose {
            if (cont.isActive) cont.resume(Unit)
        }
    }

    // если корутину отменили — закрываем UI
    cont.invokeOnCancellation {
        runCatching { session.close() } // или closeUi(), если close() нет
    }
}