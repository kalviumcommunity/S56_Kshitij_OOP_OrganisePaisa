import java.util.ArrayList;

// Expense class representing each expense entry
class Expense {
    private String description;
    private double amount;
    private String category;

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

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
    private ArrayList<Expense> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void listExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded");
        } else {
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();
        
        //Creating Expense objects
        Expense expense1 = new Expense("Lunch", 10.20, "Food");
        Expense expense2 = new Expense("Electricity Bill", 60, "Utilities");
        
        //Adding Expense objects to ExpenseTracker
        expenseTracker.addExpense(expense1);
        expenseTracker.addExpense(expense2);
        
        //Listing all expenses
        expenseTracker.listExpenses();
    }
}
