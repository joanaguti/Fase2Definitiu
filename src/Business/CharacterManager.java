package Business;

import Business.Entity.Character;
import Business.Entity.Monster;
import Persistence.CharactersJsonDAO;
import Persistence.MonstersJsonDAO;

import java.util.ArrayList;

public class CharacterManager {
    private CharactersJsonDAO charJsonDAO;
    private Character character;

    public CharacterManager(CharactersJsonDAO charJsonDAO) {
        this.charJsonDAO = charJsonDAO;
        this.character = null;
    }

    public String changeName(String name){

        String newName;
        char[] nameChr = name.toCharArray();
        System.out.println("Primera lletra sense + 0  = " + nameChr[0]);
        if (nameChr[0] >= 'a' &&nameChr[0] <= 'z'){
            nameChr[0] = (char)(nameChr[0] - ' ');
        }

        System.out.println("Primera lletra amb + 0  = " + nameChr[0]);
        for (int i = 0; i < name.length(); i ++){
            if (nameChr[i] == ' ' && (i +1 != name.length())){
                System.out.println("ENTRA 1 ");
                if (nameChr[i + 1] >= 'a' &&nameChr[i + 1] <= 'z'){
                    System.out.println("ENTRA 2 ");
                    nameChr[i + 1] = (char)(nameChr[i + 1] - ' ');
                }
            }
        }
        System.out.println("Nom original = " + name);
        newName = String.valueOf(nameChr);
        System.out.println("Nom retocat = " + newName);

        return newName;
    }

    public int generateStatistics(int num){
        int newNumber;
        // 1 i 2???
        if(num == 2){
            newNumber = -1;
        }else if(num>=3 && num<=5){
            newNumber = 0;
        }else if(num>=6 && num<=9){
            newNumber = 1;
        }else if(num>=10 && num<=11){
            newNumber = 2;
        }else{
            newNumber = 3;
        }
        return newNumber;
    }
    public Boolean characterExists(String name){
        return charJsonDAO.findCharacter(name);
    }

    public ArrayList<String> filterCharacters(String player){
        return charJsonDAO.selectCharacters(player);
    }
}
