package pl.grychtol;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    static Account account;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        String message;
        while (scanner.hasNextLine()) {
            showMenu();
            message = scanner.nextLine();
            if (message.isEmpty() || message.contains("0")){
                break;
            }
            readInput(Integer.parseInt(message));
        }
//
//        for (String string :
//                purchases) {
//            System.out.println(string);
//        }
//        System.out.println();
//        System.out.println("Total: $"+summary);

    }
    public static void showMenu() {
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("0) Exit");
    }


    public static void readInput(int command) {
        switch (command) {
            case 1:{
                account.addIncome(scanner.nextLine());
                break;
            }
            case 2:{
                account.addPurchaseTitle(scanner.nextLine());
                account.addPurchasePrice(scanner.nextLine());
                break;
            }
            case 3:{
                account.printHistory();
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


}
