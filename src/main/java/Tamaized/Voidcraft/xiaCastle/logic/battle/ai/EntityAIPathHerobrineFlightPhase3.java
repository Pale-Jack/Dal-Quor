package Tamaized.Voidcraft.xiaCastle.logic.battle.ai;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import Tamaized.Voidcraft.mobs.EntityVoidNPC;
import Tamaized.Voidcraft.projectiles.HerobrineFireball;

public class EntityAIPathHerobrineFlightPhase3 extends EntityVoidNPCAIBase{
	
	private EntityLiving theWatcher;
    /** The closest entity which is being watched by this one. */
    protected Entity closestEntity;
    /** This is the Maximum distance that the AI will look for the Entity */
    private float maxDistanceForPlayer = 30;
    private ArrayList<Class> watchedClass = new ArrayList<Class>();
    
    private EntityAIHandler ai;
	
	private BlockPos loc;
	private int currPath = 0;
	private boolean xPos = true;
	private boolean zPos = true;
	
	private int currTick = 0;
	private int tick_Fireball = 0;
	
	private int callTick = 2*20;
	private int callTick_Fireball = 3*20;

	public EntityAIPathHerobrineFlightPhase3(EntityVoidNPC entityMobHerobrine, ArrayList<Class> c) {
		watchedClass = new ArrayList<Class>();
		watchedClass.addAll(c);
		entity = entityMobHerobrine;
		theWatcher = entity;
		ai = new EntityAIHandler(entityMobHerobrine, (int) entityMobHerobrine.posX, (int) entityMobHerobrine.posY, (int) entityMobHerobrine.posZ);
	}

	@Override
	public void kill() {
		ai.kill();
		super.kill();
	}

	@Override
	public void Init(){
		super.Init();
		
		ai.Init(3);
		
		loc = entity.getPosition().add(0, 10, 0);
	
		execute = true;
	}

	@Override
	public void updateTask(){
		if(!execute) return;
		ai.update();
		
		if(currTick==callTick){
			for(Class c : watchedClass){
				Entity e = theWatcher.worldObj.findNearestEntityWithinAABB(c, theWatcher.getEntityBoundingBox().expand((double)maxDistanceForPlayer, 30.0D, (double)maxDistanceForPlayer), theWatcher);
				if(e != null){
					closestEntity = e;
					break;
				}
				closestEntity = null;
			}
			currTick=0;
    	}
		
		if(closestEntity != null){
			theWatcher.getLookHelper().setLookPosition(closestEntity.posX, closestEntity.posY + (double)closestEntity.getEyeHeight(), closestEntity.posZ, 10.0F, (float)theWatcher.getVerticalFaceSpeed());
			
			double d0 = closestEntity.posX - theWatcher.posX;
            double d2 = closestEntity.posZ - theWatcher.posZ;
			float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			
			float f3 = MathHelper.wrapDegrees(f - theWatcher.rotationYaw);
	        
			theWatcher.rotationYaw = theWatcher.rotationYaw + f3;
		}
		
		if(tick_Fireball >= callTick_Fireball){
			if(closestEntity != null){
				/*theWatcher.worldObj.playRecord(new BlockPos((int)theWatcher.posX, (int)theWatcher.posY, (int)theWatcher.posZ), null);//((EntityPlayer)null, 1008, new BlockPos((int)theWatcher.posX, (int)theWatcher.posY, (int)theWatcher.posZ), 0);
	    		double d5 = closestEntity.posX - theWatcher.posX;
	    		double d6 = closestEntity.getEntityBoundingBox().minY + (double)(closestEntity.height / 2.0F) - (theWatcher.posY + (double)(theWatcher.height / 2.0F));
	    		double d7 = closestEntity.posZ - theWatcher.posZ;
	                
	    		HerobrineFireball entitylargefireball = new HerobrineFireball(theWatcher.worldObj, theWatcher, d5, d6, d7);
	    		double d8 = 4.0D;
	    		Vec3d vec3 = theWatcher.getLook(1.0F);
	    		entitylargefireball.posX = theWatcher.posX;// + vec3.xCoord * d8;
	    		entitylargefireball.posY = theWatcher.posY + (double)(theWatcher.height / 2.0F) + 0.5D;
	    		entitylargefireball.posZ = theWatcher.posZ;// + vec3.zCoord * d8;
	    		theWatcher.worldObj.spawnEntityInWorld(entitylargefireball);*/
			}
			tick_Fireball = 0;
		}
		
		double y = entity.posY;
		double py = loc.getY();
		double dy = 0;
		
		if(y < py) dy = 0.2;
		else if(y == py) dy = 0.0;
		else{
			entity.posY = py;
			dy = 0;
		}
		entity.posY += dy;
		
    	currTick++;
    	tick_Fireball++;
	}

}
