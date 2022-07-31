package thatpreston.mermod.item.modifier;

public enum NecklaceModifiers {
    MERMAID_BRA("mermaid_bra", "bra", true, 0, false),
    TAIL_GRADIENT("tail_gradient", "gradient", true, 0, false),
    GLOWING_PEARL("glowing_pearl", "glint", false, 16777060, true),
    MOON_ROCK("moon_rock", "texture", false, 16755968, false),
    URSULA_SHELL("ursula_shell", "texture", false, 16768000, false);
    private final String id;
    private final String category;
    private final boolean colorable;
    private final int tooltipColor;
    private final boolean hasGlint;
    NecklaceModifiers(String id, String category, boolean colorable, int tooltipColor, boolean hasGlint) {
        this.id = id;
        this.category = category;
        this.colorable = colorable;
        this.tooltipColor = tooltipColor;
        this.hasGlint = hasGlint;
    }
    public String getId() {
        return id;
    }
    public String getCategory() {
        return category;
    }
    public boolean isColorable() {
        return colorable;
    }
    public int getTooltipColor() {
        return tooltipColor;
    }
    public boolean getHasGlint() {
        return hasGlint;
    }
}