@file:Import("custom/quests.ui.kts", "custom/debug.ui.kts")  

screen("efc:quests_list") {
    title = "Мой дневник"
    pausesGame = true
    closeOnEscape = true
    
    content {
        QuestsMenu()
    }  
}

screen("efc:debug") {
    title = "Меню отладки"
    pausesGame = true
    closeOnEscape = true
    
    content {
        DebugMenu()
    }  
}