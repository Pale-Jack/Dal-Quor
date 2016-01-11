package Tamaized.Voidcraft.machina;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;

public class VoidMacerator extends BlockContainer {
	
	private Random rand = new Random();
	
	public boolean isActive;
	
	public VoidMacerator(){
		super(Material.rock);
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state){
		super.onBlockAdded(world, pos, state);
		this.setDefaultDirection(world, pos);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand){
		if(this.isActive){
			int dir = getMetaFromState(state);
			
			float x1 = (float) pos.getX() + 0.5F;
			float y1 = (float) pos.getY() + rand.nextFloat(); 
			float z1 = (float) pos.getZ() + 0.5F;
			
			float f = 0.52F;
			float f1 = rand.nextFloat() * 0.6F - 0.3F;
			
			if(dir == 4){
				world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 - f), (double) (y1), (double) (z1 + f1), -1D, 0D, 0D);
				world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 - f), (double) (y1), (double) (z1 + f1), -1D, 0D, 0D);
				world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 - f), (double) (y1), (double) (z1 + f1), -1D, 0D, 0D);
			}else if(dir == 5){
				world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f), (double) (y1), (double) (z1 + f1), 1D, 0D, 0D);
				world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f), (double) (y1), (double) (z1 + f1), 1D, 0D, 0D);
				world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f), (double) (y1), (double) (z1 + f1), 1D, 0D, 0D);
			}else if(dir == 2){
				world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, -1D);
				world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, -1D);
				world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, -1D);
			}else if(dir == 3){
				world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 1D);
				world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 1D);
				world.spawnParticle(EnumParticleTypes.PORTAL, (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 1D);
			}
		}
	}
	
	private void setDefaultDirection(World world, BlockPos pos){
		if(!world.isRemote){
			Block l = world.getBlockState(pos.add(0, 0, -1)).getBlock();
			Block il = world.getBlockState(pos.add(0, 0, 1)).getBlock();
			Block jl = world.getBlockState(pos.add(-1, 0, 0)).getBlock();
			Block kl = world.getBlockState(pos.add(1, 0, 0)).getBlock();
		
			byte b0 = 3;
			
			world.setBlockState(pos, this.getStateFromMeta(b0), 2);
		}
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		if(!world.isRemote){
			FMLNetworkHandler.openGui(player, voidCraft.instance, voidCraft.guiIdMacerator, world, x, y, z);
		}
		return true;
	}
		
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityVoidMacerator();
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
		int l = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		if(l == 0){
			world.setBlockState(pos, this.getStateFromMeta(2), 2);
		}
		
		if(l == 1){
			world.setBlockState(pos, this.getStateFromMeta(5), 2);
		}
		
		if(l == 2){
			world.setBlockState(pos, this.getStateFromMeta(3), 2);
		}
		
		if(l == 3){
			world.setBlockState(pos, this.getStateFromMeta(4), 2);
		}
		
		if(stack.hasDisplayName()){
			((TileEntityVoidMacerator) world.getTileEntity(pos)).setGuiDisplayName(stack.getDisplayName());
		}
	}
	
	public boolean hasComparatorInputOverride(){
		return true;
	}
	
	@Override
	public int getComparatorInputOverride(World world, BlockPos pos){
		return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(pos));
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player){
		return new ItemStack(voidCraft.blocks.voidMacerator);
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state){
			TileEntityVoidMacerator tileentity = (TileEntityVoidMacerator) world.getTileEntity(pos);
			
			if(tileentity != null){
				for(int i = 0; i < tileentity.getSizeInventory(); i++){
					ItemStack itemstack = tileentity.getStackInSlot(i);
					
					if(itemstack != null){
						float f = this.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
						
						while(itemstack.stackSize > 0){
							int j = this.rand.nextInt(21);
							
							if(j > itemstack.stackSize){
								j = itemstack.stackSize;
							}
							
							itemstack.stackSize-=j;
							
							EntityItem item = new EntityItem(world, (double)((float)pos.getX() + f), (double)((float)pos.getY() + f1), (double)((float)pos.getZ() + f2), new ItemStack(itemstack.getItem()));
							
							if(itemstack.hasTagCompound()){
								item.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
							}
							
							float f3 = 0.05F;
							item.motionX = (double)((float)this.rand.nextGaussian() * f3);
							item.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
							item.motionZ = (double)((float)this.rand.nextGaussian() * f3);
							
							world.spawnEntityInWorld(item);
						}
					}
				}
				
				//world.func_96440_m(x, y, z, oldBlockID);
			}
		
		
		super.breakBlock(world, pos, state);
	}

	
	
	
	
	}