package budget;

/**
 * Hello world!
 */

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Teller teller = new Teller();
        while (teller.switcherNotZero()) {
            teller.showMainMenu();
            teller.readCommand();
            teller.action();
        }
    }
}


class Account {
    private double balance = 0.0;
    private double groupTotalSpend = 0.0;
    private List<Purchase> transactionHistory = new LinkedList<>();
    private List<Purchase> temporaryHistory = new LinkedList<>();

    public void printBalance() {
        System.out.println("Balance: $" + balance);
    }

    public void addIncome(String income) {
        this.balance += Double.parseDouble(income);
    }

    public void showPurchases(int command) {
        if (this.transactionHistory.isEmpty()) {
            System.out.println("Purchase list is empty");
            return;
        }
        temporaryHistory = transactionHistory.stream()
                .filter(p -> p.getCategory().getValue() == command)
                .collect(Collectors.toList());

        System.out.println(PurchaseGroup.valueOf(command-1).getName()+": ");
        if (temporaryHistory.isEmpty()){
            System.out.println("Purchase list is empty");
            return;
        }
        
        groupTotalSpend = temporaryHistory.stream()
                .map(x -> x.getPrice())
                .collect(Collectors.summingDouble(Double::doubleValue));
        
        System.out.println("Total sum: $" + groupTotalSpend);
    }

    public void addPurchase(Purchase purchase) {
        this.transactionHistory.add(purchase);
    }
//
//    public void addPurchasePrice(String price) {
//        this.balance -= Double.parseDouble(price);
//        this.totalSpend += Double.parseDouble(price);
//        String temp = this.history.get(history.size() - 1);
//        this.history.set(history.size() - 1, temp.concat(": $" + price));
//    }
}

class Teller {
    private Scanner scanner = new Scanner(System.in);
    private int command = 0;
    private boolean switcher = true;
    private Account account = new Account();

    public boolean switcherNotZero() {
        return switcher;
    }

    public void readCommand() {
        String input = scanner.nextLine();
        if (input.matches("\\d+")) { // it checks the input line contains only digits
            this.command = Integer.parseInt(input);
        } else {
            System.out.println("Incorrect number: " + input);
        }
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
                Purchase purchase = new Purchase();
                System.out.println("Enter purchase name:");
                purchase.setTitle(scanner.nextLine());
                System.out.println("Enter its price:");
                purchase.setPrice(scanner.nextLine());
                System.out.println("Purchase was added!");
                System.out.println();
                getRegisteredPurchaseType();
                if (command == 5) {
                    return;
                }
                purchase.setCategory(command);
                account.addPurchase(purchase);
                break;
            }
            case 3: {
                getListedPurchaseType();
                account.showPurchases(command);
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

    private int getRegisteredPurchaseType() {
        showRegisteredPurchaseTypeMenu();
        readCommand();
        return command;
    }

    private void getListedPurchaseType() {
        showRegisteredPurchaseTypeMenu();
        readCommand();
        account.showPurchases(command);
    }

    public void showMainMenu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("0) Exit");
    }

    private void showRegisteredPurchaseTypeMenu() {
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) Back");
    }

    private void showListPurchaseTypeMenu() {
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) All");
        System.out.println("6) Back");
    }
}

class Purchase {
    private PurchaseGroup category;
    private String title;
    private Double price;

    public PurchaseGroup getCategory() {
        return category;
    }

    public void setCategory(int command) {
        this.category = PurchaseGroup.valueOf(command);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    @Override
    public String toString() {
        return title + " $" + price;
    }
}

enum PurchaseGroup {
    FOOD(1, "Food"),
    CLOTHES(2, "Clothes"),
    ENTERTAINMENT(3, "Entertainment"),
    OTHER(4, "Other");

    private String name;
    private int value;

    private static Map map = new HashMap();

    static {
        for (PurchaseGroup category : PurchaseGroup.values()) {
            map.put(category.value, category);
        }
    }

    public static PurchaseGroup valueOf(int category) {
        return (PurchaseGroup) map.get(category);
    }

    public int getValue(){
        return this.value;
    }
    public String getName(){
        return this.name;
    }

    private PurchaseGroup(int value, String name) {
        this.value = value;
        this.name = name;
    }

}
