package kouka3.poker2;

import java.util.Scanner;

public class Player {
    protected String name;
    protected Card[] handList = new Card[5];

    public Player(Tranpcard deck) {
        Scanner stdIn = new Scanner(System.in);
        this.name = stdIn.next();
        stdIn.close();

        for (int i = 0; i < 5; i++) {
            handList[i] = deck.getCard();
        }
    }


}
