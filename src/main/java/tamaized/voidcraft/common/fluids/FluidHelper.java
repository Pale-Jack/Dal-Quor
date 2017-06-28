package tamaized.voidcraft.common.fluids;

import tamaized.voidcraft.VoidCraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class FluidHelper {

	public static void sendToAllAround(IFaceFluidHandler source, World world, BlockPos pos, int amount) {
		for (EnumFacing face : EnumFacing.VALUES) {
			if (!source.outputFaces().contains(face)) continue;
			TileEntity te = world.getTileEntity(pos.offset(face));
			if (te instanceof IFaceFluidHandler) {
				IFaceFluidHandler fte = ((IFaceFluidHandler) te);
				if (fte.inputFaces().contains(face.getOpposite()) && fte.fill(new FluidStack(VoidCraft.fluids.voidFluid, amount), true) > 0) {
					source.drain(new FluidStack(VoidCraft.fluids.voidFluid, amount), true);
				}
			}
		}
	}

}
