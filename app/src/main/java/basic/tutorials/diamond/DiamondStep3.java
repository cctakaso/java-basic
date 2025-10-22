package basic.tutorials.diamond;

import java.util.Scanner;

/**
 * ステップ3: 完全なダイアモンドを描くプログラム
 * ステップ2の上半分を描画するロジックに、逆の順序で下半分を描画するロジックを追加します。
 * これにより、一つのプログラム内で複数のループを組み合わせて目的の図形を完成させる手法を学びます。
 * 実行例 (入力値: 9)
 *     *
 *    ***
 *   *****
 *  *******
 * *********
 *  *******
 *   *****
 *    ***
 *     *
 */
public class DiamondStep3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ダイアモンドの幅(奇数)を入力してください > ");
        int width = scanner.nextInt();

        // 入力が偶数の場合、奇数に補正してバランスを整える
        if (width % 2 == 0) {
            width++;
            System.out.println("偶数が入力されたため、幅を " + width + " に補正しました。");
        }

        int center = width / 2;

        // --- ダイアモンドの上半分 ---
        for (int i = 0; i <= center; i++) {
            // スペースの描画
            for (int j = 0; j < center - i; j++) {
                System.out.print(" ");
            }
            // 「*」の描画
            for (int j = 0; j < (i * 2) + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // --- ダイアモンドの下半分 ---
        // 中心の1行下から逆順に描画していく
        for (int i = center - 1; i >= 0; i--) {
            // スペースの描画
            for (int j = 0; j < center - i; j++) {
                System.out.print(" ");
            }
            // 「*」の描画
            for (int j = 0; j < (i * 2) + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        scanner.close();
    }
}
