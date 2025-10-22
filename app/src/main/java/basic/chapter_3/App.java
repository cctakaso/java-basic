package basic.chapter_3;

/**
 * 第3章: 計算と比較はお任せ！演算子と型キャスト
 *
 * 【このコードで学ぶこと】
 * - 算術、関係、論理、代入演算子の使い方。
 * - 異なるデータ型間の計算ルールである「型キャスト」。
 * - if文の簡易版として使える「三項演算子」  。
 */
public class App {

    public static void main(String[] args) {
        // --- 算術演算子と型キャスト ---
        int a = 10;
        int b = 3;
        // int同士の割り算は小数点以下が切り捨てられる
        System.out.println("10 / 3 = " + (a / b));

        // 正確な計算結果を得るには、どちらかをdouble型にする必要がある。
        // 演算時に、もう一方も自動的にdouble型に変換される (暗黙の型キャスト)
        double result = (double) a / b;
        System.out.println("(double)10 / 3 = " + result);

        // --- 関係演算子と論理演算子 ---
        int age = 20;
        boolean hasLicense = false;
        // 20歳以上、かつ、免許を持っているか？
        boolean canDrive = (age >= 20) && (hasLicense == true);
        System.out.println("運転できますか？ " + canDrive);

        // --- 三項演算子  ---
        // (条件式) ? (trueの場合の値) : (falseの場合の値)
        // 上の canDrive の結果を使ってメッセージを切り替える
        String message = canDrive ? "運転できます。" : "運転できません。";
        System.out.println(message);

        // --- インクリメント・デクリメント演算子  ---
        int count = 5;
        // 後置インクリメント: 式を評価した後に1を足す
        System.out.println("count++ の評価値: " + (count++)); // 5 が表示される
        System.out.println("評価後のcount: " + count);      // 6 になっている

        // 前置インクリメント: 1を足した後に式を評価
        count = 5; // リセット
        System.out.println("++count の評価値: " + (++count)); // 6 が表示される
        System.out.println("評価後のcount: " + count);      // 6 になっている
    }
}
