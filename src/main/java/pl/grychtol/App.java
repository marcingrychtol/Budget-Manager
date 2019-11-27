package pl.grychtol;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    static Account account = new Account();
    static Scanner scanner = new Scanner(System.in);
    public static boolean switcher = true;

    public static void main(String[] args) {

        while (switcher) {
            showMenu();
            readCommand();
        }

    }

    public static void readCommand() {
        int command = scanner.nextInt();
        if (command == 0){
            System.out.println("Bye!");
            switcher = false;
            return;
        }
        switch (command) {
            case 1:{
                System.out.println("Enter income:");
                account.addIncome(scanner.nextLine());
                System.out.println("Income was added!");
                System.out.println();
                break;
            }
            case 2:{
                System.out.println("Enter purchase name:");
                account.addPurchaseTitle(scanner.nextLine());
                System.out.println("Enter its price:");
                account.addPurchasePrice(scanner.nextLine());
                System.out.println("Purchase was added!");
                System.out.println();
                break;
            }
            case 3:{
                account.showPurchases();
                System.out.println();
                break;
            }
            case 4:{
                account.printBalance();
                System.out.println();
                break;
            }
            default:{
                break;
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
