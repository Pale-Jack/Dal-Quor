package Tamaized.Voidcraft.items.inventory;

import Tamaized.Voidcraft.voidCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;

public abstract class InventoryItem implements IInventory {

	private ItemStack parent;
	private ItemStack[] inventory;

	public InventoryItem(ItemStack stack, int slots) {
		parent = stack;
		inventory = new ItemStack[slots];
		readFromNBT(parent.getSubCompound(voidCraft.modid + "_InventoryItem"));
	}

	public void saveData() {
		writeToNBT(parent.getSubCompound(voidCraft.modid + "_InventoryItem"));
	}

	protected void readFromNBT(NBTTagCompound nbt) {
		NBTTagList list = (NBTTagList) nbt.getTag("Items");
		inventory = new ItemStack[getSizeInventory()];
		if (list != null) {
			for (int i = 0; i < list.tagCount(); i++) {
				NBTTagCompound nbtc = (NBTTagCompound) list.getCompoundTagAt(i);
				byte b = nbtc.getByte("Slot");
				if (b >= 0 && b < inventory.length) {
					inventory[b] = new ItemStack(nbtc);
				}
			}
		}
	}

	protected NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i].func_190926_b()) {
				NBTTagCompound nbtc = new NBTTagCompound();
				nbtc.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(nbtc);
				list.appendTag(nbtc);
			}
		}
		nbt.setTag("Items", list);
		return nbt;
	}

	@Override
	public abstract String getName();

	@Override
	public abstract boolean hasCustomName();

	@Override
	public abstract ITextComponent getDisplayName();

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		if (inventory[i].func_190926_b()) {
			ItemStack itemstack;
			if (inventory[i].func_190916_E() <= count) {
				itemstack = inventory[i];
				inventory[i] = ItemStack.field_190927_a;
				return itemstack;
			} else {
				itemstack = inventory[i].splitStack(count);
				if (inventory[i].func_190916_E() == 0) inventory[i] = ItemStack.field_190927_a;
				return itemstack;
			}
		}
		return ItemStack.field_190927_a;
	}

	@Override
	public ItemStack removeStackFromSlot(int i) {
		if (inventory[i].func_190926_b()) {
			ItemStack itemstack = inventory[i];
			inventory[i] = ItemStack.field_190927_a;
			return itemstack;
		}
		return ItemStack.field_190927_a;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		inventory[i] = stack;
	}

	@Override
	public abstract int getInventoryStackLimit();

	@Override
	public void markDirty() {

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {
		saveData();
	}

	@Override
	public abstract boolean isItemValidForSlot(int index, ItemStack stack);

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {

	}

}
