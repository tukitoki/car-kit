package cs.vsu.raspopov.carkit.entity.enums;

public enum DetailType {
    OIL_FILTER,
    AIR_FILTER,
    CABIN_FILTER,
    FRONT_BRAKE_PADS,
    REAR_BRAKE_PADS,
    FRONT_BRAKE_DISC,
    REAR_BRAKE_DISC,
    BRAKE_DRUM,
    SPARK_PLUG,
    IGNITION_COIL,
    DRIVER_WIPER_BLADE,
    PASSENGER_WIPER_BLADE,
    REAR_WIPER_BLADE,
    DRAIN_PLUG,
    FRONT_WHEEL_BEARING,
    REAR_WHEEL_BEARING,
    MIDDLE_SHOCK_ABSORBER,
    REAR_SHOCK_ABSORBER,
    FRONT_SHOCK_ABSORBER_SUPPORT
    ;

    @Override
    public String toString() {
        return this.name();
    }
}
