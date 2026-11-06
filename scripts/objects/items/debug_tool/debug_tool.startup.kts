package efc.items.debug

import ru.hollowhorizon.hollowengine.loader.api.AutoModelType

val DEBUG_TOOL by register("debug_tool", AutoModelType.HANDHELD) {
    Item(Item.Properties().stacksTo(1))
}
