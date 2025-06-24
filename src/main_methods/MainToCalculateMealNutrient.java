package main_methods;

import java.time.LocalDate;

import model.FoodItem;
import model.MealDetails;
import service.GetMealNutrientDetails;

public class MainToCalculateMealNutrient {
	public static void main(String[] args) {
		// !!!!!!!!GetMealNutrientDetails class works slowly
		//test getMealNutrientDetails.fetchFoodItem(), expect output 4.07 (a little slow)
		GetMealNutrientDetails getMealNutrientDetails = new GetMealNutrientDetails();
		FoodItem foodItem = getMealNutrientDetails.fetchFoodItem("Chop suey, with meat, canned", 2);
		System.out.println(foodItem.getNutritionFacts().get(0).getQuantity());
		
		// get MealDetails (contains nutrient data) representing a specific meal 
		MealDetails mealDetails = getMealNutrientDetails.fetchMealDetails(1, LocalDate.parse("2010-01-01"), "lunch");
		System.out.println(mealDetails.getFoodItems().get(0).getItemName());
		System.out.println(mealDetails.getFoodItems().get(1).getItemName());

	}
}
