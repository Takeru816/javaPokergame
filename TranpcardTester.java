package kouka3.poker2;

public class TranpcardTester {
    public static void main(String[] args) {
        Tranpcard test = new Tranpcard();
        Card i = test.getCard();
        i.showCard();
        System.out.println(i.getNumber());
    }

    
}
