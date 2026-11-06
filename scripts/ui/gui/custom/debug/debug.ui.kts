@Composable
fun UiScope.DebugMenu() {
    Box(tags = listOf("screen-root"), 
    modifier = Modifier.style("efc:ui/styles/test.hss")) {
        Column(tags = listOf("debug-root")) {
    
            // Заголовок
            Row(tags = listOf("header")) {
                Text("Debug Menu", tags = listOf("title"))
            }
    
            Row(tags = listOf("status-row")) {
                Text("Режим выбора блоков:", tags = listOf("label"))
                Text(
                    if (/* можно будет передавать состояние */ false) "ВКЛ" else "ВЫКЛ",
                    tags = listOf("value")
                )
            }
    
            Row(
                tags = listOf("button-row"),
                modifier = Modifier.onClick {
                    send { putString("action", "toggle_selection") }
                }
            ) {
                Text("Переключить режим выбора блоков", tags = listOf("button"))
            }
    
            Row(
                tags = listOf("button-row"),
                modifier = Modifier.onClick {
                    send { putString("action", "clear_allowed") }
                }
            ) {
                Text("Очистить список разрешённых блоков", tags = listOf("button"))
            }
    
            Row(
                tags = listOf("button-row"),
                modifier = Modifier.onClick {
                    send { putString("action", "close") }
                }
            ) {
                Text("Закрыть", tags = listOf("button"))
            }
        }
    }
}