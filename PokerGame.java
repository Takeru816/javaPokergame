package kouka3.poker2;

import java.util.Scanner;

public class PokerGame {

    public void game() {

        Scanner stdIn = new Scanner(System.in);
        System.out.println("1~4人のnpcと対戦できます。");
        System.out.print("何人と対戦しますか：");   int npcNum = stdIn.nextInt();

        
        Tranpcard deck = new Tranpcard();
        Player player = new Player(deck);

        
    }
}
