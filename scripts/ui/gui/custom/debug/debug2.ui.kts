private enum class DebugTab {
    SELECT_MODE,
    WORLD,
    PLAYER,
    INFO
}

@Composable
fun UiScope.DebugMenu() {
    var tab by remember { mutableStateOf(DebugTab.SELECT_MODE) }
    // позже можно прокинуть с сервера
    val selectionOn = false

    Box(
        tags = listOf("screen-root"),
        modifier = Modifier.style("efc:ui/styles/test.hss")
    ) {
        Column(tags = listOf("debug-panel")) {

            // ===== HEADER =====
            Row(tags = listOf("header-row")) {
                Text("⚙", tags = listOf("header-icon"))
                Text("Debug Menu", tags = listOf("header-title"))
                Box(tags = listOf("header-line"))
                Text("◆", tags = listOf("header-diamond"))
            }

            // ===== TABS =====
            Row(tags = listOf("tabs-row")) {
                TabChip("■", "Select Mode", tab == DebugTab.SELECT_MODE) {
                    tab = DebugTab.SELECT_MODE
                }
                TabChip("◎", "World", tab == DebugTab.WORLD) {
                    tab = DebugTab.WORLD
                }
                TabChip("☺", "Player", tab == DebugTab.PLAYER) {
                    tab = DebugTab.PLAYER
                }
                TabChip("i", "Info", tab == DebugTab.INFO) {
                    tab = DebugTab.INFO
                }
            }

            // маркер под активной вкладкой (упрощённо — линия под рядом)
            Box(tags = listOf("tabs-underline"))

            // ===== CONTENT =====
            Column(tags = listOf("content")) {
                when (tab) {
                    DebugTab.SELECT_MODE -> SelectModeContent(selectionOn)
                    DebugTab.WORLD -> PlaceholderContent("World", "Инструменты мира появятся здесь")
                    DebugTab.PLAYER -> PlaceholderContent("Player", "Инструменты игрока появятся здесь")
                    DebugTab.INFO -> PlaceholderContent("Info", "Echo of the Fallen Civilization\nDebug panel")
                }
            }

            // ===== CLOSE =====
            Row(
                tags = listOf("close-btn"),
                modifier = Modifier.onClick {
                    send { putString("action", "close") }
                }
            ) {
                Text("✕  Закрыть", tags = listOf("close-text"))
            }
        }
    }
}

@Composable
private fun UiScope.TabChip(
    icon: String,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val tags = if (selected) listOf("tab", "tab-active") else listOf("tab")
    Row(
        tags = tags,
        modifier = Modifier.onClick { onClick() }
    ) {
        Text(icon, tags = listOf("tab-icon"))
        Text(label, tags = listOf("tab-label"))
    }
}

@Composable
private fun UiScope.SelectModeContent(selectionOn: Boolean) {
    // заголовок секции
    Row(tags = listOf("section-header")) {
        Text("■", tags = listOf("section-icon"))
        Text("Режим выбора блоков", tags = listOf("section-title"))
        Box(tags = listOf("section-line"))
    }

    // статус + «переключатель»
    Row(tags = listOf("status-card")) {
        Text("Режим выбора:", tags = listOf("status-label"))
        Row(
            tags = listOf("toggle-box"),
            modifier = Modifier.onClick {
                send { putString("action", "toggle_selection") }
            }
        ) {
            Text("◀", tags = listOf("toggle-arrow"))
            Text(
                if (selectionOn) "ВКЛ" else "ВЫКЛ",
                tags = listOf(if (selectionOn) "toggle-on" else "toggle-off")
            )
            Text("▶", tags = listOf("toggle-arrow"))
        }
    }

    // основная кнопка
    Row(
        tags = listOf("action-primary"),
        modifier = Modifier.onClick {
            send { putString("action", "toggle_selection") }
        }
    ) {
        Text("↻  Переключить режим выбора", tags = listOf("action-primary-text"))
    }// вторичная
    Row(
        tags = listOf("action-secondary"),
        modifier = Modifier.onClick {
            send { putString("action", "clear_allowed") }
        }
    ) {
        Text("🗑  Очистить список блоков", tags = listOf("action-secondary-text"))
    }

    // подсказка
    Row(tags = listOf("hint-card")) {
        Text("!", tags = listOf("hint-icon"))
        Text(
            "ПКМ по блоку в мире — добавить/убрать из списка.",
            tags = listOf("hint-text")
        )
    }
}

@Composable
private fun UiScope.PlaceholderContent(title: String, body: String) {
    Row(tags = listOf("section-header")) {
        Text("•", tags = listOf("section-icon"))
        Text(title, tags = listOf("section-title"))
        Box(tags = listOf("section-line"))
    }
    Row(tags = listOf("hint-card")) {
        Text(body, tags = listOf("hint-text"))
    }
}