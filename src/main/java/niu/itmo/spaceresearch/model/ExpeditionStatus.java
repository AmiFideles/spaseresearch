package niu.itmo.spaceresearch.model;

/**
 * @author amifideles
 */
public enum ExpeditionStatus {
    IN_PROGRESS,
    LANDED,
    COMPLETED;

    public String toRussian() {
        return switch (this) {
            case IN_PROGRESS -> "В полете";
            case LANDED -> "Приземление";
            case COMPLETED -> "Завершена";
        };
    }
}
