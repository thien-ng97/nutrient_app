package main_methods;

import java.time.LocalDate;
import java.util.List;

import dao.FoodDAO;
import dao.NutrientDAO;
import model.FoodItem;
import model.Meal;
import model.MealDetails;
import model.Nutrient;
import service.GetMealNutrientDetails;
import service.SwapFoodItem;

// This class is to show the food swapping functionality
// !!!!!!!! it is slow

public class MainToDemoFoodSwapping {
	public static void main(String[] args) {	
		// create a Meal object
		/*
		 * Assume user 1 had a lunch on 2011-01-01 and 
		 * user 1 had 2 "Chop suey", 4 "Beef pot roast" in that meal.
		 * 
		 */
		Meal meal = new Meal(1, "lunch", LocalDate.parse("2011-01-01"));
		meal.addItems("Chop suey", 2);
		meal.addItems("Beef pot roast", 4);
		
		// now create a MealDetail object base on meal
		// !!!!!!!!!!!!!!! it is slow
		/*
		 * it contains FoodItems, and a FoodItem contains NutritionFacts, 
		 * which is a List of Nutrient objects.  
		 */
		GetMealNutrientDetails getMealNutrientDetails = new GetMealNutrientDetails();
		MealDetails mealDetails = getMealNutrientDetails.fetchMealDetails(meal);
		
		// print MealDetails before swapping
		System.out.println("Before swapping: \n");
		printItemsInMealDetails(mealDetails);
		
		// then create FoodItem objects itemToBeRepalced and newItem
		/*
		 * Assume we need to use 3 "Fried chicken" to replace 
		 * 3 of the 4 "Beef pot roast" in user 1's lunch
		 */
		FoodItem foodItemToBeReplaced = new FoodItem("Beef pot roast", 3);
		FoodItem newFoodItem = new FoodItem("Fried chicken", 3);
		
		// swap food items
		SwapFoodItem swapFoodItem = new SwapFoodItem();
		swapFoodItem.swap(mealDetails, foodItemToBeReplaced, newFoodItem);
		System.out.println("====================================================");
		System.out.println("\n\nAfter swapping:\n");
		printItemsInMealDetails(mealDetails);

	}
	
	public static void printItemsInMealDetails(MealDetails mealdetails) {
		List<FoodItem> foodItems = mealdetails.getFoodItems();
		System.out.println("Print food items: ");
		System.out.println("--item name-|-item food group-|-item quantity-------");
		for (FoodItem foodItem : foodItems) {
			System.out.println("  " + foodItem.getItemName() + 
					" | " + foodItem.getFoodGroup() +
					" | " + foodItem.getQuantity());			
		}
	}
}
