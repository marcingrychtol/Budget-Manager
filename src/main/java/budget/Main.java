package budget;

/**
 * Hello world!
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader();
        while (reader.switcherNotZero()) {
            reader.showMainMenu();
            reader.readCommand();
            reader.action();
        }
    }
}


class Account {
    private double balance = 0.0;
    private double totalSpend = 0.0;
    private List<Purchase> history = new LinkedList<>();

    public void printBalance() {
        System.out.println("Balance: $" + balance);
    }

    public void addIncome(String income) {
        this.balance += Double.parseDouble(income);
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

    public void showPurchases(int command) {
        if (this.history.isEmpty()) {
            System.out.println("Purchase list is empty");
            return;
        }
        history.stream()
                .forEach(System.out::println);
        System.out.println("Total sum: $" + totalSpend);
    }

    public void addPurchase(Purchase purchase) {
        this.history.add(purchase);
    }
//
//    public void addPurchasePrice(String price) {
//        this.balance -= Double.parseDouble(price);
//        this.totalSpend += Double.parseDouble(price);
//        String temp = this.history.get(history.size() - 1);
//        this.history.set(history.size() - 1, temp.concat(": $" + price));
//    }
}

class Reader {
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

    private PurchaseGroup(int value, String name) {
        this.value = value;
        this.name = name;
    }

}
