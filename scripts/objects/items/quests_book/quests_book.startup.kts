package efc.items.story

import ru.hollowhorizon.hollowengine.loader.api.AutoModelType

val QUESTS_BOOK by register("quests_book", AutoModelType.HANDHELD) {
    Item(Item.Properties().stacksTo(1))
}