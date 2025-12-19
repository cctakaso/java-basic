package basic.tutorials.sort;

/**
 * マージソート (Merge Sort)
 *
 * 考え方 (分割統治法):
 * 1. 【分割】ソート対象の配列を、要素数が1になるまで再帰的に2つに分割していく。
 *    (要素数が1の配列は、定義上ソート済みとみなせる)
 * 2. 【統治】分割した2つの部分配列を、それぞれソート済みの状態にする。
 * 3. 【結合】ソート済みの2つの部分配列を、1つのソート済み配列にマージ（統合）する。
 *    マージ処理では、2つの部分配列の先頭要素を比較し、小さい方から順に新しい配列に格納していく。
 * この再帰的な分割と結合を繰り返すことで、最終的に配列全体がソートされる。
 */
public class SortMerge {

    /**
     * マージソートアルゴリズムを使用して整数配列をソートします。
     * @param array ソートする整数配列
     * @return ソートされた整数配列
     */
    public static int[] sort(int[] array) {
        // 配列の要素が1つ以下なら、すでにソート済みなので何もしない
        if (array.length <= 1) {
            return array;
        }
        // 再帰的なマージソートを開始
        mergeSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * 配列を再帰的に分割するためのメソッド
     * @param array 対象の配列
     * @param left 分割範囲の左端のインデックス
     * @param right 分割範囲の右端のインデックス
     */
    private static void mergeSort(int[] array, int left, int right) {
        // 左端が右端より小さい場合（＝分割する要素が2つ以上ある場合）
        if (left < right) {
            // 中間地点のインデックスを計算
            int middle = (left + right) / 2;
            // 左半分を再帰的にソート
            mergeSort(array, left, middle);
            // 右半分を再帰的にソート
            mergeSort(array, middle + 1, right);
            // ソートされた2つの半分をマージ（統合）する
            merge(array, left, middle, right);
        }
    }

    /**
     * 2つのソート済み部分配列を1つのソート済み配列にマージするメソッド
     * @param array 対象の配列
     * @param left 左の部分配列の開始インデックス
     * @param middle 左右の部分配列の境界インデックス
     * @param right 右の部分配列の終了インデックス
     */
    private static void merge(int[] array, int left, int middle, int right) {
        // --- 2つの部分配列のサイズを計算 ---
        int n1 = middle - left + 1; // 左の部分配列のサイズ
        int n2 = right - middle;    // 右の部分配列のサイズ

        // --- 作業用の一次配列を作成 ---
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // --- 元の配列からデータを一次配列にコピー ---
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int i = 0; i < n2; i++) {
            rightArray[i] = array[middle + 1 + i];
        }

        // --- 2つの一次配列を比較しながら、元の配列にマージしていく ---
        int i = 0; // 左の一次配列のインデックス
        int j = 0; // 右の一次配列のインデックス
        int k = left; // 元の配列のマージ開始位置のインデックス

        // 左右どちらかの一次配列の末尾に達するまでループ
        while (i < n1 && j < n2) {
            // 左の配列の要素が右の配列の要素以下の場合
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i]; // 左の要素を元の配列に格納
                i++;
            } else {
                array[k] = rightArray[j]; // 右の要素を元の配列に格納
                j++;
            }
            k++;
        }

        // --- 残った要素を元の配列にコピー ---
        // 左の一次配列に要素が残っている場合
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        // 右の一次配列に要素が残っている場合
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
