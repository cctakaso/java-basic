package basic.tutorials.diamond;

import java.util.Scanner;

/**
 * ステップ2: ダイアモンドの上半分を描くプログラム
 * 各行の先頭に必要なスペースの数と、「*」の数を計算して出力します。
 * これにより、図形描画における座標計算の考え方を学びます。
----------------------------
ダイアモンド例 (入力値: 3)
 *
***
 *
----------------------------
ダイアモンド例 (入力値: 5)
  *
 ***
*****
 ***
  *
----------------------------
ダイアモンド例 (入力値: 7)
   *
  ***
 *****
*******
 *****
  ***
   *
 */
public class DiamondStep2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 奇数を入力すると綺麗な菱形になります
        System.out.print("ダイアモンドの幅(奇数)を入力してください > ");
        int width = scanner.nextInt();

        // 中心までの距離を計算 (例: widthが7なら、centerは3)
        int center = width / 2;

        // ダイアモンドの上半分を描画
        for (int i = 0; i <= center; i++) {
            // 1. スペースを出力 (行が下がるにつれて減っていく)
            for (int j = 0; j < center - i; j++) {
                System.out.print(" ");
            }

            // 2. 「*」を出力 (行が下がるにつれて増えていく)
            for (int j = 0; j < (i * 2) + 1; j++) {
                System.out.print("*");
            }

            // 3. 1行描画したら改行
            System.out.println();
        }
        scanner.close();
    }
}