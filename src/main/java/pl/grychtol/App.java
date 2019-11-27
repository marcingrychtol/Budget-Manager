package pl.grychtol;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    static Account account;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        showMenu();

        String message;
        while (scanner.hasNextLine()) {
            message = scanner.nextLine();
            if (message.isEmpty()){
                break;
            }

            account.addPurchase(message);
            if (!message.isEmpty()){
                summary += Double.parseDouble(message.split("\\$")[1]);
            }
        }

        for (String string :
                purchases) {
            System.out.println(string);
        }
        System.out.println();
        System.out.println("Total: $"+summary);

    }
    public static void showMenu() {
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("0) Exit");
    }

    private static int tempInput;

    public static void readInput(String input) {
        if (input.isEmpty()) {
            return;
        }
        tempInput = Integer.parseInt(input);
        switch (tempInput) {
            case 0:{
                return;
            }
            case 1:{

                account.addIncome(scanner.nextDouble());
                break;
            }
            case 2:{
                account.addPurchase(scanner.nextLine());
                break;
            }
            case 3:{
                account.printHistory();
                break;
            }
            case 4:{

                break;
            }
            default:{

                return;
            }
        }
    }


}
