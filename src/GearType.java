public enum GearType {
    
    UNDEFINED,
    NORMAL,
    AUTOMATIC;
    
    public String toString() {
        String name = name().toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}