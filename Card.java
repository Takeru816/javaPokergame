package kouka3.poker2;

public class Card {
    private int number;
    private String symbol;

    public Card(int number, String symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    public int getNumber() {
        return number;
    }

    public String getSymbol() {
        return symbol;
    }

    public void showCard() {
        System.out.println("数字 = " + getNumber() + "," + "シンボル = " + getSymbol());
    }
}