package Tamaized.Voidcraft.vadeMecum;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class VadeMecumCraftingFurnace implements IVadeMecumCrafting {

	private static final ResourceLocation TEXTURE = new ResourceLocation(voidCraft.modid, "textures/gui/VadeMecum/Crafting_Furnace.png");

	private final String title;
	private final ItemStack input;
	private final ItemStack output;

	public VadeMecumCraftingFurnace(String title, ItemStack input, ItemStack output) {
		this.title = ("" + I18n.format(title, new Object[0])).trim();
		this.input = input;
		this.output = output;
	}

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my) {
		gui.drawCenteredStringNoShadow(render, TextFormatting.UNDERLINE + title, x + 65, y, 0x000000);
		GlStateManager.color(1, 1, 1, 1);
		gui.mc.getTextureManager().bindTexture(TEXTURE);
		gui.drawTexturedModalRect(x, y + 35, 128, 128, 0, 0, 256, 256);

		gui.renderItemStack(input, x + 32, y + 90, mx, my);

		gui.renderItemStack(output, x + 92, y + 90, mx, my);
	}

}
