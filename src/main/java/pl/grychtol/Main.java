package pl.grychtol;

/**
 * Hello world!
 */

import java.util.Scanner;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Reader reader = new Reader();

        while (reader.switcherNotZero()) {
            reader.showMenu();
            reader.readCommand();
            reader.action();
        }

    }
}

class Account {
    private double balance = 0.0;
    private double totalSpend = 0.0;
    private double lastIncome = 0.0;
    private List<String> history = new LinkedList<>();

    public void printBalance() {
        System.out.println("Balance: $" + balance);
    }

    public double getLastIncome() {
        return lastIncome;
    }

    public void addIncome(String income) {
        this.lastIncome = Double.parseDouble(income);
        this.balance += this.lastIncome;
    }

    public void showPurchases() {
        if (this.history.isEmpty()) {
            System.out.println("Purchase list is empty");
            return;
        }
        history.stream()
                .forEach(System.out::println);

        System.out.println("Total sum: $" + totalSpend);
    }

    public void addPurchaseTitle(String purchase) {
        this.history.add(purchase);
    }

    public void addPurchasePrice(String price) {
        this.balance -= Double.parseDouble(price);
        this.totalSpend += Double.parseDouble(price);
        String temp = this.history.get(history.size() - 1);
        this.history.set(history.size() - 1, temp.concat(": $"+price));
    }
}

class Reader {
    private Scanner scanner = new Scanner(System.in);
    private int command = 0;

    public boolean switcherNotZero() {
        return switcher;
    }

    private boolean switcher = true;
    private Account account = new Account();

    public void readCommand() {
        this.command = scanner.nextInt();
        scanner.nextLine(); //to get rid of newline character after reading int

    }

    public void action() {
        System.out.println();
        switch (command) {
            case 0: {
                System.out.println("Bye!");
                switcher = false;
                break;
            }
            case 1: {
                System.out.println("Enter income:");
                account.addIncome(scanner.nextLine());
                System.out.println("Income was added!");
                System.out.println();
                break;
            }
            case 2: {
                System.out.println("Enter purchase name:");
                account.addPurchaseTitle(scanner.nextLine());
                System.out.println("Enter its price:");
                account.addPurchasePrice(scanner.nextLine());
                System.out.println("Purchase was added!");
                System.out.println();
                break;
            }
            case 3: {
                account.showPurchases();
                System.out.println();
                break;
            }
            case 4: {
                account.printBalance();
                System.out.println();
                break;
            }
            default: {
                break;
            }
        }
    }

    public void showMenu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("0) Exit");
    }


}


