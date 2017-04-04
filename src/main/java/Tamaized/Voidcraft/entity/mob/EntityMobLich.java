package Tamaized.Voidcraft.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.entity.EntityVoidMob;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import Tamaized.Voidcraft.vadeMecum.progression.VadeMecumWordsOfPower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMobLich extends EntityVoidMob implements IRangedAttackMob {

	public static final List<IVadeMecumCapability.Category> spells = new ArrayList<IVadeMecumCapability.Category>();

	static {
		spells.add(IVadeMecumCapability.Category.Flame);
		spells.add(IVadeMecumCapability.Category.Fireball);
		spells.add(IVadeMecumCapability.Category.RingOfFire);
		spells.add(IVadeMecumCapability.Category.LitStrike);
		spells.add(IVadeMecumCapability.Category.RingOfLit);
		spells.add(IVadeMecumCapability.Category.IceSpike);
		spells.add(IVadeMecumCapability.Category.RingOfFrost);
		spells.add(IVadeMecumCapability.Category.AcidSpray);
		spells.add(IVadeMecumCapability.Category.Disint);
		spells.add(IVadeMecumCapability.Category.RingOfAcid);
	}

	public EntityMobLich(World par1World) {
		super(par1World);

		this.isImmuneToFire = true;
		this.setSize(0.9F, 3.0F);

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(4, new EntityAIRestrictSun(this));
		this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(2, new EntityAILookIdle(this));
		this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 20, 20, 15.0F));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));

		Predicate ies = new Predicate() {
			/**
			 * Return whether the specified entity is applicable to this filter.
			 */
			public boolean apply(Entity p_82704_1_) {
				if (p_82704_1_ instanceof EntityWitherSkeleton) return false;
				else return true;
			}

			public boolean apply(Object p_apply_1_) {
				return p_apply_1_ instanceof Entity ? this.apply((Entity) p_apply_1_) : false;
			}
		};

		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySnowman.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true, false, ies));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityMobLich.class, 0, true, false, ies)); // Lich hate Lich
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, true, false, ies)); // Passive animals
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySlime.class, 0, true, false, ies)); // Slime extends EntityLiving so need to add it manually
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000000298023224D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(65.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobLichSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound() {
		return VoidSoundEvents.EntityMobLichSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobLichSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.6F;
	}

	@Override
	protected Item getDropItem() {
		return VoidCraft.items.voidCloth;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float par2) {
		if (!canAttack(target)) return;
		int randAttk = world.rand.nextInt(spells.size());
		VadeMecumWordsOfPower.invoke(world, spells.get(randAttk), this, target);
	}
}