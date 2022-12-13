package Presentation.Views;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuView {
    private Scanner scanner;
    public MenuView() {
        this.scanner = new Scanner(System.in);
    }
    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showMainMessage() {
        System.out.println("   ____ _               __       __    ____ ___   ___   _____");
        System.out.println("  / __/(_)__ _   ___   / /___   / /   / __// _ \\ / _ \\ / ___/");
        System.out.println(" _\\ \\ / //  ' \\ / _ \\ / // -_) / /__ _\\ \\ / , _// ___// (_ /");
        System.out.println("/___//_//_/_/_// .__//_/ \\__/ /____//___//_/|_|/_/    \\___/");
        System.out.println("/_/");
        System.out.println("\nWelcome to Simple LSRPG\nLoading data...");
    }
    public void showMenu(){
        System.out.println("\nThe tavern keeper looks at you and says:\n"+"“Welcome adventurer! How can I help you?”\n");
        System.out.println("1) Character creation\n" +
                "2) List Characters\n" +
                "3) Create an adventure\n" +
                "4) Start an adventure\n" +
                "5) Exit");

    }
    public int askForInteger(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("This isn't an integer!");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public String askForString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
    public boolean checkSpecialChar(String word) {
        boolean contains = false;
        int numAscii = 0;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            numAscii = (int) c;
            if(numAscii < 'A' || numAscii > 'Z' && numAscii < 'a' || numAscii > 'z'){
                contains = true;     //Te un caracter especial
            }
        }
        if(contains) {
            System.out.println("\nName can't contain special characters.");
        }
            return contains;
    }
    public int askNumberInARange(String message, int min, int max){
        do {
            System.out.print(message);
            String evaluate = scanner.nextLine();
            try {
                int num = Integer.parseInt(evaluate);
                if (num < min || num > max) {
                    System.out.println("\nWrong number try again.");
                } else {
                    return num;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nWrong number try again.");
            }
        } while (true);
    }
}

