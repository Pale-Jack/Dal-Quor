package Tamaized.Voidcraft.proxy;

import Tamaized.TamModized.proxy.AbstractProxy;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.blocks.TileEntityNoBreak;
import Tamaized.Voidcraft.blocks.render.RenderNoBreak;
import Tamaized.Voidcraft.blocks.render.RenderVoidicAnchor;
import Tamaized.Voidcraft.blocks.render.RenderVoidicCharger;
import Tamaized.Voidcraft.client.*;
import Tamaized.Voidcraft.entity.boss.EntityBossCorruptedPawn;
import Tamaized.Voidcraft.entity.boss.dragon.render.RenderDragonOldWithBar;
import Tamaized.Voidcraft.entity.boss.dragon.sub.voidic.EntityVoidicDragon;
import Tamaized.Voidcraft.entity.boss.dragon.sub.voidic.render.RenderVoidicDragon;
import Tamaized.Voidcraft.entity.boss.herobrine.EntityBossHerobrine;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.*;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.render.ModelHerobrineShadow;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.render.RenderHerobrineCreeper;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.render.RenderHerobrineShadow;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.render.RenderHerobrineTNTPrimed;
import Tamaized.Voidcraft.entity.boss.herobrine.render.RenderHerobrine;
import Tamaized.Voidcraft.entity.boss.lob.EntityLordOfBlades;
import Tamaized.Voidcraft.entity.boss.lob.render.ModelLordOfBlades;
import Tamaized.Voidcraft.entity.boss.lob.render.RenderLordOfBlades;
import Tamaized.Voidcraft.entity.boss.model.ModelCorruptedPawn;
import Tamaized.Voidcraft.entity.boss.model.ModelVoidBoss;
import Tamaized.Voidcraft.entity.boss.model.ModelVoidBossOverlay;
import Tamaized.Voidcraft.entity.boss.render.RenderCorruptedPawn;
import Tamaized.Voidcraft.entity.boss.render.bossBar.BossBarOverlay;
import Tamaized.Voidcraft.entity.boss.twins.EntityBossDol;
import Tamaized.Voidcraft.entity.boss.twins.EntityBossZol;
import Tamaized.Voidcraft.entity.boss.twins.render.RenderDol;
import Tamaized.Voidcraft.entity.boss.twins.render.RenderZol;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia2;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.EntityDragonXia;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.EntityWitherbrine;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.EntityZolXia;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.render.EntityDolXia;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.render.RenderTwinsXia;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.render.RenderWitherbrine;
import Tamaized.Voidcraft.entity.boss.xia.model.ModelXia2;
import Tamaized.Voidcraft.entity.boss.xia.render.RenderXia;
import Tamaized.Voidcraft.entity.boss.xia.render.RenderXia2;
import Tamaized.Voidcraft.entity.companion.EntityCompanionFireElemental;
import Tamaized.Voidcraft.entity.companion.render.RenderFireElementalCompanion;
import Tamaized.Voidcraft.entity.ghost.EntityGhostBiped;
import Tamaized.Voidcraft.entity.ghost.EntityGhostPlayer;
import Tamaized.Voidcraft.entity.ghost.render.RenderGhostPlayer;
import Tamaized.Voidcraft.entity.mob.*;
import Tamaized.Voidcraft.entity.mob.dalquor.EntityHashalaq;
import Tamaized.Voidcraft.entity.mob.dalquor.model.ModelHashalaq;
import Tamaized.Voidcraft.entity.mob.lich.EntityLichInferno;
import Tamaized.Voidcraft.entity.mob.model.ModelLich;
import Tamaized.Voidcraft.entity.mob.model.ModelSpectreChain;
import Tamaized.Voidcraft.entity.mob.model.ModelVoidWrath;
import Tamaized.Voidcraft.entity.mob.model.ModelWraith;
import Tamaized.Voidcraft.entity.mob.render.RenderEtherealGuardian;
import Tamaized.Voidcraft.entity.nonliving.*;
import Tamaized.Voidcraft.entity.nonliving.render.*;
import Tamaized.Voidcraft.events.client.DebugEvent;
import Tamaized.Voidcraft.events.client.TextureStitch;
import Tamaized.Voidcraft.handlers.SkinHandler;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicAnchor;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCharger;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import Tamaized.Voidcraft.sound.client.BGMusic;
import Tamaized.Voidcraft.vadeMecum.contents.VadeMecumMainEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends AbstractProxy {

	private static final ResourceLocation WHITESPACE = new ResourceLocation(VoidCraft.modid, "textures/entity/whitespace.png");
	public static VadeMecumGUI vadeMecum;
	public static VadeMecumMainEntry vadeMecumEntryList;

	public ClientProxy() {
		super(Side.CLIENT);
	}

	@Override
	public void preRegisters() {
		OBJLoader.INSTANCE.addDomain(VoidCraft.modid);
	}

	@Override
	public void preInit() {
		MinecraftForge.EVENT_BUS.register(SkinHandler.instance);
		vadeMecumEntryList = new VadeMecumMainEntry();
		vadeMecumEntryList.preLoadObject();

		float shadowSize = 0.5F;
		// MOBS
		RenderingRegistry.registerEntityRenderingHandler(EntityLordOfBlades.class, new IRenderFactory<EntityLordOfBlades>() {
			@Override
			public Render<? super EntityLordOfBlades> createRenderFor(RenderManager manager) {
				return new RenderLordOfBlades(manager, new ModelLordOfBlades(), shadowSize, new ResourceLocation(VoidCraft.modid, "textures/entity/lordofblades.png"));
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityHashalaq.class, new IRenderFactory<EntityHashalaq>() {
			@Override
			public Render<? super EntityHashalaq> createRenderFor(RenderManager manager) {
				return new RenderGeneric(manager, new ModelHashalaq(), shadowSize, new ResourceLocation(VoidCraft.modid, "textures/entity/hashalaq.png"), 0.5F);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityMobWraith.class, new IRenderFactory<EntityMobWraith>() {
			@Override
			public Render<? super EntityMobWraith> createRenderFor(RenderManager manager) {
				return new RenderGeneric(manager, new ModelWraith(), shadowSize, new ResourceLocation(VoidCraft.modid, "textures/entity/zwraith.png"), 1.0F);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityMobSpectreChain.class, new IRenderFactory<EntityMobSpectreChain>() {
			@Override
			public Render<? super EntityMobSpectreChain> createRenderFor(RenderManager manager) {
				return new RenderGeneric(manager, new ModelSpectreChain(), shadowSize, new ResourceLocation(VoidCraft.modid, "textures/entity/zspectrechain.png"), 1.0F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityMobLich.class, new IRenderFactory<EntityMobLich>() {
			@Override
			public Render<? super EntityMobLich> createRenderFor(RenderManager manager) {
				return new RenderGeneric(manager, new ModelLich(), shadowSize, new ResourceLocation(VoidCraft.modid, "textures/entity/voidiclich.png"), 1.0F);
			}
		});

		RenderingRegistry.registerEntityRenderingHandler(EntityMobVoidWrath.class, new IRenderFactory<EntityMobVoidWrath>() {
			@Override
			public Render<? super EntityMobVoidWrath> createRenderFor(RenderManager manager) {
				return new RenderGeneric(manager, new ModelVoidWrath(), shadowSize, new ResourceLocation(VoidCraft.modid, "textures/entity/zvoidwrath.png"), 1.0F);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityBossCorruptedPawn.class, new IRenderFactory<EntityBossCorruptedPawn>() {
			@Override
			public Render<? super EntityBossCorruptedPawn> createRenderFor(RenderManager manager) {
				return new RenderCorruptedPawn(manager, new ModelCorruptedPawn(), shadowSize);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityBossHerobrine.class, new IRenderFactory<EntityBossHerobrine>() {
			@Override
			public Render<? super EntityBossHerobrine> createRenderFor(RenderManager manager) {
				return new RenderHerobrine(manager, new ModelVoidBoss<EntityBossHerobrine>(), shadowSize);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityBossDol.class, new IRenderFactory<EntityBossDol>() {
			@Override
			public Render<? super EntityBossDol> createRenderFor(RenderManager manager) {
				return new RenderDol(manager, shadowSize);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityBossZol.class, new IRenderFactory<EntityBossZol>() {
			@Override
			public Render<? super EntityBossZol> createRenderFor(RenderManager manager) {
				return new RenderZol(manager, new ModelVoidBoss<EntityBossZol>(), shadowSize);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityBossXia.class, new IRenderFactory<EntityBossXia>() {
			@Override
			public Render<? super EntityBossXia> createRenderFor(RenderManager manager) {
				return new RenderXia(manager, new ModelVoidBossOverlay<EntityBossXia>(), shadowSize);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityBossXia2.class, new IRenderFactory<EntityBossXia2>() {
			@Override
			public Render<? super EntityBossXia2> createRenderFor(RenderManager manager) {
				return new RenderXia2(manager, new ModelXia2<EntityBossXia2>(), shadowSize);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityGhostPlayer.class, new IRenderFactory<EntityGhostPlayer>() {
			@Override
			public Render<? super EntityGhostPlayer> createRenderFor(RenderManager manager) {
				return new RenderGhostPlayer(manager, new ModelPlayer(0.0F, false));
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityGhostBiped.class, new IRenderFactory<EntityGhostBiped>() {
			@Override
			public Render<? super EntityGhostBiped> createRenderFor(RenderManager manager) {
				return new RenderGhostPlayer(manager, new ModelBiped(0.0F));
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineCreeper.class, new IRenderFactory<EntityHerobrineCreeper>() {
			@Override
			public Render<? super EntityHerobrineCreeper> createRenderFor(RenderManager manager) {
				return new RenderHerobrineCreeper(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineWitherSkull.class, new IRenderFactory<EntityHerobrineWitherSkull>() {
			@Override
			public Render<? super EntityHerobrineWitherSkull> createRenderFor(RenderManager manager) {
				return new RenderWitherSkull(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineTNTPrimed.class, new IRenderFactory<EntityHerobrineTNTPrimed>() {
			@Override
			public Render<? super EntityHerobrineTNTPrimed> createRenderFor(RenderManager manager) {
				return new RenderHerobrineTNTPrimed(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineShadow.class, new IRenderFactory<EntityHerobrineShadow>() {
			@Override
			public Render<? super EntityHerobrineShadow> createRenderFor(RenderManager manager) {
				return new RenderHerobrineShadow(manager, new ModelHerobrineShadow<EntityHerobrineShadow>());
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityWitherbrine.class, new IRenderFactory<EntityWitherbrine>() {
			@Override
			public Render<? super EntityWitherbrine> createRenderFor(RenderManager manager) {
				return new RenderWitherbrine(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityDragonXia.class, new IRenderFactory<EntityDragonXia>() {
			@Override
			public Render<? super EntityDragonXia> createRenderFor(RenderManager manager) {
				return new RenderDragonOldWithBar(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityVoidicDragon.class, new IRenderFactory<EntityVoidicDragon>() {
			@Override
			public Render<? super EntityVoidicDragon> createRenderFor(RenderManager manager) {
				return new RenderVoidicDragon(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityDolXia.class, new IRenderFactory<EntityDolXia>() {
			@Override
			public Render<? super EntityDolXia> createRenderFor(RenderManager manager) {
				return new RenderTwinsXia(manager, RenderTwinsXia.TEXTURE_DOL, new ModelVoidBoss<EntityDolXia>(), shadowSize);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityZolXia.class, new IRenderFactory<EntityZolXia>() {
			@Override
			public Render<? super EntityZolXia> createRenderFor(RenderManager manager) {
				return new RenderTwinsXia(manager, RenderTwinsXia.TEXTURE_ZOL, new ModelVoidBoss<EntityZolXia>(), shadowSize);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityMobEtherealGuardian.class, new IRenderFactory<EntityMobEtherealGuardian>() {
			@Override
			public Render<? super EntityMobEtherealGuardian> createRenderFor(RenderManager manager) {
				return new RenderEtherealGuardian(manager, shadowSize);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityCompanionFireElemental.class, new IRenderFactory<EntityCompanionFireElemental>() {
			@Override
			public Render<? super EntityCompanionFireElemental> createRenderFor(RenderManager manager) {
				return new RenderFireElementalCompanion(manager, shadowSize);
			}
		});

		// Projectiles and MISC.
		RenderingRegistry.registerEntityRenderingHandler(VoidChain.class, new IRenderFactory<VoidChain>() {
			@Override
			public Render<? super VoidChain> createRenderFor(RenderManager manager) {
				return new RenderVoidChain(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(AcidBall.class, new IRenderFactory<AcidBall>() {
			@Override
			public Render<? super AcidBall> createRenderFor(RenderManager manager) {
				return new RenderAcidBall(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(ProjectileDisintegration.class, new IRenderFactory<ProjectileDisintegration>() {
			@Override
			public Render<? super ProjectileDisintegration> createRenderFor(RenderManager manager) {
				return new RenderAcidBall(manager);
			}
		});
		// RenderingRegistry.registerEntityRenderingHandler(EntityHookShot.class, new RenderHook(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineFireball.class, new IRenderFactory<EntityHerobrineFireball>() {
			@Override
			public Render<? super EntityHerobrineFireball> createRenderFor(RenderManager manager) {
				return new RenderFireball(manager, 2.0F);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityLichInferno.class, new IRenderFactory<EntityLichInferno>() {
			@Override
			public Render<? super EntityLichInferno> createRenderFor(RenderManager manager) {
				return new RenderNull(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityObsidianFlask.class, new IRenderFactory<EntityObsidianFlask>() {
			@Override
			public Render<? super EntityObsidianFlask> createRenderFor(RenderManager manager) {
				return new RenderObsidianFlask(manager, Minecraft.getMinecraft().getRenderItem());
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntitySpellRune.class, new IRenderFactory<EntitySpellRune>() {
			@Override
			public Render<? super EntitySpellRune> createRenderFor(RenderManager manager) {
				return new RenderSpellRune(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityCasterLightningBolt.class, new IRenderFactory<EntityLightningBolt>() {
			@Override
			public Render<? super EntityLightningBolt> createRenderFor(RenderManager manager) {
				return new RenderLightningBolt(manager);
			}
		});
		RenderingRegistry.registerEntityRenderingHandler(EntitySpellImplosion.class, new IRenderFactory<EntitySpellImplosion>() {
			@Override
			public Render<? super EntitySpellImplosion> createRenderFor(RenderManager manager) {
				return new RenderSpellImplosion(manager);
			}
		});
	}

	@Override
	public void init() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNoBreak.class, new RenderNoBreak());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVoidicCharger.class, new RenderVoidicCharger());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVoidicAnchor.class, new RenderVoidicAnchor());

		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(VoidCraft.blocks.blockNoBreak), 0, TileEntityNoBreak.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(VoidCraft.blocks.voidicCharger), 0, TileEntityVoidicCharger.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(VoidCraft.blocks.voidicAnchor), 0, TileEntityVoidicAnchor.class);
	}

	@Override
	public void postInit() {
		// Events
		MinecraftForge.EVENT_BUS.register(new BossBarOverlay());
		MinecraftForge.EVENT_BUS.register(new BGMusic());
		MinecraftForge.EVENT_BUS.register(new DebugEvent());
		MinecraftForge.EVENT_BUS.register(new ClientInfusionOverlayRender());
		MinecraftForge.EVENT_BUS.register(new Tamaized.Voidcraft.client.RenderPlayer());
		MinecraftForge.EVENT_BUS.register(new Tamaized.Voidcraft.client.RenderLiving());
		MinecraftForge.EVENT_BUS.register(new Tamaized.Voidcraft.client.RenderSheathe());
		MinecraftForge.EVENT_BUS.register(new ClientRenderTicker());
		MinecraftForge.EVENT_BUS.register(new TextureStitch());

		RenderPlayer playerRenderer = (Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default"));
		playerRenderer.addLayer(new LayerVoidSpikes(playerRenderer));
		playerRenderer.addLayer(new LayerCustomElytra(playerRenderer));
		// playerRenderer.addLayer(new LayerSheath(playerRenderer));

		RenderPlayer playerRendererSlim = (Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim"));
		playerRendererSlim.addLayer(new LayerVoidSpikes(playerRendererSlim));
		playerRendererSlim.addLayer(new LayerCustomElytra(playerRendererSlim));
		// playerRendererSlim.addLayer(new LayerSheath(playerRendererSlim));

		VoidCraft.channel.register(new ClientPacketHandler());

	}

}
