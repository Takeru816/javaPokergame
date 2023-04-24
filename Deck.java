package poker2;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
    // フィールド
    private ArrayList<Card> deck_list;
    // コンストラクタ
    public Deck() {
        setDeck();
    }

    // デッキ生成
    public void setDeck() {
        this.deck_list = new ArrayList<>();
        String[] symbol_list = {"spade", "heart", "diamond", "club"};
        for (int number_i = 1; number_i < 13 + 1; number_i++) {
            for (int symbol_i = 0; symbol_i < symbol_list.length; symbol_i++) {
                this.deck_list.add(new Card(number_i, symbol_list[symbol_i]));
            }
        }
        shuffleDeck();
    }

    public Card getCard() {
        return this.deck_list.remove(0);
    }

    private void shuffleDeck() {
        Collections.shuffle(this.deck_list);
    }

    public void sortDeck() {
        Collections.sort(this.deck_list);
    }
}