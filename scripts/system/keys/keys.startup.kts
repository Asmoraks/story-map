import net.minecraft.client.KeyMapping
import org.lwjgl.glfw.GLFW

@SubscribeEvent
fun onKeys(event: RegisterKeyBindingsEvent) {
    event.registerKeyMapping(KeyMapping("test.test.test", GLFW.GLFW_KEY_J, "test.test.test"))
    event.registerKeyMapping(KeyMapping("test.test.test", GLFW.GLFW_KEY_Y, "test.test.test"))
}