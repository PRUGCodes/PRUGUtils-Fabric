package net.prug.prugutils.main;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class PrugUtils implements ModInitializer {

	public static final ConfigurableExplosiveBlock MINING_CHARGE = new ConfigurableExplosiveBlock(
			FabricBlockSettings.of(Material.METAL).hardness(4.0f), 8.0f, false, DestructionType.NONE);
	public static final Item PLAYERPROTEKEQU = new Item(new Item.Settings().group(PrugUtils.PRUGBLOCKS));
	public static final ConfigurableDrinkableEffectType SPEEDSODA = new ConfigurableDrinkableEffectType(
			new Item.Settings().group(PrugUtils.PRUGBLOCKS), StatusEffect.byRawId(1), 200, 0, StatusEffect.byRawId(2),
			200, 0, PrugUtils.MINING_CHARGE);

	public static final ItemGroup PRUGBLOCKS = FabricItemGroupBuilder
			.create(new Identifier("prugutils", "prugblocks")).icon(() -> new ItemStack(PrugUtils.MINING_CHARGE))
			.appendItems(stacks -> {
				stacks.add(new ItemStack(PrugUtils.MINING_CHARGE));
				stacks.add(new ItemStack(PrugUtils.PLAYERPROTEKEQU));
				stacks.add(new ItemStack(PrugUtils.SPEEDSODA));
			}).build();

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier("prugutils", "miningcharge"), MINING_CHARGE);
		Registry.register(Registry.ITEM, new Identifier("prugutils", "miningcharge"),
				new BlockItem(MINING_CHARGE, new Item.Settings().group(PrugUtils.PRUGBLOCKS)));
		Registry.register(Registry.ITEM, new Identifier("prugutils", "player_protek_equ"), PLAYERPROTEKEQU);
		Registry.register(Registry.ITEM, new Identifier("prugutils", "soda_speed"), SPEEDSODA);
	}
}


