package Tamaized.Voidcraft.vadeMecum.contents.progression;

import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.armor.voidarmor.VadeMecumPageListVoidArmor;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.blocks.VadeMecumBlocksEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.bosses.VadeMecumBossesEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit.VadeMecumPageListFruit;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.items.VadeMecumItemsEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.VadeMecumMachinesEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.mobs.VadeMecumMobsEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.thevoid.VadeMecumPageListVoid;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.tools.VadeMecumToolsEntry;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.voidicinfusion.VadeMecumPageListVoidicInfusion;
import Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.VadeMecumWeaponsEntry;

public class VadeMecumProgressionEntryList {

	public static enum Entry {
		MAIN
	}

	public static int getEntryID(Entry e) {
		return e.ordinal();
	}

	public static Entry getEntryFromID(int id) {
		return id >= Entry.values().length ? null : Entry.values()[id];
	}

	public final VadeMecumEntry MAIN;

	public VadeMecumProgressionEntryList() {
		MAIN = new VadeMecumProgressionEntry();
	}

	public void preLoadObjects() {
		MAIN.initObjects();
	}

}
