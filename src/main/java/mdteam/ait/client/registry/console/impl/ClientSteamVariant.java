package mdteam.ait.client.registry.console.impl;

import mdteam.ait.AITMod;
import mdteam.ait.client.models.consoles.ConsoleModel;
import mdteam.ait.client.models.consoles.SteamConsoleModel;
import mdteam.ait.client.registry.console.ClientConsoleVariantSchema;
import mdteam.ait.tardis.console.variant.steam.SteamVariant;
import net.minecraft.util.Identifier;
import org.joml.Vector3f;

public class ClientSteamVariant extends ClientConsoleVariantSchema {
	public static final Identifier TEXTURE = new Identifier(AITMod.MOD_ID, ("textures/blockentities/consoles/steam_console.png"));
	public static final Identifier EMISSION = new Identifier(AITMod.MOD_ID, ("textures/blockentities/consoles/steam_console_emission.png"));

	public ClientSteamVariant() {
		super(SteamVariant.REFERENCE, SteamVariant.REFERENCE);
	}

	@Override
	public Identifier texture() {
		return TEXTURE;
	}

	@Override
	public Identifier emission() {
		return EMISSION;
	}

	@Override
	public ConsoleModel model() {
		return new SteamConsoleModel(SteamConsoleModel.getTexturedModelData().createModel());
	}

	@Override
	public Vector3f sonicItemTranslations() {
		return new Vector3f(0.9f, 1.125f, -0.19f);
	}

	@Override
	public float[] sonicItemRotations() {
		return new float[]{30f, 120f};
	}
}
