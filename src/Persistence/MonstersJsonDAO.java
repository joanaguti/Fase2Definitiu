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
            for(int i = 0; i<monsters.size(); i++){
                Monster monster = monsters.get(i);
                System.out.println("Nom Monstra= "+ monster.getNameMonster());
            }
        }catch(FileNotFoundException e){
            // No s'ha pogut obrir el fitxer.
            System.out.println("ERROR FILE OPENING");
        }

        return monsters;
    }

}
