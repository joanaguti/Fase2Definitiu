package Business;

public class Dice {
    private int num;

    public Dice() {
    }

    public int rollDice(int max, int min) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}
