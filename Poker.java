package kouka3.poker2;
import java.util.*;

public class Poker {
	// staticフィールド
    protected static Tranpcard deck_list = new Tranpcard();                            // 山札のlist(static)
	protected static ArrayList<String> player_name_list = new ArrayList<>();
    protected static ArrayList<String> hand_name_list = new ArrayList<>();

	// 勝者を決めるstaticメソッド
	public static void judge() {
		int[] judge_point = new int[player_name_list.size()];
		for (int list_i = 0; list_i < player_name_list.size(); list_i++) {
			switch (hand_name_list.get(list_i)) {
				case "ロイヤルストレートフラッシュ" : judge_point[list_i] = 1; break;
				case "ストレートフラッシュ" : judge_point[list_i] = 2; break;
				case "4カード" : judge_point[list_i] = 3; break;
				case "フルハウス" : judge_point[list_i] = 4; break;
				case "フラッシュ" : judge_point[list_i] = 5; break;
				case "ストレート" : judge_point[list_i] = 6; break;
				case "3カード" : judge_point[list_i] = 7; break;
				case "2ペア" : judge_point[list_i] = 8; break;
				case "1ペア" : judge_point[list_i] = 9; break;
				case "ノーハンド" : judge_point[list_i] = 10; break;
				default : judge_point[list_i] = -1; break;
			}
		}

		int min = 10;
		for (int list_i = 0; list_i < judge_point.length; list_i++) {
			if (min > judge_point[list_i]) 
				min = judge_point[list_i];
		}
		int winner_count = 0;
		for (int list_i = 0; list_i < judge_point.length; list_i++) {
			if (min == judge_point[list_i])
				winner_count += 1;
		}
		ArrayList<Integer> winner_index_list = new ArrayList<>();
		for (int list_i = 0; list_i < judge_point.length; list_i++) {
			if (min == judge_point[list_i])
				winner_index_list.add(list_i);
		}

		System.out.println("今回の勝負の勝者は");
		for (int list_i = 0; list_i < winner_index_list.size(); list_i++) {
			System.out.print(player_name_list.get(winner_index_list.get(list_i)) + "さん　");
		}
		System.out.println("です！");
		
	}
    // フィールド 
    protected ArrayList<Card> hand_list = new ArrayList<>();                                   // 手札のlist
    protected String hand_name;                                                                   // 手札の役
    protected String player_name;                                               
    // コンストラクタなし


	// ゲーム序盤のメソッド
	public void pokerJoban() {
		speace();
		setName();
		createHand();
		handJudgement();
		showHand();
	}

	// ゲーム中盤のメソッド
	public void pokerTuban() {
		speace();
		showHand();
		changeHand();
	}

	// ゲーム終盤のメソッド
	public void pokerSyuban() {
		handJudgement();
		showHand();
		setPlayerNameList();
		setHandNameList();
	}

	// 名前をstaticのリストにセット
	protected void setPlayerNameList() {
		player_name_list.add(player_name);
	}

	// 手札をstaticのリストにセット
	protected void setHandNameList() {
		hand_name_list.add(hand_name);
	}

	// 名前をセットするメソッド
    protected void setName() {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("プレイヤー名を入力してください");
        System.out.print("プレイヤー名には英数字しか使えません：");     player_name = stdIn.next();
    }
	
    // 手札を生成するメソッド
    protected void createHand() {
        // 手札(hand_list)を生成
		for (int hand_i = 0; hand_i < 5; hand_i++) {
			hand_list.add(deck_list.getCard());

		}
    }

    // 手札を表示するメソッド
    protected void showHand() {
        System.out.println(player_name + "さんの手札は");
        for (int hand_i = 0; hand_i < hand_list.size(); hand_i++) {
			hand_list[hand_i].showCard();
		}
        System.out.println(hand_name);
        System.out.println();
    }

    // 手札を交換するメソッド
    protected void changeHand() {
        Scanner stdIn = new Scanner(System.in);
		System.out.println(player_name + "さんは");
        // 手札を交換するための配列(change_list)を生成する
		int[] change_list = {0, 0, 0, 0, 0};
		for (int i = 0; i < 5; i++) {
			System.out.println("手札の" + (i + 1) + "番目のカードを交換しますか？");
			System.out.print("1.交換する / 2.交換しない：");		int change = stdIn.nextInt();
			if (change == 1) {
				change_list[i] = 1;
			}
			System.out.println();
		}

		// 手札を交換する
		for (int change_i = 0; change_i < change_list.length; change_i++) {
			if (change_list[change_i] == 1) {
				hand_list.set(change_i, deck_list.getCard());
			}
		}
        System.out.println();
    }

    // 役の判定をするメソッド
    protected void handJudgement() {

        // 手札をソートするための配列(hand_number_list)
		int[] hand_number_list = new int[hand_list.size()];

		// HashMap型(deck_listのcard)から取り出したObject型の値をint型にダウンキャストするための変数
		Object downcast_before = null;
		int downcast_after = 0;

		for (int hand_i = 0; hand_i < hand_list.size(); hand_i++) {
			// numberのダウンキャスト
			downcast_before = hand_list.get(hand_i).getNumber();
			downcast_after = (int) downcast_before;
			hand_number_list[hand_i] = downcast_after;
		}

		Arrays.sort(hand_number_list);

		// 役を判定するための変数
		int pair_count = 0;
		int match_count = 0;
		int match_num = 0;
		boolean flash_flag = true;
		boolean straight_flag = true;

		// 役の判定
		for (int hand_i = 1; hand_i < hand_number_list.length; hand_i++) {
			if (hand_number_list[hand_i - 1] == hand_number_list[hand_i]) {
				match_count += 1;
				// 最後のループでチェック
				if (hand_i == 4) {
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
			if (flash_flag && hand_list.get(hand_i).getSymbol() != hand_list.get(hand_i - 1).getSymbol()) {
				flash_flag = false;
			}
			// 数字が連続しているか
			if (straight_flag && hand_number_list[hand_i - 1] != hand_number_list[hand_i] - 1) {
				// ロイヤルストレート用
				if (hand_number_list[hand_i - 1] != 1 || hand_number_list[hand_i] != 10) {
					straight_flag = false;
				}
			}
		}
		// 最終手札チェック
		if (straight_flag && flash_flag) {
			if (hand_number_list[0] == 1 && hand_number_list[4] == 13) {
				hand_name = "ロイヤルストレートフラッシュ";
			} else {
				hand_name = "ストレートフラッシュ";
			}
		} else if (2 < match_num) {
			if (match_num == 4) {
				hand_name = "4カード";
			} else {
				if (0 < pair_count) {
					hand_name = "フルハウス";
				} else {
					hand_name = "3カード";
				}
			}
		} else if (flash_flag) {
			hand_name = "フラッシュ";
		} else if (straight_flag) {
			hand_name = "ストレート";
		} else if (0 < pair_count) {
			if (pair_count == 2) {
				hand_name = "2ペア";
			} else {
				hand_name = "1ペア";
			}
		} else {
			hand_name = "ノーハンド";
		}
    }
	protected void speace() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}
}
