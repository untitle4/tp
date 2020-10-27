package seedu.duke.model;

public class ConfigParameter {
    private String name;
    private int recommendedHours;

    public ConfigParameter(String name, int recommendedHours) {
        this.name = name;
        this.recommendedHours = recommendedHours;
    }

    public ConfigParameter() {
        this.name = "";
        this.recommendedHours = 0;
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
}
