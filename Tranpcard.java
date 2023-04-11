package kouka3.poker2;

import java.util.ArrayList;
import java.util.Collections;


public class Tranpcard {
    // フィールド
    private ArrayList<Card> deck_list;
    // コンストラクタ
    public Tranpcard() {
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
        return deck_list.remove(0);
    }

    public void shuffleDeck() {
        Collections.shuffle(deck_list);
    }
}