
private enum class DebugTab {
    SELECT_MODE,
    WORLD,
    PLAYER,
    INFO
}

@Composable
fun UiScope.DebugMenu() {
    var tab by remember { mutableStateOf(DebugTab.SELECT_MODE) }

    Box(
        tags = listOf("screen-root"),
        modifier = Modifier.style("efc:ui/styles/debug.hss")
    ) {
        Column(tags = listOf("debug-root")) {

            // Заголовок
            Row(tags = listOf("header")) {
                Text("Debug Menu", tags = listOf("title"))
            }

            // Вкладки
            Row(tags = listOf("tabs-row")) {
                DebugTabButton("Select Mode", tab == DebugTab.SELECT_MODE) {
                    tab = DebugTab.SELECT_MODE
                }
                DebugTabButton("World", tab == DebugTab.WORLD) {
                    tab = DebugTab.WORLD
                }
                DebugTabButton("Player", tab == DebugTab.PLAYER) {
                    tab = DebugTab.PLAYER
                }
                DebugTabButton("Info", tab == DebugTab.INFO) {
                    tab = DebugTab.INFO
                }
            }

            // Контент вкладки
            Column(tags = listOf("tab-content")) {
                when (tab) {
                    DebugTab.SELECT_MODE -> SelectModeTab()
                    DebugTab.WORLD -> WorldTab()
                    DebugTab.PLAYER -> PlayerTab()
                    DebugTab.INFO -> InfoTab()
                }
            }

            // Общая кнопка закрытия
            Row(
                tags = listOf("button-row", "close"),
                modifier = Modifier.onClick {
                    send { putString("action", "close") }
                }
            ) {
                Text("Закрыть", tags = listOf("button"))
            }
        }
    }
}

@Composable
private fun UiScope.DebugTabButton(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val tags = if (selected) {
        listOf("tab-btn", "tab-btn-active")
    } else {
        listOf("tab-btn")
    }

    Row(
        tags = tags,
        modifier = Modifier.onClick { onClick() }
    ) {
        Text(label, tags = listOf("tab-btn-text"))
    }
}

@Composable
private fun UiScope.SelectModeTab() {
    // Статус (пока false — позже можно прокинуть с сервера)
    val selectionOn = false

    Row(tags = listOf("status-row")) {
        Text("Режим выбора блоков:", tags = listOf("label"))
        Text(
            if (selectionOn) "ВКЛ" else "ВЫКЛ",
            tags = listOf(if (selectionOn) "value-on" else "value-off")
        )
    }

    Row(
        tags = listOf("button-row", "toggle"),
        modifier = Modifier.onClick {
            send { putString("action", "toggle_selection") }
        }
    ) {
        Text("Переключить режим выбора", tags = listOf("button"))
    }

    Row(
        tags = listOf("button-row", "danger"),
        modifier = Modifier.onClick {
            send { putString("action", "clear_allowed") }
        }
    ) {
        Text("Очистить список блоков", tags = listOf("button"))
    }

    Row(tags = listOf("hint-row")) {
        Text(
            "ПКМ по блоку в мире — добавить/убрать из списка",
            tags = listOf("hint")
        )
    }
}

@Composable
private fun UiScope.WorldTab() {
    Row(tags = listOf("status-row")) {
        Text("World tools", tags = listOf("label"))
        Text("soon", tags = listOf("value"))
    }

    Row(
        tags = listOf("button-row"),
        modifier = Modifier.onClick {
            send { putString("action", "world_placeholder") }
        }
    ) {
        Text("Пример действия мира", tags = listOf("button"))
    }
}

@Composable
private fun UiScope.PlayerTab() {
    Row(tags = listOf("status-row")) {
        Text("Player tools", tags = listOf("label"))
        Text("soon", tags = listOf("value"))
    }
    Row(
        tags = listOf("button-row"),
        modifier = Modifier.onClick {
            send { putString("action", "player_placeholder") }
        }
    ) {
        Text("Пример действия игрока", tags = listOf("button"))
    }
}

@Composable
private fun UiScope.InfoTab() {
    Column(tags = listOf("info-block")) {
        Text("Echo of the Fallen Civilization", tags = listOf("info-title"))
        Text("Debug panel for map tools", tags = listOf("hint"))
        Text("Select Mode — выбор разрешённых блоков", tags = listOf("hint"))
    }
}