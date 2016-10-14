package Tamaized.Voidcraft.vadeMecum.entry.blocks.voidcrystalblock.pages;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumCrafting;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VadeMecumPageVoidCrystalBlock3 implements IVadeMecumPage {

	private IVadeMecumCrafting crafting = new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
			new ItemStack(voidCraft.items.voidcrystal),
			new ItemStack(voidCraft.items.voidcrystal),
			new ItemStack(voidCraft.items.voidcrystal),
			new ItemStack(voidCraft.items.voidcrystal),
			new ItemStack(voidCraft.items.voidcrystal),
			new ItemStack(voidCraft.items.voidcrystal),
			new ItemStack(voidCraft.items.voidcrystal),
			new ItemStack(voidCraft.items.voidcrystal),
			new ItemStack(voidCraft.items.voidcrystal) }, new ItemStack(voidCraft.blocks.blockVoidcrystal));

	@Override
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset) {
		crafting.render(gui, render, x+craftXoffset, y);
	}

}
