package budget;

//import budget.utils.Menu;

import static budget.utils.Menu.menu;

public class Main {
    public static void main(String[] args) {

//        Menu menu = new Menu();

        while (menu.isSwitcher()) {
            menu.showMenu_Main();
            menu.readCommand();
            menu.actionMain();
        }
        System.out.print("Bye!");

    }
}


