package Presentation.Views;

import java.text.Normalizer;
import java.util.ArrayList;
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
        char auxChr;
        word = Normalizer.normalize(word , Normalizer.Form.NFD);
        word = word.replaceAll("[^\\p{ASCII}]", "");
        System.out.println("WORD DPS DE LA VAINA RARA: " + word);
        char[] wordChr = word.toCharArray();

        for (int i = 0; i < word.length(); i ++){
            if ((wordChr[i] < 'A' || wordChr[i] > 'Z' && wordChr[i] < 'a' || wordChr[i] > 'z') && (wordChr[i] != ' ')){
                contains = true;
                //return true, millor no?
                System.out.println("ENTRA PERQUE NO ES UNA LLETRA");
            } else{
                if (wordChr[i] == ' '){
                    System.out.println("ENTRA PERQUE ES UN ESPAI O TILDE");
                    contains = false;
                }
            }
        }
        if(contains) {
            //System.out.println("\nName can't contain special characters.");
            showMessage("\nName can't contain special characters.");
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
    public int askNumberInARangeOneTime(String message, int min, int max){
        System.out.print(message);
        String evaluate = scanner.nextLine();
        int num;
        try {
            num = Integer.parseInt(evaluate);
            if (num < min || num > max) {
                System.out.println("\nERROR: Character not found");
            } else {
                return num;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nERROR: Character not found");
            num = 0;
        }
        return num;
    }
    public void showList(ArrayList<String> list){
        System.out.println("\nYou watch as some adventurers get up from their chairs and approach you.\n");
        for(int i=0; i<list.size(); i++){
            String output = list.get(i);
            System.out.println("\t"+(i+1)+". "+output);
        }
        System.out.println("\n0. Back");
    }
    public void showInformationCharacter (ArrayList<String> information){
        System.out.println("\t* Name:     "+ information.get(0));
        System.out.println("\t* Player:   "+ information.get(1));
        System.out.println("\t* Class:    Adventure");
        System.out.println("\t* Level:    X COMRPOBAR");
        System.out.println("\t* XP:       "+ information.get(2));
        int aux = Integer.parseInt(information.get(3));
        if (aux < 0) {
            System.out.println("\t* Body:     "+ information.get(3));
        } else{
            System.out.println("\t* Body:     +"+ information.get(3));
        }
        aux = Integer.parseInt(information.get(4));
        if (aux < 0) {
            System.out.println("\t* Mind:     "+ information.get(4));
        } else{
            System.out.println("\t* Mind:     +"+ information.get(4));
        }
        aux = Integer.parseInt(information.get(5));
        if (aux < 0) {
            System.out.println("\t* Spirit:   "+ information.get(5));
        } else{
            System.out.println("\t* Spirit:   +"+ information.get(5));
        }

    }
    public void showMonstersList(ArrayList<String> monstNames, ArrayList<String> monstTypes){
        for(int j=0; j<monstNames.size();j++){
            String name = monstNames.get(j);
            String type = monstTypes.get(j);
            System.out.println(j+1+". "+name+" ("+type+")");
        }
    }
}

