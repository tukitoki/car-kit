package cs.vsu.raspopov.carkit.entity.enums;

public enum DetailEnum {
    OIL_FILTER("Масляный фильтр"),
    OIL("Масло"),
    AIR_FILTER("Воздушный фильтр"),
    CABIN_FILTER("Кабиный фильтр"),
    FRONT_BRAKE_PADS("Передние тормозные колодки"),
    REAR_BRAKE_PADS("Задние тормозные колодки"),
    FRONT_BRAKE_DISC("Передний тормозной диск"),
    REAR_BRAKE_DISC("Задний тормозной диск"),
    BRAKE_DRUM("Тормозной барабан"),
    SPARK_PLUG("Свеча зажигания"),
    IGNITION_COIL("Катушка зажигания"),
    DRIVER_WIPER_BLADE("Щетка стеклоочистителя водителя"),
    PASSENGER_WIPER_BLADE("Щетка стеклоочистителя пассажира"),
    REAR_WIPER_BLADE("Щетка стеклоочистителя задняя"),
    DRAIN_PLUG("Сливная пробка"),
    FRONT_WHEEL_BEARING("Передний ступичный подшипник"),
    REAR_WHEEL_BEARING("Задний ступичный подшипник"),
    MIDDLE_SHOCK_ABSORBER("Средний амортизатор"),
    REAR_SHOCK_ABSORBER("Задний амортизатор"),
    FRONT_SHOCK_ABSORBER_SUPPORT("Опора переднего амортизатора")
    ;

    private final String name;

    DetailEnum(String name) {
        this.name = name;
    }

    public static DetailEnum fromName(String name) {
        for (DetailEnum detailEnum : DetailEnum.values()) {
            if (detailEnum.name.equalsIgnoreCase(name)) {
                return detailEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
