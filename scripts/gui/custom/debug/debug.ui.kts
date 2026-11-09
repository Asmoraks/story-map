private enum class DebugTab {
    INFO,
    SELECT_MODE,
    WORLD,
    PLAYER
}

@Composable
fun UiScope.DebugMenu() {
    var tab by remember { mutableStateOf(DebugTab.SELECT_MODE) }
    
    Box(
        tags = listOf("screen-root"),
        modifier = Modifier.style("efc:ui/styles/debug.hss")
    ) {
        Column(tags = listOf("debug-panel")) {

            // HEADER
            Row(tags = listOf("header-row")) {
                Text("⚙", tags = listOf("header-icon"))
                Text("Debug Menu", tags = listOf("header-title"))
                Box(tags = listOf("header-line"))
                Text("◆", tags = listOf("header-diamond"))
            }

            // TABS
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

            Box(tags = listOf("tabs-underline"))

            // CONTENT
            Column(tags = listOf("content")) {
                when (tab) {
                    DebugTab.INFO -> PlaceholderContent("Info", "Echo of the Fallen Civilization\nDebug panel")
                    DebugTab.SELECT_MODE -> SelectModeContent()
                    DebugTab.WORLD -> PlaceholderContent("World", "Инструменты мира появятся здесь")
                    DebugTab.PLAYER -> PlaceholderContent("Player", "Инструменты игрока появятся здесь")
                }
            }

            // CLOSE — снизу по центру
            Row(tags = listOf("footer")) {
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
}

@Composable
private fun UiScope.TabChip(
    icon: String,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val tags = if (selected) listOf("tab", "tab-active") else listOf("tab")
    Row(tags = tags, modifier = Modifier.onClick { onClick() }) {
        Text(icon, tags = listOf("tab-icon"))
        Text(label, tags = listOf("tab-label"))
    }
}

@Composable
private fun UiScope.SelectModeContent() {
    // заголовок секции — СНАРУЖИ рамки
    val allowedBlocksType = data.get<String>(dataKey("blocks_type"))
    val selectionMode = data.get<Boolean>(dataKey("selection_mode"))
    var enable by remember { mutableStateOf(selectionMode) }
    
    if (enable != selectionMode) {
        enable = selectionMode
    }
    
    Row(tags = listOf("section-header")) {
        Text("■", tags = listOf("section-icon"))
        Text("Режим выбора блоков", tags = listOf("section-title"))
        Box(tags = listOf("section-line"))
    }

    // ВСЁ НИЖЕ — в одной рамке
    Column(tags = listOf("section-frame")) {

        // статус + вкл/выкл
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
                    if (enable) "Вкл" else "Выкл",
                    tags = listOf(if (enable) "toggle-on" else "toggle-off")
                )
                Text("▶", tags = listOf("toggle-arrow"))
            }
        }
        // подсказка
        Row(tags = listOf("hint-card")) {
            Text("!", tags = listOf("hint-icon"))
            Text(
                "ПКМ по блоку в мире — добавить/убрать из списка.",
                tags = listOf("hint-text")
            )
        }
        // главная кнопка
        Row(
            tags = listOf("action-secondary"),
            modifier = Modifier.onClick {
                // включает режим и подсказывает игроку
                send { putString("action", "blocks_selection") }
            }
        ) {
            Text("■  Выбор блоков для редактирования", tags = listOf("action-primary-text"))
        }

        // список выбранных блоков
        Column(tags = listOf("list-card")) {
            Row(tags = listOf("list-header")) {
                Text("Выбранные блоки ", tags = listOf("list-title"))
                Text(
                    " ${allowedBlocksType}",
                    tags = listOf("list-count")
                )
            }

            if (allowedBlocksType.isEmpty()) {
                Row(tags = listOf("list-empty")) {
                    Text("Список пуст", tags = listOf("list-empty-text"))
                }
            } else {
                Column(tags = listOf("list-body")) {
                    allowedBlocksType.forEach { pos ->
                        Row(tags = listOf("list-item")) {
                            Text("■", tags = listOf("list-item-icon"))
                            Text(allowedBlocksType, tags = listOf("list-item-text"))
                        }
                    }
                }
            }
        }

        // очистить
        Row(
            tags = listOf("action-secondary"),
            modifier = Modifier.onClick {
                send { putString("action", "clear_allowed") }
            }
        ) {
            Text("🗑  Очистить список блоков", tags = listOf("action-secondary-text"))
        }
    }
}

@Composable
private fun UiScope.PlaceholderContent(title: String, body: String) {
    Row(tags = listOf("section-header")) {
        Text("•", tags = listOf("section-icon"))
        Text(title, tags = listOf("section-title"))
        Box(tags = listOf("section-line"))
    }
    Column(tags = listOf("section-frame")) {
        Row(tags = listOf("hint-card")) {
            Text(body, tags = listOf("hint-text"))
        }
    }
}
