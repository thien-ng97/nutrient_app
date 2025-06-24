package ui;

import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Meal;
import service.MealLoggingService;

public class MealLoggingForm extends JFrame{
	private Meal meal;
	
	public MealLoggingForm() {
		setTitle("Meal Logging");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(8,2));
		
		JTextField userField = new JTextField();
		JTextField dateField = new JTextField("YYYY-MM-DD");
		JComboBox<String> mealTypeBox = new JComboBox<>(new String[] {"breakfast","lunch","dinner","snack"});
		JTextField ingredientField = new JTextField("Please add ingredient one by one");
		JTextField quantityField = new JTextField();
		
		JButton addButton = new JButton("Add ingredient");
		JButton submitButton = new JButton("Log this meal");
		
		add(new JLabel("user_id")); add(userField);
		add(new JLabel("Date of meal")); add(dateField);
		add(new JLabel("Type of meal")); add(mealTypeBox);
		add(new JLabel("Ingredient")); add(ingredientField);
		add(new JLabel("Quantity")); add(quantityField);
		
		add(new JLabel("")); add(addButton);
		add(new JLabel("")); add(submitButton);
		
		
		addButton.addActionListener(e -> {
			if (this.meal == null) {
				this.meal = new Meal(Integer.parseInt(userField.getText()),
						(String)mealTypeBox.getSelectedItem(),
						LocalDate.parse(dateField.getText()));
			}
			this.meal.addItems((String)ingredientField.getText(), Integer.parseInt(quantityField.getText()));
			
			JOptionPane.showMessageDialog(this, "Ingredient added!");
		});
			submitButton.addActionListener(e -> {
				new MealLoggingService().logMeal(this.meal);
				JOptionPane.showMessageDialog(this, "Meal saved!");
			
		});
			
			setVisible(true);
	}
	
}
