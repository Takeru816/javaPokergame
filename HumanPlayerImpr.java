package poker2;

import java.util.Scanner;

public class HumanPlayerImpr extends AbstractPokerPlayer{

    public HumanPlayerImpr(Deck deck) {
        super(deck);
        setName();
    }

    @Override
    protected void setName() {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("プレイヤー名を入力してください："); this.name = stdIn.next();
    }

    @Override
    public void changeHand(Deck deck) {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("手札を交換しますか yes == f | no == それ以外："); String koukanFlg = stdIn.next();
        if (koukanFlg.equals("f")) {
            for (int i = 0; i < 5; i++) {
                System.out.print(i + 1 + "枚目の手札を交換しますか？ yes = f | no = それ以外：");    String changeFlg = stdIn.next();
                if (changeFlg.equals("f")) {
                    this.handList[i] = deck.getCard();
                }
            }
            judgeHand();
        }

    }
}
