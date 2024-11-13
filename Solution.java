import java.util.Scanner;

// Abstract base class representing each expense entry
abstract class Expense {
    private String description;
    private double amount;
    private String category;

    public Expense() {
        this.description = "Unknown";
        this.amount = 0.0;
        this.category = "Miscellaneous";
    }

    public Expense(String description, double amount, String category) {
        setDescription(description);
        setAmount(amount);
        setCategory(category);
    }

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

    public abstract void printExpenseDetails();

    @Override
    public String toString() {
        return "Description: " + getDescription() + ", Amount: Rs. " + getAmount() + ", Category: " + getCategory();
    }
}

// Subclass for recurring expenses
class RecurringExpense extends Expense {
    private String frequency;

    public RecurringExpense(String description, double amount, String category, String frequency) {
        super(description, amount, category);
        this.frequency = frequency;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public void printExpenseDetails() {
        System.out.println("Expense Details: " + getDescription() + ", Rs. " + getAmount() + ", " + getCategory());
        System.out.println("Frequency: " + getFrequency());
    }
}

// Subclass for one-time expenses
class OneTimeExpense extends Expense {
    private String date;

    public OneTimeExpense(String description, double amount, String category, String date) {
        super(description, amount, category);
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public void printExpenseDetails() {
        System.out.println("Expense Details: " + getDescription() + ", Rs. " + getAmount() + ", " + getCategory());
        System.out.println("Date: " + getDate());
    }
}

// New type of expense: GiftExpense (demonstrates Open/Closed Principle)
class GiftExpense extends Expense {
    private String recipient;

    public GiftExpense(String description, double amount, String category, String recipient) {
        super(description, amount, category);
        this.recipient = recipient;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public void printExpenseDetails() {
        System.out.println("Expense Details: " + getDescription() + ", Rs. " + getAmount() + ", " + getCategory());
        System.out.println("Recipient: " + getRecipient());
    }
}

// ExpenseTracker class for managing expenses
class ExpenseTracker {
    private Expense[] expenses;
    private int count;

    public ExpenseTracker() {
        this(10);
    }

    public ExpenseTracker(int size) {
        this.expenses = new Expense[size];
        this.count = 0;
    }

    public void addExpense(Expense expense) {
        if (count < expenses.length) {
            expenses[count++] = expense;
            System.out.println("Expense added successfully.");
        } else {
            System.out.println("Cannot add more expenses. Array is full.");
        }
    }

    public void listExpenses() {
        if (count == 0) {
            System.out.println("No expenses recorded.");
        } else {
            for (int i = 0; i < count; i++) {
                expenses[i].printExpenseDetails();
            }
        }
    }

    public void listExpensesByCategory(String category) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (expenses[i].getCategory().equalsIgnoreCase(category)) {
                System.out.println(expenses[i]);
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
        for (int i = 0; i < count; i++) {
            total += expenses[i].getAmount();
            System.out.println(expenses[i]);
        }
        System.out.println("Total Expenses: Rs. " + total);
    }
}

// Main class for user interaction
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of expenses you want to track: ");
        int size = scanner.nextInt();
        scanner.nextLine();

        ExpenseTracker expenseTracker = new ExpenseTracker(size);

        boolean running = true;
        while (running) {
            System.out.println("\nExpense Tracker:");
            System.out.println("1. Add One-Time Expense");
            System.out.println("2. Add Recurring Expense");
            System.out.println("3. Add Gift Expense");
            System.out.println("4. List Expenses");
            System.out.println("5. Show Summary");
            System.out.println("6. List Expenses by Category");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
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
                    scanner.nextLine();
                    System.out.print("Enter category (e.g., Food, Utilities, Entertainment): ");
                    category = scanner.nextLine();
                    System.out.print("Enter frequency (e.g., Monthly, Weekly): ");
                    String frequency = scanner.nextLine();
                    RecurringExpense recurringExpense = new RecurringExpense(description, amount, category, frequency);
                    expenseTracker.addExpense(recurringExpense);
                    break;
                case 3:
                    System.out.print("Enter description: ");
                    description = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter category (e.g., Gifts, Entertainment): ");
                    category = scanner.nextLine();
                    System.out.print("Enter recipient: ");
                    String recipient = scanner.nextLine();
                    GiftExpense giftExpense = new GiftExpense(description, amount, category, recipient);
                    expenseTracker.addExpense(giftExpense);
                    break;
                case 4:
                    expenseTracker.listExpenses();
                    break;
                case 5:
                    expenseTracker.showSummary();
                    break;
                case 6:
                    System.out.print("Enter category to list expenses: ");
                    category = scanner.nextLine();
                    expenseTracker.listExpensesByCategory(category);
                    break;
                case 7:
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
