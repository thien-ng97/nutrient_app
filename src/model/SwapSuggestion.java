package model;

import java.util.HashMap;
import java.util.Map;

public class SwapSuggestion {
	 private String originalItem;
	    private String substituteItem;
	    private Map<String, Double> nutrientChanges;

	    public SwapSuggestion(String originalItem, String substituteItem) {
	        this.originalItem = originalItem;
	        this.substituteItem = substituteItem;
	        this.nutrientChanges = new HashMap<>();
	    }

	    public String getOriginalItem() { return originalItem; }
	    public String getSubstituteItem() { return substituteItem; }
	    public Map<String, Double> getNutrientChanges() { return nutrientChanges; }
	    public void addNutrientChange(String nutrient, double delta) {
	        nutrientChanges.put(nutrient, delta);
	    }
}
