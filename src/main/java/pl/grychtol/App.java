package pl.grychtol;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    static Account account = new Account();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        String message;
        while (true) {
            showMenu();
            message = scanner.nextLine();
            if (message.isEmpty() || message.contains("0")){
                System.out.println("Bye!");
                break;
            }
            readInput(Integer.parseInt(message));
        }

    }

    public static void readInput(int command) {
        switch (command) {
            case 1:{
                System.out.println("Enter income:");
                account.addIncome(scanner.nextLine());
                System.out.println("Income was added!");
                break;
            }
            case 2:{
                System.out.println("Enter purchase name:");
                account.addPurchaseTitle(scanner.nextLine());
                System.out.println("Enter its price:");
                account.addPurchasePrice(scanner.nextLine());
                System.out.println("Purchase was added!");
                break;
            }
            case 3:{
                account.showPurchases();
                break;
            }
            case 4:{
                account.printBalance();
                break;
            }
            default:{
                return;
            }
        }
    }
    public static void showMenu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("0) Exit");
    }


}
