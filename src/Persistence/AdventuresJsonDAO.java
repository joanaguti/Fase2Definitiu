package Persistence;

import Business.Entity.*;
import Business.Entity.Character;
import com.google.gson.Gson;
import com.google.gson.*;

import javax.sql.rowset.serial.SQLOutputImpl;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe AdventuresJsonDAO
 * <p>Classe que ens serveix per consultar, i guardar informacio del tipus Adventure al fitxer JSON.
 * </p>
 *
 * @author Cristina Martí i Joana Gutiérrez
 * @version 1.0
 */

/**
 * Constructor basic per poder inicialitzar la classe
 */
public class AdventuresJsonDAO {

    private final Gson gson;

    public AdventuresJsonDAO() {
        this.gson = new Gson();
    }

    /**
     * Llegeix totes les aventures del fitxer JSON
     *
     * @param -
     * @return llista de totes les aventures que tenim al fitxer.
     */
    public ArrayList<Adventure> readAdventures() {
        ArrayList<Adventure> adventures = new ArrayList<>();
        try {
            FileReader fr = new FileReader("Files/adventures.json");
            Adventure[] listall = gson.fromJson(gson.newJsonReader(fr), Adventure[].class );
            adventures = new ArrayList<>(List.of(listall));

        } catch (FileNotFoundException e) {
            System.out.println("Error opening");
        }
        return adventures;
    }
    /**
     * Extreu els noms dels monstres d'una aventura i d'un combat en concret del fitxer Json.
     *
     * @param name de l'aventura de la que li volem extreure informació.
     * @return noms dels monstres d'una aventura i d'un combat en concret
     */
    public ArrayList<String> getAllNameMonstersOfOneFight(String name, int numFight){
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Adventure> adventures = readAdventures();
        for (int i = 0; i < adventures.size(); i++) {
            if(name.equals(adventures.get(i).getAdventureName())){
                ArrayList<Fight> fights = adventures.get(i).getFightList();
                ArrayList<MonstPlus> monstersPlus = fights.get(numFight).getMonsters();
                for(int k=0; k<monstersPlus.size(); k++){
                    Monster monsters = monstersPlus.get(k).getMonster();
                    names.add(monsters.getNameMonster());
                }
            }
        }
        return names;
    }
    public void PROVAJOANA(Adventure adventure) throws IOException {
        ArrayList<Adventure> adventures = readAdventures();
        adventures.add(adventure);
        // escriu
        FileWriter fw = new FileWriter("Files/adventures.json");
        gson.toJson(adventures, fw);
        fw.close();
    }

    public void writeFileOneAdv(Adventure adventure)throws IOException {
        ArrayList<Adventure> adventures = readAdventures();
        adventures.add(adventure);
        // escriu nou characters
        FileWriter fw = new FileWriter("Files/adventures.json");
        gson.toJson(adventures, fw);
        fw.close();
    }
    /**
     * Troba una aventura del fitxer Json.
     *
     * @param nom de l'aventura que volem comprobar.
     * @return Boolean indicant si s'ha trobat l'aventura
     */
    public Boolean findAdventure(String name){
        ArrayList<Adventure> adventures = readAdventures();
        for (int i = 0; i < adventures.size(); i++) {
            String output = adventures.get(i).getAdventureName();
            if(name.equals(output)){
                return true;
            }
        }
        return false;
    }

}
