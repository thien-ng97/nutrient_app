package ui;

import service.FoodSwapService;
import model.SwapGoal;
import model.SwapSuggestion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Swing form for Deliverable 1: food-swap UI with structured goal input.
 */
public class SwapForm extends JFrame {
    private static final long serialVersionUID = 1L;

    private JComboBox<String> mealCombo;
    private JComboBox<String> nutrientCombo;
    private JComboBox<String> typeCombo;
    private JTextField amountField;
    private JButton findButton;
    private JTable resultTable;
    private FoodSwapService service = new FoodSwapService();

    public SwapForm(List<String> meals) {
        super("Food Swap Suggestions");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        mealCombo     = new JComboBox<>(meals.toArray(new String[0]));
        nutrientCombo = new JComboBox<>(new String[]{"Fiber"});
        // Use fully-qualified SwapGoal.Type
        typeCombo     = new JComboBox<>(new String[]{
                           SwapGoal.Type.INCREASE.name(),
                           SwapGoal.Type.DECREASE.name()});
        amountField   = new JTextField("2", 5);
        findButton    = new JButton("Get Swaps");

        top.add(new JLabel("Meal (comma-separated items):")); top.add(mealCombo);
        top.add(new JLabel("Nutrient:"));                          top.add(nutrientCombo);
        top.add(new JLabel("Type:"));                              top.add(typeCombo);
        top.add(new JLabel("Amount:"));                            top.add(amountField);
        top.add(findButton);
        add(top, BorderLayout.NORTH);

        resultTable = new JTable(new DefaultTableModel(
                          new Object[]{"Original","Substitute","Delta"}, 0));
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        findButton.addActionListener(e -> onFind());

        /**
         * The creation of the UI window and the buttons, as well as the documentation of this code was done with 
         * the help of AI (mainly the use of JLabel and also JOptionPane.
         */
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void onFind() {
        String meal     = (String) mealCombo.getSelectedItem();
        List<String> items = Arrays.asList(meal.split(","));
        String nutrient = (String) nutrientCombo.getSelectedItem();
        // fully-qualified enum
        SwapGoal.Type type = SwapGoal.Type.valueOf((String) typeCombo.getSelectedItem());
        double amount    = Double.parseDouble(amountField.getText());

        SwapGoal goal    = new SwapGoal(nutrient, type, amount);
        List<SwapSuggestion> suggestions = service.suggestSwaps(items, goal);

        DefaultTableModel m = (DefaultTableModel) resultTable.getModel();
        m.setRowCount(0);

        if (suggestions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No suitable swaps found");
        } else {
            for (SwapSuggestion s : suggestions) {
                m.addRow(new Object[]{
                    s.getOriginalItem(),
                    s.getSubstituteItem(),
                    s.getNutrientChanges().get(nutrient)
                });
            }
        }
    }

    /**
     * Demo main to launch stub for Deliverable 1.
     */
    public static void main(String[] args) {
        List<String> stubMeals = Arrays.asList("White bread,Butter");
        SwingUtilities.invokeLater(() -> new SwapForm(stubMeals));
    }
}
