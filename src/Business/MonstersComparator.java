package Business;

import Business.Entity.MonstPlus;
import Business.Entity.Monster;

import java.util.Comparator;

public class MonstersComparator implements Comparator<MonstPlus> {

    public int compare(MonstPlus p1, MonstPlus p2) {
            return Integer.compare(p2.getMonster().getInitiative(), p1.getMonster().getInitiative());
        }

}
