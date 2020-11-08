package seedu.duke.model;

public class ConfigParameter {
    private String name;
    private int recommendedHours;
    private boolean hasProgramRan;

    public ConfigParameter(String name, int recommendedHours, boolean hasProgramRan) {
        this.name = name;
        this.recommendedHours = recommendedHours;
        this.hasProgramRan = hasProgramRan;
    }

    public ConfigParameter() {
        this.name = "";
        this.recommendedHours = 1;
        this.hasProgramRan = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecommendedHours() {
        return recommendedHours;
    }

    public void setRecommendedHours(int recommendedHours) {
        this.recommendedHours = recommendedHours;
    }

    public boolean getHasProgramRan() {
        return hasProgramRan;
    }

    public void setHasProgramRan(boolean hasProgramRan) {
        this.hasProgramRan = hasProgramRan;
    }

}
