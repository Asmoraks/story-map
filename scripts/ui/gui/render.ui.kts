@file:Import("custom/quests.ui.kts", "custom/debug/debug2.ui.kts")  

screen("efc:quests_book") {
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