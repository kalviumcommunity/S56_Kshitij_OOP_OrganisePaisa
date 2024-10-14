import java.util.Scanner;

// Base class representing each expense entry
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

// Single Inheritance: Subclass for recurring expenses
class RecurringExpense extends Expense {
    private String frequency; // e.g., monthly, weekly

    public RecurringExpense(String description, double amount, String category, String frequency) {
        super(description, amount, category); // Call superclass constructor
        this.frequency = frequency;
    }

    // Getter and setter for frequency
    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    // Overriding printExpenseDetails method
    @Override
    protected void printExpenseDetails() {
        super.printExpenseDetails();
        System.out.println("Frequency: " + getFrequency());
    }
}

// Hierarchical Inheritance: Subclass for one-time expenses
class OneTimeExpense extends Expense {
    private String date; // e.g., date of the expense

    public OneTimeExpense(String description, double amount, String category, String date) {
        super(description, amount, category); // Call superclass constructor
        this.date = date;
    }

    // Getter and setter for date
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Overriding printExpenseDetails method
    @Override
    protected void printExpenseDetails() {
        super.printExpenseDetails();
        System.out.println("Date: " + getDate());
    }
}

// ExpenseTracker class for managing expenses
class ExpenseTracker {
    private Expense[] expenses;
    private int count;

    public ExpenseTracker() {
        this(10); // By default, track up to 10 expenses
    }

    public ExpenseTracker(int size) {
        setExpenses(new Expense[size]);
        setCount(0);
    }

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

    // Method overloading to demonstrate polymorphism
    public void addExpense(Expense expense) {
        if (getCount() < getExpenses().length) {
            this.expenses[getCount()] = expense;
            setCount(getCount() + 1);
            System.out.println("Expense added successfully.");
        } else {
            System.out.println("Cannot add more expenses. Array is full.");
        }
    }

    // Overloaded addExpense method (Compile-time Polymorphism)
    public void addExpense(String description, double amount, String category) {
        Expense newExpense = new Expense(description, amount, category);  // Create Expense object
        addExpense(newExpense);  // Reusing the other addExpense method
    }

    public void listExpenses() {
        if (getCount() == 0) {
            System.out.println("No expenses recorded.");
        } else {
            for (int i = 0; i < getCount(); i++) {
                getExpenses()[i].printExpenseDetails();  // Calling protected method from Expense class
            }
        }
    }

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

        if (size <= 0) {
            expenseTracker = new ExpenseTracker();  // Using the default constructor
        } else {
            expenseTracker = new ExpenseTracker(size);  // Using the parameterized constructor
        }

        boolean running = true;
        while (running) {
            System.out.println("\nExpense Tracker:");
            System.out.println("1. Add One-Time Expense");
            System.out.println("2. Add Recurring Expense");
            System.out.println("3. List Expenses");
            System.out.println("4. Show Summary");
            System.out.println("5. List Expenses by Category");
            System.out.println("6. Exit");
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
                    System.out.print("Enter date (e.g., 2024-09-15): ");
                    String date = scanner.nextLine();
                    OneTimeExpense oneTimeExpense = new OneTimeExpense(description, amount, category, date);
                    expenseTracker.addExpense(oneTimeExpense);
                    break;
                case 2:
                    System.out.print("Enter description: ");
                    description = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    amount = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter category (e.g., Food, Utilities, Entertainment): ");
                    category = scanner.nextLine();
                    System.out.print("Enter frequency (e.g., Monthly, Weekly): ");
                    String frequency = scanner.nextLine();
                    RecurringExpense recurringExpense = new RecurringExpense(description, amount, category, frequency);
                    expenseTracker.addExpense(recurringExpense);
                    break;
                case 3:
                    expenseTracker.listExpenses();
                    break;
                case 4:
                    expenseTracker.showSummary();
                    break;
                case 5:
                    System.out.print("Enter category to list expenses (e.g., Food, Utilities, Entertainment): ");
                    category = scanner.nextLine();
                    expenseTracker.listExpensesByCategory(category);
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting the expense tracker.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
