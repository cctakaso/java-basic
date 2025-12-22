package basic.b_chapter_7;

/**
 * 第7章: コードを整理整頓！再利用できる部品「メソッド」
 *
 * 【このコードで学ぶこと】
 * - メソッドの定義と呼び出し（引数、戻り値）。
 * - メソッド化によるコードの再利用性と可読性の向上。
 * - メソッドが自身を呼び出す「再帰呼び出し」  。
 */
public class App {

    // mainメソッドから各種メソッドを呼び出す
    public static void main(String[] args) {
        // 引数あり、戻り値ありのメソッド
        double result = calculateArea(5.0, 10.0);
        System.out.println("幅5.0, 高さ10.0の長方形の面積: " + result);

        // 同じメソッドを違う引数で再利用
        System.out.println("幅3.0, 高さ4.0の長方形の面積: " + calculateArea(3.0, 4.0));

        // --- 再帰呼び出し  ---
        // 1から10までの合計を計算
        int sumResult = sumUpTo(10);
        System.out.println("1から10までの合計: " + sumResult);
    }

    /**
     * 長方形の面積を計算して返すメソッド。
     * @param width  幅 (double型)
     * @param height 高さ (double型)
     * @return 面積 (double型)
     */
    public static double calculateArea(double width, double height) {
        // 戻り値の型 (double) をメソッド名の前に書く
        return width * height; // returnで結果を返す
    }

    /**
     * 1から指定された数(k)までの合計を計算する再帰メソッド。
     * @param k 正の整数
     * @return 1からkまでの合計値
     */
    public static int sumUpTo(int k) {
        if (k > 0) {
            // k + (k-1までの合計) という形で自身を呼び出す
            return k + sumUpTo(k - 1);
        } else {
            // kが0になったら再帰を停止し、0を返す
            return 0;
        }
    }
}