@Composable  
fun UiScope.QuestsMenu() {
    Column(modifier = Modifier.style("ui/style/test.hss")) {  
        Row(tags = listOf("quest-row"), modifier = Modifier.onClick { send { putString("action", "open_quests") }}) {
            Text("Привет! Это моё кастомное меню", tags = listOf("body")) 
        }
    }  
}  