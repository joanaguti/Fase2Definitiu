package Business;

import Business.Entity.Character;
import Persistence.CharactersJsonDAO;

import java.io.IOException;
import java.util.ArrayList;

public class CharacterManager {
    private CharactersJsonDAO charJsonDAO;
    private Character character;
    private DiceRoller dice;

    public CharacterManager(CharactersJsonDAO charJsonDAO) {
        this.charJsonDAO = charJsonDAO;
        this.character = null;
    }

    public String changeName(String name){
        //prova = CrisTINa maRTI puEYO
        String newName;
        char[] nameChr = name.toCharArray();
        if (nameChr[0] >= 'a' &&nameChr[0] <= 'z'){
            nameChr[0] = (char)(nameChr[0] - ' ');
        }
        for (int i = 0; i < name.length(); i ++){
            if (nameChr[i] == ' ' && (i + 1 != name.length())){
                if (nameChr[i + 1] >= 'a' &&nameChr[i + 1] <= 'z'){
                    nameChr[i + 1] = (char)(nameChr[i + 1] - ' ');
                }
            }
            //Comporvem si hi han maj ja que entemitg no poden haverhi
            if (i != 0 && nameChr[i] >= 'A' && nameChr[i] <= 'Z' &&  nameChr[i - 1] != ' '){
                nameChr[i] = (char)(nameChr[i] + ' ');
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

    public void addCharacter (String name, String player, int xp, int body, int mind, int spirit, String type) throws IOException {
        Character character = new Character(name, player, xp, body, mind, spirit, type);
        charJsonDAO.writeFileOneChr(character);
    }

    public ArrayList<String> getInformation(String name){
        Character character = charJsonDAO.findInfCharacter(name);
        ArrayList<String> information = new ArrayList<>();
        information.add(character.getName());
        information.add(character.getPlayer());
        information.add(String.valueOf(character.getXp()));
        information.add(String.valueOf(character.getBody()));
        information.add(String.valueOf(character.getMind()));
        information.add(String.valueOf(character.getSpirit()));

        return information;
    }

    public void removeCharacter(String name) throws IOException {
        charJsonDAO.removeCharacterFile(name);
    }

    public int getRandom(){
        return dice.rollDice(6, 1);
    }
    public int getStatistic(int a,int b){
        return a+b;
    }

    public ArrayList<String> getCharNames(){
        return charJsonDAO.readCharNames();
    }

    public Boolean activateOp(){
        return charJsonDAO.countCharsFile();
    }
}
