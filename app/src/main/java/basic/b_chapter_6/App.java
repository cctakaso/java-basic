package basic.b_chapter_6;

/**
 * 第6章: 面倒な作業は自動化！ループ処理 for, while
 *
 * 【このコードで学ぶこと】
 * - for文、while文、拡張for文の基本的な使い方。
 * - ループを制御する break と continue。
 * - 多重配列と、それを行列として扱うための二重ループ  。
 */
public class App {

    public static void main(String[] args) {
        // --- while文：continue ---
        System.out.println("\n--- while文: 1から10までの偶数のみ表示 (continue/break) ---");
        int [] numbers = {5,2,4,1,5,6,7,12,3,9,8,10,12,0,11}; // 配列の宣言と初期化
        int k = 0;
        while (k < numbers.length) {
            if (numbers[k] == 0) { // 0なら
                break; // ループ終了
            }else if (numbers[k] % 2 != 0 || numbers[k]>10) { // 奇数、または10以上なら
                k++;
                continue; // スキップ
            }
            System.out.print(numbers[k] + " ");
            k++;
        }
        System.out.println(); // 改行

        // --- for文 ---
        System.out.println("\n--- for文: 1から10までの偶数のみ表示 (continue) ---");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 != 0) { // 奇数なら
                continue; // スキップ
            }
            System.out.print(i + " ");
        }
        System.out.println(); // 改行

        // --- 拡張for文 による配列の処理 ---
        System.out.println("\n--- 拡張for文 ---");
        // 配列の宣言と初期化 
        String[] languages = {"Java", "Python", "JavaScript"};
        for (String lang : languages) {
            System.out.println("言語: " + lang);
        }

        // --- 二重ループと多重配列  ---
        System.out.println("\n--- 九九の表 (二重ループ) ---");
        // 多重配列の宣言と初期化
        int[][] multiplicationTable = new int[9][9];

        // 外側のループ (行: 1の段, 2の段, ...)
        for (int i = 0; i < 9; i++) {
            // 内側のループ (列: x1, x2, ...)
            for (int j = 0; j < 9; j++) {
                multiplicationTable[i][j] = (i + 1) * (j + 1);
                // %3d は、3桁分のスペースを確保して右詰めで表示する書式指定子
                System.out.printf("%3d ", multiplicationTable[i][j]);
            }
            System.out.println(); // 1つの段が終わったら改行
        }
    }
}