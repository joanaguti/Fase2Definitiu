package Business;

public class DiceRoller {
    private int num;

    public DiceRoller() {
    }

    public int rollDice(int max, int min) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}