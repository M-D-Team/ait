package mdteam.ait.core.components.block.interior_door;

import dev.onyxstudios.cca.api.v3.component.Component;

public interface InteriorDataComponent extends Component {

    float getLeftDoorRotation();

    float getRightDoorRotation();

    void setLeftDoorRotation(float newRot);

    void setRightDoorRotation(float newRot);
}