package poker2;

public class Card implements Comparable<Card>{
    private int number;
    private String symbol;

    public Card(int number, String symbol) {
        this.number = number;
        this.symbol = symbol;
    }

    int getNumber() {
        return number;
    }

    String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "数字 = " + this.number + ", " + "マーク = " + this.symbol;
    }

    @Override
    public int compareTo(Card o) {
        // まずは数字を比較する
        int numberComparison = Integer.compare(number, o.number);
        if (numberComparison != 0) {
            return numberComparison;
        }
        // 数字が同じ場合はスートを比較する
        return symbol.compareTo(o.symbol);
    }
}