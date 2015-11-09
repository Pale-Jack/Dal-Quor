/**
 * Original Class file: BossBarHandler
 * Mod: Botania
 * Author: Vazkii
 * 
 * This is a custom version of it therefore I wont claim this file truly as my own.
 */
package Tamaized.Voidcraft.mobs.entity.boss;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.mobs.EntityVoidNPC;
import Tamaized.Voidcraft.mobs.ai.EntityAIPathHerobrineFlightPhase1;
import Tamaized.Voidcraft.mobs.ai.EntityAIPathHerobrineFlightPhase2;
import Tamaized.Voidcraft.mobs.ai.EntityVoidNPCAIBase;
import Tamaized.Voidcraft.mobs.entity.boss.bar.IVoidBossData;
import Tamaized.Voidcraft.sound.BossMusicManager;

public class EntityMobHerobrine extends EntityVoidNPC implements IVoidBossData{
	
	private int phase = 0;
	private static final int PHASE_01 = 1;
	private static final int PHASE_02 = 2;
	private static final int PHASE_03 = 3;
	private boolean ready = false;
	
	private ArrayList<EntityAIBase> ai = new ArrayList<EntityAIBase>();
	private ArrayList<Class> filter = new ArrayList<Class>();
	
    public EntityMobHerobrine(World par1World) {
    	super(par1World);
    	this.isImmuneToFire = true;
    	this.hurtResistantTime = 10;
    	this.setSize(0.6F, 1.8F);
    	this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    	this.tasks.addTask(6, new EntityAILookIdle(this));
    	
    	filter.add(EntityPlayer.class);
    	
    	//this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    	this.setInvul(true);
    }
    
    public boolean displayBossMode(){
    	return (PHASE_03 >= phase && phase > 0);
    }
    
    public void doDamage(int a){
    	this.setHealth(this.getHealth() - a);
    }
    
    public boolean isAIEnabled() {
        return true; //return true to tick AI
    }
    
    public boolean interact(EntityPlayer p_70085_1_){
    	if(phase == 0) ready = true;
    	else doDamage((int) this.getMaxHealth());
    	return super.interact(p_70085_1_);
    }
    
    public boolean hasStartedFight(){
    	return phase > 0 ? true : false;
    }
    
	@Override
	public float getPercentHP() {
		float f = this.getHealth() / this.getMaxHealth();
		return f < 0 ? 0 : f < 1 ? 1 : f; //Keep the number no less than 0 but ni higher than 1, even though it's impossible to reach below/beyond.
	}
    
    protected void updateAITick(){
    	if(ready){
    		phase++;
    		System.out.println("Starting Phase Init: "+phase);
    		InitPhase(phase);
    		System.out.println("Phase ("+phase+") Init finished");
    		ready = false;
    	}
    	
    	if(phase == PHASE_01){
    		
    	}else if(phase == PHASE_02){
    		
    	}else if(phase == PHASE_03){
    		
    	}else{
    		if (this.getHealth() <= 0.0F)
            {
               this.trueDeathUpdate();
            }
    	}
    }
    
    private void InitPhase(int p){
		Iterator iter = ai.iterator();
		while(iter.hasNext()){
			Object o = iter.next();
			if(o instanceof EntityVoidNPCAIBase){
				EntityVoidNPCAIBase ai = (EntityVoidNPCAIBase) o;
				ai.kill();
			}
			if(o instanceof EntityAIBase) tasks.removeTask((EntityAIBase) o);
			iter.remove();
		}
    	tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
		canDie = false;
    	if(p == PHASE_01){
    		/**
    		 * Cycle:
    		 *  - Herobrine shoots fireballs.
    		 *  - Pillars need to get hit with fireball, cycle through textures of green wool, yellow, red. 4th hit will damage herobrine.
    		 *  - Pillars Spawn every 5 seconds
    		 *  - Max of 6 Pillars at a time
    		 */
    		isFlying = true;
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
            this.setHealth(this.getMaxHealth());
            
            EntityVoidNPCAIBase newAI = new EntityAIPathHerobrineFlightPhase1(this, filter);
            ai.add(newAI);
            newAI.Init();
            this.tasks.addTask(1, newAI);
            BossMusicManager.PlayTheSound(this.worldObj, this, new ItemStack(voidCraft.voidDiscs.get(10)), new int[]{(int) this.posX, (int) this.posY, (int) this.posZ}, true);
    	}else if(p == PHASE_02){
    		/**
    		 * Cycle:
    		 *  - Herobrine chases the player.
    		 *  - On touching a player, deal damage.
    		 *  - Herobrine must run through a pillar to be dealt damage.
    		 *  - Pillars Spawn every 5 seconds
    		 *  - Max of 6 Pillars at a time
    		 */
    		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
    		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
    		this.setHealth(this.getMaxHealth());
             
    		EntityVoidNPCAIBase newAI = new EntityAIPathHerobrineFlightPhase2(this, filter);
    		ai.add(newAI);
    		newAI.Init();
    		this.tasks.addTask(1, newAI);
    	}else if(p == PHASE_03){
    		
    	}else{
    		canDie = true;
    		onDeathUpdate();
    	}
    }
    
    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource p_70645_1_){ //Switch phases when we fake death
    	if(phase > PHASE_03){
    		this.setHealth(0);
    		this.isDead = true;
    		super.onDeath(p_70645_1_);
    		setDead();
    	}else{
    		ready = true;
    		updateAITick();
    	}
    }
    
    private void trueDeathUpdate(){
    	++this.deathTime;

        if (this.deathTime >= 20)
        {
            int i;

            if (!this.worldObj.isRemote && (this.recentlyHit > 0 || this.isPlayer()) && this.func_146066_aG() && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot"))
            {
                i = this.getExperiencePoints(this.attackingPlayer);

                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
            }

            this.isDead = true;

            for (i = 0; i < 20; ++i)
            {
                double d2 = this.rand.nextGaussian() * 0.02D;
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("explode", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d2, d0, d1);
            }
        }
    }
    
    protected void onDeathUpdate(){ //Intercept deathUpdate to keep entity alive
    	if(ready) return;
		this.isDead = false;
    	onDeath(DamageSource.generic);
    }
    
    public void setDead(){ //True death
		BossMusicManager.StopTheSound();
    	Iterator iter = ai.iterator();
		while(iter.hasNext()){
			Object o = iter.next();
			if(o instanceof EntityVoidNPCAIBase){
				EntityVoidNPCAIBase ai = (EntityVoidNPCAIBase) o;
				ai.kill();
			}
			if(o instanceof EntityAIBase) tasks.removeTask((EntityAIBase) o);
			iter.remove();
		}
        phase = 100;
        this.isDead = true;
    }
    
    /**
     * Checks whether target entity is alive.
     */
    public boolean isEntityAlive()
    {
        return phase > PHASE_03;
    }
    
    protected void despawnEntity(){}
    
    @Override
    protected void collideWithEntity(Entity par1Entity){}
    
    @Override
    public void applyEntityCollision(Entity par1Entity){}

	
    @Override
    protected void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(999.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(999.0D);
    }
    
    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float p_70069_1_) {}

    /**
     * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
     * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
     */
    protected void updateFallState(double p_70064_1_, boolean p_70064_3_) {}

    @Override
    protected String getLivingSound(){
        return null;
    }

    @Override
    protected String getHurtSound(){
    	return null;
    }

    @Override
    protected String getDeathSound(){
    	return null;
    }

    @Override
    protected float getSoundVolume(){
        return 0.0F;
    }

	public String getDisplayName() {
		return "Avatar of Herobrine";
	}
}