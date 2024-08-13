// Expense class representing each expense entry
class Expense {
    private String description;
    private double amount;
    private String category;

    public Expense(String description, double amount, String category) {
        this.description = description;  // Using 'this' to refer to instance variables
        this.amount = amount;
        this.category = category;
    }

    // Getter methods
    public String getDescription() {
        return this.description;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getCategory() {
        return this.category;
    }

    @Override
    public String toString() {
        return "Description: " + this.description + ", Amount: $" + this.amount + ", Category: " + this.category;
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
