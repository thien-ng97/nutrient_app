package main_methods;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

import dao.FoodDAO;
import dao.NutrientDAO;
import model.FoodItem;
import model.MealDetails;
import model.Nutrient;
import service.GetMealNutrientDetails;

public class MainDemoOfFoodDAONutrientDAO {
	public static void main(String[] args) {
		// test FoodDAO.fetchFoodId()
		FoodItem foodItem = new FoodItem("Chop suey, with meat, canned", 2);
		FoodDAO foodDAO = new FoodDAO();
		foodDAO.fetchFoodId(foodItem);
		System.out.println(foodItem.getFoodId());
		
		// test FoodDAO.fetchFoodGroup()
		foodDAO.fetchFoodGroup(foodItem);
		System.out.println(foodItem.getFoodGroup());
		
		// test NutrientDAO.fetchNutrientValue(), expect output 4.07
		Nutrient nutrient = new Nutrient(203);	// 203 is the nutrient_id of PROTEIN
		NutrientDAO nutrientDAO = new NutrientDAO();
		nutrientDAO.fetchNutrientValue(nutrient, foodItem);
		System.out.println(nutrient.getQuantity());
		
		// test NutrientDAO.fetchNutrientUnit(), expect output "g"
		nutrientDAO.fetchNutrientUnit(nutrient);
		System.out.println(nutrient.getUnit());
		
		// test NutrientDAO.fetchNutrientName(), expect output "PROTEIN"
		nutrientDAO.fetchNutrientName(nutrient);
		System.out.println(nutrient.getNutrientName());
		
		// test NutrientDAO.fetchNutrientIds()
		List<Integer> list = nutrientDAO.fetchNutrientIds(4);
		System.out.println("nutrient_ids: " + list);
		
	}

}
