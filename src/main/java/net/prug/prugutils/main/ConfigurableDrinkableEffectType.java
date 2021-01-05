package net.prug.prugutils.main;

import net.minecraft.item.MilkBucketItem;
import org.jetbrains.annotations.Nullable;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class ConfigurableDrinkableEffectType extends MilkBucketItem {

    private final StatusEffect type1;
    private final int durration1;
    private final int multiplyer1;
    private final StatusEffect type2;
    private final int durration2;
    private final int multiplyer2;
    private final ItemConvertible returnItem;


  //Use for single effect
    public ConfigurableDrinkableEffectType(Settings settings, StatusEffect type1, int durration1, int multiplyer1, @Nullable ItemConvertible returnItem) {
      this(settings, type1, durration1, multiplyer1, null, 0, 0, returnItem);
    }
  //Use for double effect
    public ConfigurableDrinkableEffectType(Settings settings, StatusEffect type1, int durration1, int multiplyer1, @Nullable StatusEffect type2, @Nullable int durration2, @Nullable int multiplyer2, @Nullable ItemConvertible returnItem) {
        super(settings.maxCount(1));
        this.type1 = type1;
        this.durration1 = durration1;
        this.multiplyer1 = multiplyer1;
        this.type2 = type2;
        this.durration2 = durration2;
        this.multiplyer2 = multiplyer2;
        this.returnItem = returnItem;
    }
    
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity) {
           ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)user;
           Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
           serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
  
        if (user instanceof PlayerEntity && !((PlayerEntity)user).abilities.creativeMode) {
           stack.decrement(1);
        }
  
        if (!world.isClient) {
           user.addStatusEffect(new StatusEffectInstance(type1, durration1, multiplyer1));
           if (type2 != null){
            user.addStatusEffect(new StatusEffectInstance(type2, durration2, multiplyer2));
           }
        }
            if (returnItem != null){
               return stack.isEmpty() ? new ItemStack(returnItem) : stack;
            } else {
               return stack;
            }
         
     }
}
