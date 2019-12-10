package budget.utils;

import budget.analyzer.Analyzer;
import budget.analyzer.concrete.AnalyzeAll;
import budget.analyzer.concrete.AnalyzeByType;
import budget.analyzer.concrete.AnalyzeCertainType;
import budget.models.Purchase;
import budget.models.PurchaseCategory;

import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {

    public static final int C_EXIT = 0;
    public static final int C_ENTER_INCOME = 1;
    public static final int C_ADD_PURCHASE = 2;
    public static final int C_LIST_PURCHASES = 3;
    public static final int C_SHOW_BALANCE = 4;
    public static final int C_SAVE = 5;
    public static final int C_LOAD = 6;
    public static final int C_ANALYZE = 7;

    public static Menu menu = new Menu();
    private Menu(){}

    private Scanner scanner = new Scanner(System.in);
    private int command = 0;
    private boolean switcher = true;
    private Account account = new Account();

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    private Analyzer analyzer = new Analyzer();

    public boolean isSwitcher() {
        return switcher;
    }

    public void readCommand() {
        String input = scanner.nextLine();
        if (input.matches("\\d+")) { // it checks the input line contains only digits
            this.command = Integer.parseInt(input);
        } else {
            System.out.println("Incorrect number: " + input);
        }
        System.out.println();
    }

    public void showMenu_Main() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("7) Analyze (Sort)");
        System.out.println("0) Exit");
    }

    private void showMenu_SetPurchaseCatToSave() {
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) Back");
    }

    private void showMenu_ListPurchaseByCat() {
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) All");
        System.out.println("6) Back");
    }

    private void showMenu_ChooseAnalyzeStrategy() {
        System.out.println("How do you want to sort?");
        System.out.println("1) Sort all purchases");
        System.out.println("2) Sort by type");
        System.out.println("3) Sort certain type");
        System.out.println("4) Back");
    }

    private void showMenu_ChooseAnalyzeCertainType() {
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
    }

    public void actionMain() {

        switch (command) {
            case C_EXIT:
                switcher = false;
                return;
            case C_ENTER_INCOME: {
                System.out.println("Enter income:");
                account.addIncome(scanner.nextLine());
                System.out.println("Income was added!");
                System.out.println();
                break;
            }// ENTER INCOME

            case C_ADD_PURCHASE: {
                while (true) {
                    showMenu_SetPurchaseCatToSave();
                    readCommand();
                    if (command == 5) {
                        break;
                    }

                    PurchaseCategory tempCategory = PurchaseCategory.getCategoryByNumber(command);

                    System.out.println();
                    System.out.println("Enter purchase name:");
                    String tempTitle = scanner.nextLine();
                    System.out.println("Enter its price:");
                    Double tempPrice = Double.parseDouble(scanner.nextLine());

                    account.addPurchase(new Purchase(tempCategory, tempTitle, tempPrice));

                    System.out.println("Purchase was added!");
                    System.out.println();
                }
                break;
            }// ADD PURCHASE

            case C_LIST_PURCHASES: {
                while (true) {
                    if (account.getTransactionHistory(5).isEmpty()) {
                        System.out.println("Purchase list is empty!");
                        System.out.println();
                        return;
                    }
                    showMenu_ListPurchaseByCat();
                    readCommand();
                    if (command == 6) {
                        System.out.println();
                        break;
                    }
                    showPurchases(command);
                }
                break;
            }// LIST PURCHASES

            case C_SHOW_BALANCE: {
                System.out.println("Balance: $" + account.getBalance());
                System.out.println();
                break;
            }// SHOW BALANCE

            case C_SAVE: {
                if (account.save()) {
                    System.out.println("History saved succesfully!");
                } else {
                    System.out.println("Problem occured while saving history!");
                }
                break;
            }// SAVING

            case C_LOAD: {
                if (account.load()) {
                    System.out.println("Purchases were loaded!");
                    System.out.println();
                } else {
                    System.out.println("Problem occured while loading history!");
                }
                break;
            }// LOADING

            case C_ANALYZE: {
                while (true) {
                    showMenu_ChooseAnalyzeStrategy();
                    readCommand();
                    if (command == 4){
                        return;
                    }
                    actionAnalyze();
                }
            }// ANALYZE

            default: {
                break;
            }// DEFAULT
        }
    }

    private void actionAnalyze() {
        if (command < 1 || command > 3){
            System.out.println("Invalid command, try again!");
            return;
        }
        if (command ==1 && account.getTransactionHistory(5).isEmpty()) {
            System.out.println("Purchase list is empty!");
            System.out.println();
            return;
        }
        switch(command){
            case 1:{
                analyzer.setAnalyzeStrategy(new AnalyzeAll());
                analyzer.showAnalysis("All",account.getTransactionHistory());
                break;
            }
            case 2:{
                analyzer.setAnalyzeStrategy(new AnalyzeByType());
                analyzer.showAnalysis("Types",account.getTransactionHistory());
                break;
            }
            case 3:{
                showMenu_ChooseAnalyzeCertainType();
                readCommand();
                if (command < 1 || command > 4){
                    System.out.println("Invalid command, try again!");
                    return;
                }
                analyzer.setAnalyzeStrategy(
                        new AnalyzeCertainType(PurchaseCategory.getCategoryByNumber(command)));
                analyzer.showAnalysis(
                        PurchaseCategory.getCategoryByNumber(command).getName(),
                        account.getTransactionHistory());
                break;
            }
            default:
                break;
        }
    }

    public void showPurchases(int command) {

        if ( account.getTransactionHistory(command).isEmpty()) {
            System.out.println("Purchase list is empty");
            System.out.println();
            return;
        }
        if (command > 0 && command < 5) {
            System.out.println(PurchaseCategory.getCategoryByNumber(command).getName() + ": ");
        } else if (command == 5) {
            System.out.println("All: ");
        } else {
        }
        account.getTransactionHistory(command).stream()
                .map(x -> x.toString())
                .forEach(s -> System.out.println(s));
        System.out.println("Total sum: $" + countTotal(command));
        System.out.println();
    }

    public Double countTotal() {
        return countTotal(5);
    }

    public Double countTotal(int command) {
        return account.getTransactionHistory(command).stream()
                .map(x -> x.getPrice())
                .collect(Collectors.summingDouble(Double::doubleValue));
    }
}

