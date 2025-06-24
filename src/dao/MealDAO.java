
package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.Map;

import model.Meal;
import util.DatabaseManager;

// may be changed to utility class later, access database table meals
// !!!!!!!!!!!!!can try to refactor this to use TEMPLETE METHOD later
public class MealDAO {
	
	// load Meal data into database table meals
	public void logMeal(Meal meal) {
		String sqlToInsert = "INSERT INTO meals (user_id, meal_type, date, ingredient, quantity) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseManager.getInstance().getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sqlToInsert)) {
			
			stmt.setInt(1, meal.getUserId());
			stmt.setString(2, meal.getMealType());
			stmt.setDate(3, Date.valueOf(meal.getDate()));
			for (Map.Entry<String, Integer> entry : meal.getItems().entrySet()) {
				String ingredient = entry.getKey();
				Integer quantity = entry.getValue();
				stmt.setString(4, ingredient);
				stmt.setInt(5, quantity);
				stmt.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public Meal fetchMeal(int id, LocalDate date, String mealType) {
		String sqlToSelectItems = "SELECT ingredient, quantity FROM meals WHERE user_id = ? AND meal_type = ? AND date = ?";
		Meal meal = new Meal(id, mealType, date);
		try (Connection conn = DatabaseManager.getInstance().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlToSelectItems)) {
				
			stmt.setInt(1, id);
			stmt.setString(2, mealType);
			stmt.setDate(3, Date.valueOf(date));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				meal.addItems(rs.getString(1), rs.getInt(2));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return meal;
	}
	

	

}
