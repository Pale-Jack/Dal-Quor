package tamaized.voidcraft.common.vademecum;

import tamaized.voidcraft.client.gui.VadeMecumGUI;
import net.minecraft.client.gui.FontRenderer;

public interface IVadeMecumCrafting {
	
	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int mx, int my);
	
}
