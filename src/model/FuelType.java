package model;
public enum FuelType {

    UNDEFINED,
    DIESEL,
    GASOLINE,
    ELECTRIC;

    public String toString() {
        String name = name().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
