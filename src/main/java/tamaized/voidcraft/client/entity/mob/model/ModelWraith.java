// Date: 8/6/2014 12:40:17 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package tamaized.voidcraft.client.entity.mob.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWraith extends ModelBase {
	// fields
	ModelRenderer Head;
	ModelRenderer Headwear;
	ModelRenderer Body;
	ModelRenderer RightArm;
	ModelRenderer LeftArm;
	// ModelRenderer ;

	public ModelWraith() {
		textureWidth = 64;
		textureHeight = 32;

		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-4F, -8F, -4F, 8, 8, 8);
		Head.setRotationPoint(0F, -14F, 0F);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		Headwear = new ModelRenderer(this, 0, 16);
		Headwear.addBox(-4F, -8F, -4F, 8, 8, 8);
		Headwear.setRotationPoint(0F, -14F, 0F);
		Headwear.setTextureSize(64, 32);
		Headwear.mirror = true;
		setRotation(Headwear, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 36, 0);
		Body.addBox(-4F, -1F, -2F, 8, 13, 4);
		Body.setRotationPoint(0F, -12F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		RightArm = new ModelRenderer(this, 56, 6);
		RightArm.addBox(-1F, 0F, -1F, 2, 12, 2);
		RightArm.setRotationPoint(-5F, -12F, -4F);
		RightArm.setTextureSize(64, 32);
		RightArm.mirror = true;
		setRotation(RightArm, 0F, 0F, 0F);
		// LeftArm.mirror = true;
		LeftArm = new ModelRenderer(this, 56, 6);
		LeftArm.addBox(-1F, 0F, -1F, 2, 12, 2);
		LeftArm.setRotationPoint(7F, -12F, -4F);
		LeftArm.setTextureSize(64, 32);
		LeftArm.mirror = true;
		setRotation(LeftArm, 0F, 0F, 0F);
		LeftArm.mirror = false;
		// = new ModelRenderer(this, 0, 0);
		// .addBox(0F, 0F, 0F, 1, 1, 1);
		// .setRotationPoint(0F, -19F, 0F);
		// .setTextureSize(64, 32);
		// .mirror = true;
		// setRotation(, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Head.render(f5);
		Headwear.render(f5);
		Body.render(f5);
		RightArm.render(f5);
		LeftArm.render(f5);
		// .render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float limbSwingAmount, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, limbSwingAmount, f2, f3, f4, f5, entity);

		Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		Head.rotateAngleY = f3 / (180F / (float) Math.PI);
		Headwear.rotateAngleX = f4 / (180F / (float) Math.PI);
		Headwear.rotateAngleY = f3 / (180F / (float) Math.PI);

		float maxSwing = 1.0F;

		LeftArm.rotateAngleX = limbSwingAmount * maxSwing;
		RightArm.rotateAngleX = limbSwingAmount * maxSwing;

	}

}
