package Persistence;

import Business.Entity.Monster;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MonstersJsonDAO {
    private final Gson gson;

    public MonstersJsonDAO() {   // CONTROLAR FILENOTFOUND throws FileNotFoundException
        this.gson = new Gson();
    }

    public ArrayList<Monster> readFile(){
        ArrayList <Monster> monsters = new ArrayList<Monster>();
        try{
            FileReader fr = new FileReader("Files/monsters.json");
            Monster[] listall = gson.fromJson(gson.newJsonReader(fr), Monster[].class );
            monsters = new ArrayList<Monster>(List.of(listall));
        }catch(FileNotFoundException e){
            // No s'ha pogut obrir el fitxer.
            System.out.println("ERROR FILE OPENING");
        }

        return monsters;
    }
    public ArrayList<String> getAllNames(){
        ArrayList<Monster> monsters = readFile();
        ArrayList<String> names = new ArrayList<>();
        // Per retornar nomes names.
        for(int i = 0; i<monsters.size(); i++){
            Monster monster = monsters.get(i);
            names.add(monster.getNameMonster());
        }
        return names;
    }
    public ArrayList<String> getAllTypes(){
        ArrayList<Monster> monsters = readFile();
        ArrayList<String> types = new ArrayList<>();
        // Per retornar nomes names.
        for(int i = 0; i<monsters.size(); i++){
            Monster monster = monsters.get(i);
            types.add(monster.getChallenge());
        }
        return types;
    }
    public Monster readOneMonster(int index){
        ArrayList<Monster> monsters = readFile();
        return monsters.get(index);
    }

}
