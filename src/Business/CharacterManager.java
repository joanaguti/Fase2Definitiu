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
/*
    public String changeName(String name){
        String newName;
        char newLetter;
        boolean first = false;
        for(int i=0; i<name.length(); i++){
            if(!first){
                newLetter = Character.toUpperCase(name.charAt(i));
                first = true;
            }else{
                newLetter = Character.toLowerCase(name.charAt(i));
            }
            // newName = newName + newLetter;       //Como concateno un char i una cadena.
        }

        return newName;
    }*/
    public int adjudicateStatistics(int num){
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
    public Boolean characterExixts(String name){
        return charJsonDAO.findCharacter(name);
    }

    /*
    ArrayList<String> filterCharacters(String player){                  //Retorna tots els noms de personatge
        ArrayList <Character> characters = charJsonDAO.readFile();
        ArrayList<String> names = new ArrayList<>();
        if(!player.equals(" ")){
            for(int i=0; i<characters.size(); i++){
                Character character = characters.get(i);
                if(player.equals(character.getNamePlayer())){
                    names.add(character.getCharacterName());
                }
            }
        }else{                                                         // Si entran espai retorno tots els noms
            for(int i=0; i<characters.size(); i++){
                Character character = characters.get(i);
                //names.add(character.getCharacterName());
            }
        }
        return names;
    }*/
}
