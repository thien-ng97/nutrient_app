package model;

public class SwapGoal {
	/**
     * Type of goal: INCREASE to raise nutrient intake, DECREASE to lower it.
     */
    public enum Type { INCREASE, DECREASE }

    /** The name of the nutrient (e.g., "Fiber"). */
    private String nutrient;
    /** The goal type (INCREASE or DECREASE). */
    private Type type;
    /** The desired amount change (positive number, units depend on nutrient). */
    private double amount;

    /**
     * Constructs a new SwapGoal.
     *
     * @param nutrient the nutrient to target
     * @param type     whether to increase or decrease the nutrient
     * @param amount   the magnitude of change desired
     */
    public SwapGoal(String nutrient, Type type, double amount) {
        this.nutrient = nutrient;
        this.type     = type;
        this.amount   = amount;
    }

    /**
     * @return the nutrient name this goal targets
     */
    public String getNutrient() { return nutrient; }

    /**
     * @return the goal type (INCREASE or DECREASE)
     */
    public Type getType() { return type; }

    /**
     * @return the amount by which to change the nutrient
     */
    public double getAmount() { return amount; }
}
