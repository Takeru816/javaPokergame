package poker2;

import java.util.Arrays;

public abstract class AbstractPokerPlayer implements InterfacePokerPlayer {
    protected String name;								// プレイヤー名
    protected Card[] handList = new Card[5];			// 手札
    protected String hand;								// 手札の役

    public AbstractPokerPlayer(Deck deck) {
        setHand(deck);
    }

    protected abstract void setName();

    protected void setHand(Deck deck) {
        for (int i = 0; i < 5; i++) {
            this.handList[i] = deck.getCard();
        }
        judgeHand();
    }

    @Override
    public void showHand() {
        System.out.println(this.name);
        for (int i = 0; i < 5; i++) {
            System.out.println(this.handList[i]);
        }
        System.out.println(this.hand);
    }

    public void sortHand() {
        Arrays.sort(this.handList);
    }

	// 役の判定
    protected void judgeHand() {
        sortHand();

		// 役を判定するための変数
		int pair_count = 0;
		int match_count = 0;
		int match_num = 0;
		boolean flash_flag = true;
		boolean straight_flag = true;

		// 役の判定
		for (int i = 1; i < 5; i++) {
			if (handList[i].getNumber() == handList[i - 1].getNumber()) {
				match_count += 1;
				// 最後のループでチェック
				if (i == 4) {
					if (match_count == 1) {
						pair_count += 1;
					// 3カード以上
					} else if (1 < match_count) {
						match_num = match_count + 1;
					}
				}
			} else {
				// 違う数字の場合
				if (match_count == 1) {
					pair_count += 1;
				// 3カード以上の場合
				} else if (1 < match_count) {
					match_num = match_count + 1;
				}
				match_count = 0;
			}
			// 同じマークが続いてるかチェック（フラッシュ用）
			if (flash_flag && handList[i].getSymbol() != handList[i - 1].getSymbol()) {
				flash_flag = false;
			}
			// 数字が連続しているか
			if (straight_flag && handList[i - 1].getNumber() != handList[i].getNumber() - 1) {
				// ロイヤルストレート用
				if (handList[i - 1].getNumber() != 1 || handList[i].getNumber() != 10) {
					straight_flag = false;
				}
			}
		}

		if (straight_flag && flash_flag) {
			if (handList[0].getNumber() == 1 && handList[4].getNumber() == 13) {
				hand = "ロイヤルストレートフラッシュ";
			} else {
				hand = "ストレートフラッシュ";
			}
		} else if (2 < match_num) {
			if (match_num == 4) {
				hand = "4カード";
			} else {
				if (0 < pair_count) {
					hand = "フルハウス";
				} else {
					hand = "3カード";
				}
			}
		} else if (flash_flag) {
			hand = "フラッシュ";
		} else if (straight_flag) {
			hand = "ストレート";
		} else if (0 < pair_count) {
			if (pair_count == 2) {
				hand = "2ペア";
			} else {
				hand = "1ペア";
			}
		} else {
			hand = "ノーハンド";
		}
    }
}