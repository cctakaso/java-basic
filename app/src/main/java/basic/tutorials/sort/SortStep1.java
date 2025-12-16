package basic.tutorials.sort;

import java.util.Scanner;

public class SortStep1 {
    /**
     * 1からsizeまでの連続値をシャッフルしてsize個の要素を持つ整数配列を作成します。
     * (フィッシャー・イェーツのシャッフルアルゴリズム)
     * @return シャッフルされた整数配列
     */
    public static int[] createTestData(int size) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        // 配列をシャッフルする
        for (int i = 0; i < array.length; i++) {
            int index = (int) (Math.random() * (array.length-1));
            // 要素を交換
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
        // 1. 試験データの作成
        Scanner scanner = new Scanner(System.in);
        System.out.print("試験データ数を入力してください (10〜100程度) > ");
        int n = scanner.nextInt();
        int[] originalData = createTestData(n);

        scanner.close();
    }
}
