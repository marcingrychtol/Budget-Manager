package pl.grychtol;

import java.util.LinkedList;
import java.util.List;

public class Account {
    private double balance = 0.0;
    private double lastIncome = 0.0;
    private List<String> history = new LinkedList<>();

    public double getBalance() {
        return balance;
    }

    public double getLastIncome() {
        return lastIncome;
    }

    public void addIncome(double income) {
        System.out.println("Enter income:");

        this.lastIncome = income;
        this.balance += income;
        System.out.println("Income was added!");

    }

    public void printHistory() {
        System.out.println(history.toString());
    }

    public void addPurchase(String purchase) {
        System.out.println("Enter purchase name:");

        this.history.add(purchase);
        System.out.println("Income was added!");
    }
}

