package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidicDragonscale;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidicDragonscale implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.items.voidicDragonScale).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.voidicDragonScale") };
	}

}
