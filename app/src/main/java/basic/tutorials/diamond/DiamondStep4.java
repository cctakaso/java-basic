package basic.tutorials.diamond;

import java.util.Scanner;

/**
 * 完成版: 洗練されたダイアモンド描画プログラム
 * 上下二つのループを一つに統合し、中心からの距離を「絶対値」で計算することで、
 * より効率的でコードの重複がない、洗練されたロジックでダイアモンドを描画します。
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
public class DiamondStep4 {

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

        // 一つのループでダイアモンド全体を描画
        for (int i = 0; i < width; i++) {
            // 中心からの距離を計算 (例: width=7, center=3 の場合)
            // i=0 -> |3-0|=3 | i=1 -> |3-1|=2 | i=2 -> |3-2|=1 | i=3 -> |3-3|=0
            // i=4 -> |3-4|=1 | i=5 -> |3-5|=2 | i=6 -> |3-6|=3
            int distanceFromCenter = Math.abs(center - i);
   
            // 描画する「*」の数を計算
            int numAsterisks = width - (2 * distanceFromCenter);

            // 1. スペースを出力
            for (int j = 0; j < distanceFromCenter; j++) {
                System.out.print(" ");
            }

            // 2. 「*」を出力
            for (int j = 0; j < numAsterisks; j++) {
                System.out.print("*");
            }
   
            // 3. 改行
            System.out.println();
        }
        scanner.close();
    }
}