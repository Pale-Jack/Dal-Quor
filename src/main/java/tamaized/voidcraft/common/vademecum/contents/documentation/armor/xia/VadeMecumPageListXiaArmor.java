package tamaized.voidcraft.common.vademecum.contents.documentation.armor.xia;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;

public class VadeMecumPageListXiaArmor implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] { new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.xiaArmor", VoidCraft.modid + ".VadeMecum.docs.desc.xiaArmor") };
	}

}
