package poker2;

public class NonPlayerCharacterImpr extends AbstractPokerPlayer {

    private static int npcNum = 0; 

    public NonPlayerCharacterImpr(Deck deck) {
        super(deck);
        npcNum ++;
        setName();
    }

    @Override
    protected void setName() {
        this.name = "npc_" + npcNum;
    }

    // 3カード 2ペア 1ペア ノーハンドの時のみ手札を変える
    @Override
    public void changeHand(Deck deck) {
        switch (this.hand) {
            case "3カード": {
                int tmp = this.handList[0].getNumber();
                int threeNum = 0;
                for (int i = 1; i < 5; i++) {
                    if (this.handList[i].getNumber() == tmp) {
                        threeNum = tmp;
                        break;
                    }
                    tmp = this.handList[i].getNumber();
                }
                for (int i = 0; i < 5; i++) {
                    if (threeNum != this.handList[i].getNumber()) {
                        this.handList[i] = deck.getCard();
                    }
                }
                break;
            }

            case "2ペア": {
                int tmp = this.handList[0].getNumber();
                int[] twopairNums = new int[2];
                int nowIndex = 0;
                for (int i = 1; i < 5; i++) {
                    if (this.handList[i].getNumber() == tmp) {
                        twopairNums[nowIndex] = this.handList[i].getNumber();
                        nowIndex++;
                    }
                    tmp = this.handList[i].getNumber();
                }
                for (int i = 0; i < 5; i++) {
                    if (this.handList[i].getNumber() != twopairNums[0] && this.handList[i].getNumber() != twopairNums[1]) {
                        this.handList[i] = deck.getCard();
                        break;
                    }
                }
                break;
            }

            case "1ペア": {
                int tmp = this.handList[0].getNumber();
                int pairNum = 0;
                for (int i = 1; i < 5; i++) {
                    if (this.handList[i].getNumber() == tmp) {
                        pairNum = this.handList[i].getNumber();
                        break;
                    }
                    tmp = this.handList[i].getNumber();
                }
                for (int i = 0; i < 5; i++) {
                    if (pairNum != this.handList[i].getNumber()) {
                        this.handList[i] = deck.getCard();
                    }
                }
                break;
            }

            case "ノーハンド": {
                for (int i = 0; i < 4; i++) {
                    this.handList[i] = deck.getCard();
                }
                break;
            }
        }
    }
}
