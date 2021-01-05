//Creates a non-player damaging block type that explodes on right click
//Destruction types:
//Break -- breaks and drops all blocks
//Destroy -- breaks and randomly drops blocks (tnt)
//None -- no breaking or dropping
//
//Note: uses vanilla explosions so excessive power levels WILL cause excessive lag
package net.prug.prugutils.main;



import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class ConfigurableExplosiveBlock extends Block {

    private final float power;
    private final boolean createFire;
    private final DestructionType destructionType;

    public ConfigurableExplosiveBlock(Settings settings, float power, boolean createFire, DestructionType destructionType) {
        super(settings);
        this.power = power;
        this.createFire = createFire;
        this.destructionType = destructionType;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            
            Explosion explosion = new Explosion(world, null, null, null, (double)(pos.getX()), (double)(pos.getY()), (double)(pos.getZ()), power, createFire, destructionType);
            world.removeBlock(pos, false);
            explosion.collectBlocksAndDamageEntities();
            explosion.affectWorld(true);
        }
 
        return ActionResult.SUCCESS;
    }
}