package mdteam.ait.core.blockentities;

import mdteam.ait.client.renderers.exteriors.ExteriorEnum;
import mdteam.ait.client.renderers.exteriors.MaterialStateEnum;
import mdteam.ait.core.AITBlockEntityTypes;
import mdteam.ait.core.helper.TardisUtil;
import mdteam.ait.core.helper.desktop.TARDISDesktop;
import mdteam.ait.core.tardis.Tardis;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.UUID;

import static mdteam.ait.AITMod.EXTERIORNBT;

public class ExteriorBlockEntity extends BlockEntity {
    public ExteriorBlockEntity(BlockPos pos, BlockState state) {
        super(AITBlockEntityTypes.EXTERIOR_BLOCK_ENTITY_TYPE, pos, state);
        setExterior(getExterior());
        setMaterialState(getMaterialState());
        setLeftDoorRot(getLeftDoorRotation());
        setRightDoorRot(getRightDoorRotation());
    }

    public static void tick(World world1, BlockPos pos, BlockState state1, ExteriorBlockEntity be) {

    }
    public void onPlace(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
//        if (placer != null) {
//            world.breakBlock(pos,false);
//            TARDISUtil.create(new AbsoluteBlockPos(world,placer.getMovementDirection().getOpposite(),pos));
//        }
    }
    public void useOn(BlockHitResult hit, BlockState state, PlayerEntity player, World world, boolean sneaking) {
        if(getLeftDoorRotation() == 0) {
            setLeftDoorRot(1.2f);
        } else {
            setLeftDoorRot(0);
        }
        world.playSound(null,this.pos, SoundEvents.BLOCK_IRON_DOOR_OPEN, SoundCategory.BLOCKS,0.6f, 1f);
        if(!sneaking) onEntityCollision(state, world, this.getPos(), player); else System.out.println(this.getTardisUuid());
    }

    public UUID getTardisUuid() {
        System.out.println("@!!!" + EXTERIORNBT.get(this).getTardisUuid());
        return EXTERIORNBT.get(this).getTardisUuid();
    }
    public Tardis getTardis() {
        System.out.println("@!!!" + TardisUtil.getTardisFromUuid(getTardisUuid()));
        return TardisUtil.getTardisFromUuid(getTardisUuid());
    }
    public Tardis tardis() {
        return this.getTardis();
    }
    public void link(Tardis tardis) {
        EXTERIORNBT.get(this).setTardisUuid(tardis.getUuid());
    }
    public void setExterior(ExteriorEnum exterior) {
        EXTERIORNBT.get(this).setExterior(exterior);
    }

    public ExteriorEnum getExterior() {
        return EXTERIORNBT.get(this).getExterior();
    }
    public TARDISDesktop getDesktop() {
        return this.getTardis().getDesktop();
    }

    public void setLeftDoorRot(float rotation) {
        EXTERIORNBT.get(this).setLeftDoorRotation(rotation);
    }

    public void setRightDoorRot(float rotation) {
        EXTERIORNBT.get(this).setRightDoorRotation(rotation);
    }

    public float getLeftDoorRotation() {
        return EXTERIORNBT.get(this).getLeftDoorRotation();
    }

    public float getRightDoorRotation() {
        return EXTERIORNBT.get(this).getRightDoorRotation();
    }

    public void setMaterialState(MaterialStateEnum materialState) {
        EXTERIORNBT.get(this).setMaterialState(materialState);
    }

    public MaterialStateEnum getMaterialState() {
        return EXTERIORNBT.get(this).getCurrentMaterialState();
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(getLeftDoorRotation() > 0 || getRightDoorRotation() > 0) {
            getDesktop().teleportToDoor(entity);
            if (getDesktop() != null && getDesktop().needsGeneration()) {
                getDesktop().generate();
            }
        }
    }
}
