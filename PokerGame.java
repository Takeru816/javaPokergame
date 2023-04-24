package poker2;

import java.util.Scanner;

public class PokerGame {

    // ポーカーゲームの進行をするメソッド
    public void game() {

        Scanner stdIn = new Scanner(System.in);
        System.out.println("1~4人のnpcと対戦できます。");
        System.out.print("何人と対戦しますか：");   int npcNum = stdIn.nextInt();

        Deck deck = new Deck();
        InterfacePokerPlayer[] pokerPlayersList = new InterfacePokerPlayer[npcNum + 1];
        
        pokerPlayersList[0] = new HumanPlayerImpr(deck);
        for (int i = 1; i < npcNum + 1; i++) {
            pokerPlayersList[i] = new NonPlayerCharacterImpr(deck);
        }

        pokerPlayersList[0].showHand();       
        
        for (int i = 0; i < pokerPlayersList.length; i++) {
            pokerPlayersList[i].changeHand(deck);
        }

        System.out.println();
        System.out.println("-------result-------");
        for (int i = 0; i < pokerPlayersList.length; i++) {
            pokerPlayersList[i].showHand();
        } 
    }
}
