package Persistence;

import Business.Entity.Character;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharactersJsonDAO {

    private final Gson gson;

    public CharactersJsonDAO() {
        this.gson = new Gson();
    }

    public ArrayList<Character> readCharacters() {
        ArrayList<Character> characters = new ArrayList<>();
        try {
            FileReader fr = new FileReader("Files/characters.json");
            Character[] listall = gson.fromJson(gson.newJsonReader(fr), Character[].class );
            characters = new ArrayList<Character>(List.of(listall));
            //Fer switch segons tipus de character

        } catch (FileNotFoundException e) {
            //!!!!!!!!!
            System.out.println("Error opening");
        }
        return characters;
        }

    public Boolean findCharacter(String name){
        ArrayList<Character> characters = readCharacters();
        for (int i = 0; i < characters.size(); i++) {
            String output = characters.get(i).getName();
            if(name.equals(output)){
                return true;
            }
        }
        return false;
    }
    public ArrayList<String> selectCharacters(String player){                  //Retorna tots els noms de personatge
        ArrayList <Character> characters = readCharacters();
        ArrayList<String> names = new ArrayList<>();
        if(!player.isEmpty()){
            for(int i=0; i<characters.size(); i++){
                Character character = characters.get(i);
                if(player.equals(character.getPlayer())){   //Un trozo tambien sirve
                    names.add(character.getName());
                }
            }
        }else{                                                         // Si entran espai retorno tots els noms
            for(int i=0; i<characters.size(); i++){
                Character character = characters.get(i);
                names.add(character.getName());
            }
        }
        return names;
    }

    public void writeFileOneChr(Character character)throws IOException {
        ArrayList<Character> characters = readCharacters();
        characters.add(character);
        // escriu nou characters
        FileWriter fw = new FileWriter("Files/Characters.json");
        gson.toJson(characters, fw);
        fw.close();
    }

    public void writeFileAllChr(ArrayList<Character> characters)throws IOException {
        // escriu nou characters
        FileWriter fw = new FileWriter("Files/Characters.json");
        gson.toJson(characters, fw);
        fw.close();
    }

    public Character findInfCharacter(String name){
        ArrayList<Character> characters = readCharacters();
        for (int i = 0; i < characters.size(); i++) {
            if(name.equals(characters.get(i).getName())){
                return characters.get(i);
            }
        }

        return null;
    }

    public void removeCharacterFile (String name) throws IOException {
        ArrayList<Character> characters = readCharacters();
        for (int i = 0; i < characters.size(); i++) {
            if(name.equals(characters.get(i).getName())){
                characters.remove(i);
            }
        }
        //tornar a escriure tot al fitxer ja que es consultiu
        writeFileAllChr(characters);

    }

}
