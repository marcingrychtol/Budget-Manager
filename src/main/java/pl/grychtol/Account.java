package pl.grychtol;

import java.util.LinkedList;
import java.util.List;

public class Account {
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
        System.out.println("Enter income:");
        this.lastIncome = Double.parseDouble(income);
        this.balance += this.lastIncome;
        System.out.println("Income was added!");
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
        System.out.println("Enter purchase name:");
        this.history.add(purchase);
    }

    public void addPurchasePrice(String price) {
        System.out.println("Enter its price:");
        this.balance -= Double.parseDouble(price);
        this.totalSpend += Double.parseDouble(price);
        this.history.get(history.size() - 1).concat(price);
        System.out.println("Income was added!");
    }
}

