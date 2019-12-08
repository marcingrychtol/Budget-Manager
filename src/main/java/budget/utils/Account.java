package budget.utils;


import budget.models.Purchase;
import budget.models.PurchaseCategory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Account {

    private double balance = 0.0;
    private List<Purchase> transactionHistory = new LinkedList<>();
    private File file = new File("purchases.txt");

    public boolean save() {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(String.valueOf(balance) + "\n");
            transactionHistory.stream()
                    .map(p -> p.toSaveString())
                    .forEach(p -> {
                        try {
                            fileWriter.write(p + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean load() {
        try (Scanner fileScanner = new Scanner(file)) {
            balance = Double.parseDouble(fileScanner.nextLine());
            while (fileScanner.hasNext()) {
                String[] tempString = fileScanner.nextLine().split(";");
                transactionHistory.add(new Purchase(
                                PurchaseCategory
                                        .getCategoryByNumber(
                                                Integer.parseInt(tempString[0])),
                                tempString[1],
                                Double.parseDouble(tempString[2])
                        )
                );
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public List<Purchase> getTransactionHistory() {
        return transactionHistory;
    }

    public List<Purchase> getTransactionHistory(int command) {
        if (command == 5) {
            return transactionHistory;
        }

        return transactionHistory.stream()
                .filter(p -> p.getCategory().getValue() == command)
                .collect(Collectors.toList());

    }

    public void addIncome(String income) {
        this.balance += Double.parseDouble(income);
    }

    public void addPurchase(Purchase purchase) {
        this.transactionHistory.add(purchase);
        this.balance -= purchase.getPrice();
    }

}
