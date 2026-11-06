@file:Attach(NpcEntity::class)

editor.name("Поворот к игроку")
editor.description("Нпс смотрит на игрока.")

val npc = this@NpcEntity
onUpdate(every = 1.ticks) {  
    val player = level().players().minByOrNull { it.distanceTo(npc) } ?: return@onUpdate  
    if (npc.distanceTo(player) <= 5.0) {  
        npc.lookAtNow(player, maxAngularSpeed = 50f) // плавный поворот головы каждый тик  
    }  
}