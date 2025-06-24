package main_methods;

import java.time.LocalDate;

import dao.MealDAO;
import model.Meal;

public class MainToFetchMeal {
	public static void main(String[] args) {
		// demo fetching a meal from database
		MealDAO mealDAO = new MealDAO();
		Meal meal = mealDAO.fetchMeal(3, LocalDate.parse("2011-01-01"), "dinner");
		System.out.println(meal.getUserId());
		System.out.println(meal.getDate());
		System.out.println(meal.getMealType());
		System.out.println(meal.getItems());
		

	}
}
