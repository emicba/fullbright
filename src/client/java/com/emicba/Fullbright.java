package com.emicba;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Fullbright implements ClientModInitializer {
	private static final Double FULLBRIGHT_GAMMA = 16.0;

	@Override
	public void onInitializeClient() {
		var keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.fullbright.toggleFullbright",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_N,
				"category.fullbright.name"
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (keyBinding.wasPressed()) {
				var gamma = MinecraftClient.getInstance().options.getGamma();
				gamma.setValue(gamma.getValue().equals(FULLBRIGHT_GAMMA) ? 1.0 : FULLBRIGHT_GAMMA);
			}
		});
	}
}
