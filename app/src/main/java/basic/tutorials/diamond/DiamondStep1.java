package basic.tutorials.diamond;

import java.util.Scanner;

/**
 * ステップ1: 正方形を描くプログラム
 * ユーザーから数値を入力させ、そのサイズで「*」を敷き詰めた正方形を描画します。
 * これにより、基本的な入力の受け取りと、行と列を制御する二重ループの構造を学びます。
 * 実行例 (入力値: 7)
 * *******
 * *******
 * *******
 * *******
 */
public class DiamondStep1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("正方形の一辺の長さを入力してください > ");
        int size = scanner.nextInt();

        // 外側のループが行（縦方向）を制御
        for (int i = 0; i < size; i++) {
            // 内側のループが列（横方向）を制御
            for (int j = 0; j < size; j++) {
                // `*` と半角スペースを出力
                System.out.print("* ");
            }
            // 1行の描画が終わったら改行
            System.out.println();
        }
        scanner.close();
    }
}