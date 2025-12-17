package basic.chapter_4;

import java.util.Scanner;
/**
 * 第4章: 場合分けを考えよう！条件分岐 if文
 *
 * 【このコードで学ぶこと】
 * - 基本的なif文、if-else文、if-else if-else文の構造と使い方。
 * - 複数の条件を組み合わせる方法（論理演算子の活用）。
 * - 必ず `{}` を付けるというコーディングスタイル 
 */
public class App {

    public static void main(String[] args) {
        // --- キーボードからの入力  ---
        Scanner scanner = new Scanner(System.in);
        System.out.print("貴方の数学の点数を入力してください: "); // printはprintlnと違い改行しない
        int score = scanner.nextInt();
        scanner.close();


        System.out.println("科目: 数学, 点数: " + score + "点");

        // --- if-else if-else 文による成績判定 ---
        if (score >= 90) {
            System.out.println("評価: 優");
        } else if (score >= 80) {
            System.out.println("評価: 良");
        } else if (score >= 60) {
            System.out.println("評価: 可");
        } else if (score >= 50) {
            System.out.println("評価: 追試");
        } else {
            System.out.println("評価: 不可");
        }

        // --- 複雑な条件式 ---
        // 点数が100点、または、0点だった場合に特別なメッセージを表示
        if (score == 100 || score == 0) {
            System.out.println("特徴的な点数です！");
        }else{
            System.out.println("通常の点数です。");
        }
        // 三項演算子を使った同じ条件の表現
        System.out.println(score == 100 || score == 0 ? 
            "特徴的な点数です！" : "通常の点数です。");

        // 点数が50点以上60点以下なら「追試」
        if (50 <= score && score < 60) {
            System.out.println("数学は追試です。");
        }else if (score < 50) {
            System.out.println("数学は不合格です。");
        }else{
            System.out.println("数学は合格です。");
        }
        // 三項演算子を使った同じ条件の表現
        System.out.println(50 <= score && score < 60 ? 
            "数学は追試です。" : score < 50 ? "数学は不合格です。": "数学は合格です。");

    }
}