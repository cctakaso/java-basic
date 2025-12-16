package basic.tutorials.sort;

import java.util.Scanner;

public class SortStep2 {
    /**
     * 整数配列をアスタリスク(*)を使用した縦方向の棒グラフとして表示します。
     * @param array 表示する整数配列
     * --- Original Data ---
     *      *    
     *  *   *    
     * **   *    
     * **   *  * 
     * **  **  * 
     * *** **  * 
     * *** ** ** 
     * ****** ** 
     * ****** ***
     * **********
     */
    public static void displayData(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        for (int i = max; i > 0; i--) {
            for (int j = 0; j < array.length; j++) {
                if (array[j] >= i) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // 1. 試験データの作成
        Scanner scanner = new Scanner(System.in);
        System.out.print("試験データ数を入力してください (10〜100程度) > ");
        int n = scanner.nextInt();
        int[] originalData = SortStep1.createTestData(n);

        // 2. ソート前のデータを表示
        System.out.println("--- Original Data ---");
        displayData(originalData);
        System.out.println();

        scanner.close();
    }
}
