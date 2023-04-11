package kouka3.poker2;

public class Hand {
    private Card[] handList = new Card[5];
    public Hand(Tranpcard deck) {
        for (int i = 0; i < 5; i++) {
            handList[i] = deck.getCard();
        }
    }
}
