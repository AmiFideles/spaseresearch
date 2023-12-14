package niu.itmo.spaceresearch.model;

/**
 * @author amifideles
 */
public enum Gender {
    MALE,
    FEMALE;

    public String toRussian() {
        return switch (this) {
            case MALE -> "Мужчина";
            case FEMALE -> "Женщина";
        };
    }
}
