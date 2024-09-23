import java.util.Scanner;

// Expense class representing each expense entry
class Expense {
    private String description;
    private double amount;
    private String category;

    // Default constructor (no parameters)
    public Expense() {
        this.description = "Unknown";
        this.amount = 0.0;
        this.category = "Miscellaneous";
    }

    // Parameterized constructor (with parameters)
    public Expense(String description, double amount, String category) {
        setDescription(description);  // Using mutators
        setAmount(amount);
        setCategory(category);
    }

    // Public Getter and Setter methods (Accessors and Mutators)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        if (amount > 0) {
            this.amount = amount;
        } else {
            System.out.println("Amount should be positive.");
        }
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Protected method: Only accessible within this class, package, and subclasses
    protected void printExpenseDetails() {
        System.out.println("Expense Details: " + getDescription() + ", Rs. " + getAmount() + ", " + getCategory());
    }

    @Override
    public String toString() {
        return "Description: " + getDescription() + ", Amount: Rs. " + getAmount() + ", Category: " + getCategory();  // Using accessors
    }
}

// ExpenseTracker class for managing expenses
class ExpenseTracker {
    // Private data members (encapsulation)
    private Expense[] expenses;
    private int count;

    // Default constructor (no parameters)
    public ExpenseTracker() {
        this(10); // By default, track up to 10 expenses
    }

    // Parameterized constructor
    public ExpenseTracker(int size) {
        setExpenses(new Expense[size]);
        setCount(0);
    }

    // Public Getter and Setter methods for private variables
    public Expense[] getExpenses() {
        return this.expenses;
    }

    private void setExpenses(Expense[] expenses) { // Private setter
        this.expenses = expenses;
    }

    public int getCount() {
        return this.count;
    }

    private void setCount(int count) {  // Private setter
        this.count = count;
    }

    // Public method to add an expense
    public void addExpense(Expense expense) {
        if (getCount() < getExpenses().length) {
            this.expenses[getCount()] = expense;
            setCount(getCount() + 1);
            System.out.println("Expense added successfully.");
        } else {
            System.out.println("Cannot add more expenses. Array is full.");
        }
    }

    // Public method to list all expenses
    public void listExpenses() {
        if (getCount() == 0) {
            System.out.println("No expenses recorded.");
        } else {
            for (int i = 0; i < getCount(); i++) {
                getExpenses()[i].printExpenseDetails();  // Calling protected method from Expense class
            }
        }
    }

    // Public method to list expenses by category
    public void listExpensesByCategory(String category) {
        boolean found = false;
        for (int i = 0; i < getCount(); i++) {
            if (getExpenses()[i].getCategory().equalsIgnoreCase(category)) {
                System.out.println(getExpenses()[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found for category: " + category);
        }
    }

    // Public method to show the summary of expenses
    public void showSummary() {
        double total = 0;
        System.out.println("\nExpense Summary:");
        for (int i = 0; i < getCount(); i++) {
            total += getExpenses()[i].getAmount();
            System.out.println(getExpenses()[i]);
        }
        System.out.println("Total Expenses: Rs. " + total);
    }
}

// Main class for user interaction
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of expenses you want to track: ");
        int size = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        ExpenseTracker expenseTracker;

        // Using default constructor if the size is not provided or size <= 0
        if (size <= 0) {
            expenseTracker = new ExpenseTracker();  // Using the default constructor
        } else {
            expenseTracker = new ExpenseTracker(size);  // Using the parameterized constructor
        }

        boolean running = true;
        while (running) {
            System.out.println("\nExpense Tracker:");
            System.out.println("1. Add Expense");
            System.out.println("2. List Expenses");
            System.out.println("3. Show Summary");
            System.out.println("4. List Expenses by Category");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter category (e.g., Food, Utilities, Entertainment): ");
                    String category = scanner.nextLine();
                    Expense expense = new Expense(description, amount, category);  // Using parameterized constructor
                    expenseTracker.addExpense(expense);
                    break;
                case 2:
                    expenseTracker.listExpenses();
                    break;
                case 3:
                    expenseTracker.showSummary();
                    break;
                case 4:
                    System.out.print("Enter category to list expenses (e.g., Food, Utilities, Entertainment): ");
                    category = scanner.nextLine();
                    expenseTracker.listExpensesByCategory(category);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the expense tracker");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
