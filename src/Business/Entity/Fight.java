package Business.Entity;

import java.util.ArrayList;

public class Fight {
    private ArrayList<MonstPlus> monsters;

    public Fight(ArrayList<MonstPlus> monsters) {
        this.monsters = monsters;
    }

    public ArrayList<MonstPlus> getMonsters() {
        return monsters;
    }
}
