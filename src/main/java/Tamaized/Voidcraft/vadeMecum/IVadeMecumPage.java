package Tamaized.Voidcraft.vadeMecum;

import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import net.minecraft.client.gui.FontRenderer;

public interface IVadeMecumPage {

	public void render(VadeMecumGUI gui, FontRenderer render, int x, int y, int craftXoffset);

}
