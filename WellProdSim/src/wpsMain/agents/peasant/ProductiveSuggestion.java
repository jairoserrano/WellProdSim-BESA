package wpsMain.agents.peasant;

import rational.data.InfoData;

public class ProductiveSuggestion extends InfoData {

    String name;

    public ProductiveSuggestion(String name) {
        super(name);
        this.name = name;
    }

    public String getSuggestedResourceName() {
        return name;
    }
}
