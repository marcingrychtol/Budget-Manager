package pl.grychtol;

public class Menu {
    private Menu() {
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
