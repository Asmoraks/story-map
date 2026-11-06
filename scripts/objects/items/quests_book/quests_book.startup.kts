package efc.items.story

import ru.hollowhorizon.hollowengine.loader.api.AutoModelType

val MENU_BOOK by register("menu_book", AutoModelType.HANDHELD) {
    Item(Item.Properties().stacksTo(1))
}