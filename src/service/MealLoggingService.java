package service;

import dao.MealDAO;
import model.Meal;

public class MealLoggingService {
	private MealDAO mealDAO = new MealDAO();
	
	public void logMeal(Meal meal) {
		mealDAO.logMeal(meal);
	}
}
