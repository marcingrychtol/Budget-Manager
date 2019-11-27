package pl.grychtol;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    static List<String> purchases = new LinkedList<>();
    private static double summary = 0.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String message;
        while (scanner.hasNextLine()) {
            message = scanner.nextLine();
            if (message.isEmpty()){
                break;
            }

            purchases.add(message);
//        System.out.println(temp.length);
//        System.out.println(temp[1]);
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

                break;
            }
            case 1:{

                break;
            }
            case 2:{

                break;
            }
            case 3:{

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
