package basic.b_chapter_2;

/**
 * 第2章: データを入れる魔法の箱！変数とデータ型
 *
 * 【このコードで学ぶこと】
 * - 各種データ型 (int, double, boolean, char, String) の使い方。
 * - データ型のサイズに関する補足 
 * - finalを使った定数の定義 
 * - 変数が使える範囲である「スコープ」の概念 
 */
public class App {

    public static void main(String[] args) {

        // --- 基本データ型の紹介 ---
        // int: 整数型 (32ビット)
        int age = 25;

        // double: 浮動小数点型 (64ビット)。小数はこちらを使うのが一般的。
        double height = 172.5;

        // boolean: 真偽値型 (true or false)
        boolean isStudent = true;

        // char: 文字型 (16ビット)。シングルクォーテーションで囲む。
        // Unicode文字を扱えるため、'A'や'あ'など様々な文字を1つだけ格納できる。
        char bloodType = 'A';

        // --- 参照型の紹介 ---
        // String: 文字列型。厳密には基本データ型ではないが、同様に扱える。
        String name = "Taro Yamada";

        System.out.println("--- 自己紹介 ---");
        System.out.println("名前: " + name);
        System.out.println("年齢: " + age + "歳");
        // ... 他の変数の表示

        // --- 定数 (final) ---
        // `final` を付けると再代入できなくなる定数になる。
        final double TAX_RATE = 0.1;
        System.out.println("消費税率は " + TAX_RATE + " です。");
        // TAX_RATE = 0.08; // この行を有効にするとコンパイルエラー

        // --- 変数のスコープ  ---
        // 変数は宣言されたブロック `{}` の中でしか使えない。
        {
            String blockScopedMessage = "このメッセージはこのブロックの中だけのものです。";
            System.out.println(blockScopedMessage);
        }
        // System.out.println(blockScopedMessage); // ブロックの外なのでコンパイルエラー

        // --- 複数変数の宣言  ---
        int x = 5, y = 6, z = 50;
        System.out.println("x, y, z の値: " + x + ", " + y + ", " + z);

    }
}