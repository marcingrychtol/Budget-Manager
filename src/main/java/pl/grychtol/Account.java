package pl.grychtol;

import java.util.LinkedList;
import java.util.List;

public class Account {
    private double balance = 0.0;
    private double income = 0.0;
    private List<String> history = new LinkedList<>();

    public double getBalance() {
        return balance;
    }

    public Account setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public double getIncome() {
        return income;
    }

    public Account setIncome(double income) {
        this.income = income;
        return this;
    }

    public List<String> getHistory() {
        return history;
    }

    public Account setHistory(List<String> history) {
        this.history = history;
        return this;
    }
}