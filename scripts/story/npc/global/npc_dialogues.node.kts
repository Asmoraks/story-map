@file:Attach(NpcEntity::class)

import net.minecraft.server.level.ServerPlayer

editor.name("Диалог при взаимодействии")
editor.description("ПКМ по нпс воспроизводит диалог.")

val story by editor.property<String>("Файл истории", assets = ["story"], restart = true) {
    ""
}
val speaker by editor.property<String>("Имя NPC в скрипте самой истории", restart = true) { "" }
val fromTheStart by editor.property<Boolean>("Всегда запускать сначала?") { true }

val npc = this@NpcEntity
val dialogue = DialogueController(story.removePrefix("scripts/"))

onInteract { event ->
    val player = event.player as? ServerPlayer ?: return@onInteract
    if (fromTheStart) dialogue.reset()
    dialogue.start(player) {
        character(speaker, DialogueCharacter.of(npc))
        put("player_name", player.name.string)
        put("npc_name", npc.name.string)
    }
}

// При сохранении NPC записываем в файл его сущности текущий прогресс диалога
onSave { context -> dialogue.save("dialogue", context.tag) }
onLoad { context -> dialogue.load("dialogue", context.tag) }