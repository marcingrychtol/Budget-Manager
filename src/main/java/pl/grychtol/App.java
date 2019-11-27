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


}
