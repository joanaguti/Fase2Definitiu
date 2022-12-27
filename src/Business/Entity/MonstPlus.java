package Business.Entity;

public class MonstPlus {
    private Monster monster;
    private int num;

    public MonstPlus(Monster monster, int num) {
        this.monster = monster;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
