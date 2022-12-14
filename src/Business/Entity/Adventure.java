package Business.Entity;

import java.util.ArrayList;

public class Adventure {
    private String adventureName;
    private ArrayList<Fight> fightList;

    public Adventure(String adventureName, ArrayList<Fight> fightList) {
        this.adventureName = adventureName;
        this.fightList = fightList;
    }

    public String getAdventureName() {
        return adventureName;
    }

    public ArrayList<Fight> getFightList() {
        return fightList;
    }

    public void setAdventureName(String adventureName) {
        this.adventureName = adventureName;
    }

    public void setFightList(ArrayList<Fight> fightList) {
        this.fightList = fightList;
    }
}
