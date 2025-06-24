package service;

import dao.FoodSwapDAO;
import model.SwapGoal;
import model.SwapSuggestion;
import java.util.ArrayList;
import java.util.List;

public class FoodSwapService {
	private FoodSwapDAO dao = new FoodSwapDAO();

    public List<SwapSuggestion> suggestSwaps(List<String> mealItems, SwapGoal goal) {
        List<SwapSuggestion> suggestions = new ArrayList<>();
        for (String original : mealItems) {
            for (String candidate : dao.findCandidates(original)) {
                double origVal = dao.getNutrientValue(original, goal.getNutrient(), 100);
                double candVal = dao.getNutrientValue(candidate, goal.getNutrient(), 100);
                double delta = candVal - origVal;

                boolean meets = (goal.getType() == SwapGoal.Type.INCREASE)
                                 ? delta >= goal.getAmount()
                                 : delta <= -goal.getAmount();

                if (meets) {
                    SwapSuggestion s = new SwapSuggestion(original, candidate);
                    s.addNutrientChange(goal.getNutrient(), delta);
                    suggestions.add(s);
                }
            }
        }
        return suggestions;
    }
}
