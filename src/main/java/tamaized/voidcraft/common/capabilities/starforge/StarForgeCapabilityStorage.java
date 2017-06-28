package tamaized.voidcraft.common.capabilities.starforge;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.starforge.effects.IStarForgeEffect.Tier;
import tamaized.voidcraft.common.starforge.effects.StarForgeEffectList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class StarForgeCapabilityStorage implements IStorage<IStarForgeCapability> {

	public StarForgeCapabilityStorage() {
		VoidCraft.instance.logger.info("StarForgeCapabilityStorage Registered");
	}

	@Override
	public NBTBase writeNBT(Capability<IStarForgeCapability> capability, IStarForgeCapability instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("tier1", StarForgeEffectList.getEffectID(instance.getEffect(Tier.ONE)));
		compound.setInteger("tier2", StarForgeEffectList.getEffectID(instance.getEffect(Tier.TWO)));
		compound.setInteger("tier3", StarForgeEffectList.getEffectID(instance.getEffect(Tier.THREE)));
		return compound;
	}

	@Override
	public void readNBT(Capability<IStarForgeCapability> capability, IStarForgeCapability instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound compound = (NBTTagCompound) nbt;
		instance.addEffect(StarForgeEffectList.getEffect(compound.getInteger("tier1")));
		instance.addEffect(StarForgeEffectList.getEffect(compound.getInteger("tier2")));
		instance.addEffect(StarForgeEffectList.getEffect(compound.getInteger("tier3")));
	}
	
}
