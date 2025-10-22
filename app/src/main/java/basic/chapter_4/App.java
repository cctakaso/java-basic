package basic.chapter_4;

/**
 * 第4章: プログラムに考えさせよう！条件分岐 if文
 *
 * 【このコードで学ぶこと】
 * - 基本的なif文、if-else文、if-else if-else文の構造と使い方。
 * - 複数の条件を組み合わせる方法（論理演算子の活用）。
 * - 必ず `{}` を付けるというコーディングスタイル 
 */
public class App {

    public static void main(String[] args) {
        int score = 75;
        String subject = "Math";

        System.out.println("科目: " + subject + ", 点数: " + score + "点");

        // --- if-else if-else 文による成績判定 ---
        if (score >= 90) {
            System.out.println("評価: 優");
        } else if (score >= 80) {
            System.out.println("評価: 良");
        } else if (score >= 60) {
            System.out.println("評価: 可");
        } else {
            System.out.println("評価: 不可");
        }

        // --- 複雑な条件式 ---
        // 「数学」で、かつ、点数が60点以上なら「追試なし」
        if (subject.equals("Math") && score >= 60) {
            System.out.println("数学は合格です。");
        }

        // 点数が100点、または、0点だった場合に特別なメッセージを表示
        if (score == 100 || score == 0) {
            System.out.println("特徴的な点数です！");
        }
    }
}