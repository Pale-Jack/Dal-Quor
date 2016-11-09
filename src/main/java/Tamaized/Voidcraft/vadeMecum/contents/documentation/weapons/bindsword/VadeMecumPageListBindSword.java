package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.bindsword;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListBindSword implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.tools.chainSword).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.chainSword"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(voidCraft.modid+".VadeMecum.recipe.normal", new ItemStack[] {
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.tools.voidSword),
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.items.voidChain),
						new ItemStack(voidCraft.items.voidChain) }, new ItemStack(voidCraft.tools.chainSword))) };
	}

}
