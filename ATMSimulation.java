import java.util.Scanner;

public class ATMSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Authentication variables
        int correctPin = 1234;
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        boolean isAuthenticated = false;

        // Account & Transaction History variables
        double balance = 0.0;
        String[] transactions = new String[100];
        int transactionCount = 0;

        // 1. PIN Authentication Loop
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your PIN: ");
            int enteredPin = scanner.nextInt();

            if (enteredPin == correctPin) {
                isAuthenticated = true;
                System.out.println("Login successful!\n");
                break;
            } else {
                attempts++;
                int remaining = MAX_ATTEMPTS - attempts;
                if (remaining > 0) {
                    System.out.println("Incorrect PIN. Attempts remaining: " + remaining);
                } else {
                    System.out.println("Maximum attempts reached. Account locked.");
                }
            }
        }

        // 2. Main Menu Loop (only runs if authenticated)
        if (isAuthenticated) {
            boolean running = true;

            while (running) {
                System.out.println("------- ATM MAIN MENU -------");
                System.out.println("Welcome to Awab Interprize");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. View Transactions");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Current Balance: $" + balance + "\n");
                        break;

                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();

                        if (depositAmount > 0) {
                            balance += depositAmount;
                            if (transactionCount < transactions.length) {
                                transactions[transactionCount] = String.format("Deposited: $%.2f", depositAmount);
                                transactionCount++;
                            }
                            System.out.printf("Successfully deposited $%.2f%n%n", depositAmount);
                        } else {
                            System.out.println("Invalid amount. Deposit must be greater than zero.\n");
                        }
                        break;

                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();

                        if (withdrawAmount <= 0) {
                            System.out.println("Invalid amount. Withdrawal must be greater than zero.\n");
                        } else if (withdrawAmount <= balance) {
                            balance -= withdrawAmount;
                            if (transactionCount < transactions.length) {
                                transactions[transactionCount] = String.format("Withdrew: $%.2f", withdrawAmount);
                                transactionCount++;
                            }
                            System.out.printf("Successfully withdrew $%.2f%n%n", withdrawAmount);
                        } else {
                            System.out.println("Error: Insufficient balance.\n");
                        }
                        break;

                    case 4:
                        System.out.println("\n--- Transaction History ---");
                        if (transactionCount == 0) {
                            System.out.println("No transactions recorded yet.");
                        } else {
                            for (int i = 0; i < transactionCount; i++) {
                                System.out.println((i + 1) + ". " + transactions[i]);
                            }
                        }
                        System.out.println();
                        break;

                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option. Please choose between 1 and 5.\n");
                }
            }
        }

        scanner.close();
    }
}

