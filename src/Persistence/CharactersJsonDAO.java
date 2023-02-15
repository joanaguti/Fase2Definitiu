package Persistence;

import Business.Entity.Character;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe CharactersJsonDAO
 * <p>Classe que ens serveix per consultar, i guardar informacio del tipus Character al fitxer JSON.
 * </p>
 *
 * @author Cristina Martí i Joana Gutiérrez
 * @version 1.0
 */

public class CharactersJsonDAO {
    private final Gson gson;

    public CharactersJsonDAO() {
        this.gson = new Gson();
    }


    /**
     * Per poder fer la lectura del fitxer JSON
     *
     * @param  -
     * @return ArrayList de character del fitxer
     */
    public ArrayList<Character> readCharacters() {
        ArrayList<Character> characters = new ArrayList<>();
        try {
            FileReader fr = new FileReader("Files/characters.json");
            JsonElement element = JsonParser.parseReader(fr);

            JsonArray array = element.getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                Character character = new Character();
                JsonElement pos = array.get(i);
                JsonObject aux = pos.getAsJsonObject();
                character.setName(aux.get("name").getAsString());
                character.setPlayer(aux.get("player").getAsString());
                character.setXp(aux.get("xp").getAsInt());
                character.setBody(aux.get("body").getAsInt());
                character.setMind(aux.get("mind").getAsInt());
                character.setSpirit(aux.get("spirit").getAsInt());
                character.setCharacterType(aux.get("class").getAsString());
                characters.add(character);
            }
        } catch (FileNotFoundException e) {
        }

        return characters;
        }

    /**
     * Verificar que el personatge existeixi
     *
     * @param name el que volem mirar si existeix o no
     * @return boolean indicant si s'ha trobat al fitxer JSON
     */
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

    /**
     * Extreu els noms dels personatges d'un jugador en concret del fitxer Json.
     *
     * @param player del que li volem extreure la informació.
     * @return Llista de noms dels characters que té un jugador.
     */
    public ArrayList<String> selectCharacters(String player){
        ArrayList <Character> characters = readCharacters();
        ArrayList<String> names = new ArrayList<>();
        if(!player.isEmpty()){
            for(int i=0; i<characters.size(); i++){
                Character character = characters.get(i);
                if(player.equalsIgnoreCase(character.getPlayer())){
                    names.add(character.getName());
                } else{
                    if (character.getPlayer().toLowerCase().contains(player)){
                        names.add(character.getName());
                    }
                }
            }
        }else{
            for(int i=0; i<characters.size(); i++){
                Character character = characters.get(i);
                names.add(character.getName());
            }
        }
        return names;
    }


    public void writeFileAllChr(ArrayList<Character> characters)throws IOException {

        FileWriter fw = new FileWriter("Files/Characters.json");
        JsonArray array = new JsonArray();

        for(int i = 0; i<characters.size(); i++){
            JsonObject jo = new JsonObject();
            jo.addProperty("name", characters.get(i).getName());
            jo.addProperty("player", characters.get(i).getPlayer());
            jo.addProperty("xp", characters.get(i).getXp());
            jo.addProperty("body", characters.get(i).getBody());
            jo.addProperty("mind", characters.get(i).getMind());
            jo.addProperty("spirit", characters.get(i).getSpirit());
            jo.addProperty("class", characters.get(i).getCharacterType());
            array.add(jo);
        }
        String jso = new Gson().toJson(array);
        fw.write(jso);
        fw.close();
    }


    public void writeFileOneChr(Character character)throws IOException {
        ArrayList<Character> characters = readCharacters();
        characters.add(character);
        // escriu nou characters
       writeFileAllChr(characters);
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
    public ArrayList<String> readCharNames(){
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Character> characters = readCharacters();
        for (int i = 0; i < characters.size(); i++) {
            names.add(i, characters.get(i).getName());
        }
        return names;
    }
    public Boolean countCharsFile(){
        ArrayList<Character> characters = readCharacters();
        // true -> puc fer op 4
        return characters.size() > 2;
    }

}
