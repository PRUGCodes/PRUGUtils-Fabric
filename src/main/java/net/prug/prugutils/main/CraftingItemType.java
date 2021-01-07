package net.prug.prugutils.main;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class CraftingItemType extends Item {

    private final String toolTipTraslation;

    public CraftingItemType(Settings settings, String toolTipTraslation) {
        super(settings);
        // TODO Auto-generated constructor stub
        this.toolTipTraslation = toolTipTraslation;
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText(toolTipTraslation));
    }

}
