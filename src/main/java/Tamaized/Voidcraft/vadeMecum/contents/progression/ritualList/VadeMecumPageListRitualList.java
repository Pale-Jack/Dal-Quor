package Tamaized.Voidcraft.vadeMecum.contents.progression.ritualList;

import java.util.ArrayList;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.contents.progression.ritualList.pages.TestMultiBlockGUIRender;

public class VadeMecumPageListRitualList implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		ArrayList<IVadeMecumPage> pages = new ArrayList<IVadeMecumPage>();
		pages.add(new VadeMecumPage("Voidic Ritual List", "This page will display the available rituals that can be invoked to advance your Vade Mecum Knowledge"));
		if(cap.getObtainedCategories().contains(IVadeMecumCapability.Category.INTRO)){
			if(!cap.getObtainedCategories().contains(IVadeMecumCapability.Category.TOME)){
				pages.add(new TestMultiBlockGUIRender("Test"));
			}
		}
		return pages.toArray(new IVadeMecumPage[pages.size()]);
	}

}
