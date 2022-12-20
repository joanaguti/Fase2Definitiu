package Persistence;

import Business.Entity.Character;
import Business.Entity.Monster;
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

    public ArrayList<Character> carregarEdicions() {
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
        ArrayList<Character> characters = carregarEdicions();
        for (int i = 0; i < characters.size(); i++) {
            String output = characters.get(i).getName();
            if(name.equals(output)){
                return true;
            }
        }
        return false;
    }
    public ArrayList<String> selectCharacters(String player){                  //Retorna tots els noms de personatge
        ArrayList <Character> characters = carregarEdicions();
        ArrayList<String> names = new ArrayList<>();
        if(!player.isEmpty()){
            for(int i=0; i<characters.size(); i++){
                Character character = characters.get(i);
                if(player.equals(character.getPlayer())){
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

    public void writeFile(Character character)throws IOException {
        ArrayList<Character> characters = carregarEdicions();
        characters.add(character);
        // escriu nou characters
        FileWriter fw = new FileWriter("Files/Characters.json");
        gson.toJson(characters, fw);
        fw.close();
    }

}
