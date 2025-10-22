package basic.tutorials.sort;

public class SortUtils {

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
        for (int i = array.length - 1; i > 0; i--) {
            int index = (int) (Math.random() * (i + 1));
            // 要素を交換
            int temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        return array;
    }

    /**
     * 整数配列をアスタリスク(*)を使用した縦方向の棒グラフとして表示します。
     * @param array 表示する整数配列
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
}
