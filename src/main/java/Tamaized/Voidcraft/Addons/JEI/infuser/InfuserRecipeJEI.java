package Tamaized.Voidcraft.Addons.JEI.infuser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.machina.addons.TERecipeInfuser.InfuserRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

public class InfuserRecipeJEI extends BlankRecipeWrapper {

	private final InfuserRecipe recipe;

	public InfuserRecipeJEI(InfuserRecipe r) {
		recipe = r;
	}

	@Override
	@Nonnull
	public List getInputs() {
		ArrayList<Collection> ret = new ArrayList<Collection>();
		ret.add(Collections.singletonList(voidCraft.fluids.getBucket()));
		for(ItemStack stack : recipe.getInput()){
			ret.add(Collections.singletonList(stack));
		}
		return ret;
	}

	@Override
	@Nonnull
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(recipe.getOutput());
	}

	@Override
	public void getIngredients(IIngredients ingredients) {

	}

}
