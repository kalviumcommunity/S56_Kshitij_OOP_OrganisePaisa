// Expense class representing each expense entry
class Expense {
    private String description;
    private double amount;
    private String category;

    public Expense(String description, double amount, String category) {
        description = description;  // Assigning parameters to instance variables
        amount = amount;
        category = category;
    }

    // Getter methods
    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Amount: $" + amount + ", Category: " + category;
    }
}

// ExpenseTracker class for managing expenses
class ExpenseTracker {

    public ExpenseTracker() {
        // Constructor
    }

    public void addExpense(Expense expense) {
        // Placeholder method for adding expenses
    }

    public void listExpenses() {
        // Placeholder method for listing expenses
    }

    public void showSummary() {
        // Placeholder method for showing summary
    }
}

// Main class 
public class Main {
    public static void main(String[] args) {
        // Creating an instance of ExpenseTracker
        ExpenseTracker expenseTracker = new ExpenseTracker();
    }
}
