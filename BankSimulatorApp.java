package BankApp;
import java.util.ArrayList;
import java.util.Scanner;

// Custom Exception
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}

// Bank ki Account Class
class BankAccount {
    private String name;
    private double balance;
    private ArrayList<String> history = new ArrayList<>();

    BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
        history.add("Account opened with balance: " + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            history.add("Deposited: " + amount);
            System.out.println("Amount Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            System.out.println("Withdrawal must be greater than 0");
            return;
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Not enough balance! Current: " + balance);
        }
        balance -= amount;
        history.add("Withdrawn: " + amount);
        System.out.println("Amount Withdrawn: " + amount);
    }

    public void showBalance() {
        System.out.println("Current Balance: " + balance);
    }

    public void showHistory() {
        System.out.println("---- Transaction History ----");
        for (String h : history) {
            System.out.println(h);
        }
    }
}

// Main Class jo is code ko run kregi..
public class BankSimulatorApp{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        BankAccount acc = new BankAccount(name, balance);

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Balance");
            System.out.println("4. History");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int ch = sc.nextInt();
            try {
                switch (ch) {
                    case 1:
                        System.out.print("Enter amount: ");
                        acc.deposit(sc.nextDouble());
                        break;
                    case 2:
                        System.out.print("Enter amount: ");
                        acc.withdraw(sc.nextDouble());
                        break;
                    case 3:
                        acc.showBalance();
                        break;
                    case 4:
                        acc.showHistory();
                        break;
                    case 5:
                        System.out.println("Thank you see you soon !!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (InsufficientBalanceException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}